package com.ymd.face.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo01 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger =new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2014));
        System.out.println(atomicInteger.get());
    }
}
