package com.ymd.juc_2019;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resources{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void Prod(){
        lock.lock();
        try{
            while(number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+"生产，number="+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void Consumer(){
        lock.lock();
        try{
            while(number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+"消费，number="+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        Resources resources = new Resources();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    resources.Prod();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    resources.Prod();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    resources.Consumer();
                }
            }
        },"CC").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    resources.Consumer();
                }
            }
        },"DD").start();
    }
}
