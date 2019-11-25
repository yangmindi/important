package com.ymd.face.AlterNumLock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i=1; i<=6; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"\t ******秦帝国，一统华夏");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeDoor(){
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i=1; i<=6; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t上完自习离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"\t *******班长最后关门走人");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
