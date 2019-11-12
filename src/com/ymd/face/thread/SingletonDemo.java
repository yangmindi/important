package com.ymd.face.thread;

public class SingletonDemo {
    private volatile static SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }

    //DCL
    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //并发多线程后，情况发生了很大的变化
        for(int i=1; i<=10; i++){
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
