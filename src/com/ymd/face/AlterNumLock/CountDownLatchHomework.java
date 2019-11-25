package com.ymd.face.AlterNumLock;

import java.util.concurrent.CountDownLatch;

//春夏秋冬
public class CountDownLatchHomework {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(4);

        for(int i=0; i<4; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"一年四季...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
