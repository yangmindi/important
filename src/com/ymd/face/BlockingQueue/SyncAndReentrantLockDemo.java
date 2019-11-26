package com.ymd.face.BlockingQueue;

import javax.annotation.Resource;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyCache{
    volatile int number = 1;//1-A  2-B  3-C
    Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            while(number!=1){
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=1; i<=5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 2;
            c2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while(number!=2){
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=1; i<=10; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while(number!=3){
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=1; i<=15; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }

}

public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for(int i=1; i<=10; i++){
            new Thread(()->{
                myCache.print5();
            },"AAA").start();

            new Thread(()->{
                myCache.print10();
            },"BBB").start();

            new Thread(()->{
                myCache.print15();
            },"CCC").start();
        }
    }
}
