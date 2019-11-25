package com.ymd.face.BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {//资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {

        lock.lock();
        try {
            //1.判断
            while (number != 0) {
                //等待，不能生产
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();

        while(number == 0){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        condition.signalAll();

        lock.unlock();
    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for(int i=1; i<=5; i++){
                shareData.increment();
            }
        },"AA").start();

        new Thread(()->{
            for(int i=1; i<=5; i++){
                shareData.decrement();
            }
        },"BB").start();
    }
}
