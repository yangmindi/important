package com.ymd.face;

import java.util.concurrent.TimeUnit;

class MyData {
    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }
}

public class VolatileDemo{
    public static void main(String[] args) {

    }

    public static void seeOkByVolatile(){
        MyData myData = new MyData();//资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update number value:"+myData.number);
        },"AAA").start();

        //第二个线程就是我们的主线程
        while(myData.number == 0){
            //mian线程就一直在这里循环，知道number值不再等于零
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get number value:"+myData.number);
    }
}