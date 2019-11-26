package com.ymd.face.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName()+"\t invkoed sendMsg");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t ####invoked sendEmail");
    }

    //====================================================

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            setLock();
        }finally {
            lock.unlock();
        }
    }

    public void setLock(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set");
        }finally {
            lock.unlock();
        }
    }
}

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendMsg();
        },"AAA").start();

        new Thread(()->{
            phone.sendMsg();
        },"BBB").start();

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();
    }
}
