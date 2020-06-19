package com.ymd.face.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newFixedThreadPool {
    public static void main(String[] args) {
        //一池多个处理线程，线程个数不确定
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        //10个用户办理业务，每个用户就是一个来自外部的请求线程
        try {
            //模拟10个用户
            for (int i = 1; i <= 10; i++) {
                //由一个线程池处理
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "办理业务");
                });

                //暂停一会线程
                Thread.sleep(1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
