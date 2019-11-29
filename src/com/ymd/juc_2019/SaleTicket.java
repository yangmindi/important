package com.ymd.juc_2019;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void saleTicket(){
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"\t 卖出票："+(number--)+"还剩："+number+"张票");
            }
        }finally {
            lock.unlock();
        }
    }
}

/*
题目：三个售票员  卖出  30张票
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{ for(int i=1; i<=40; i++){ ticket.saleTicket(); } },"AA").start();
        new Thread(()->{ for(int i=1; i<=40; i++){ ticket.saleTicket(); } },"BB").start();
        new Thread(()->{ for(int i=1; i<=40; i++){ ticket.saleTicket(); } },"CC").start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=40; i++){
                    ticket.saleTicket();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=40; i++){
                    ticket.saleTicket();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=40; i++){
                    ticket.saleTicket();
                }
            }
        },"C").start();*/
    }
}
