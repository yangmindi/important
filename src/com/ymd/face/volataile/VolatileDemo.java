package com.ymd.face.volataile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addTo60(){
        this.number = 60;
    }

    //请注意，此时number前面是加了volatile关键字修饰的，volatile不能保证原子性
    public void addPlusPlus(){
        number++;
    }

    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo{
    public static void main(String[] args) {
        MyData myData = new MyData();

        for(int i=1; i<=20; i++){
            new Thread(()->{
                for(int j=1; j<=1000; j++){
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String .valueOf(i)).start();
        }

        //需要等待上面20个线程全部计算完成之后，再用main线程取得最终的结果值是多少？
        while(Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int type,finally number value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type,finally number value:"+myData.atomicInteger);
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