package com.ymd.face.ArrayList;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        List list = new Vector();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 20; i++) {
            final int temp = i;
            new Thread(() -> {
                String substring = UUID.randomUUID().toString().substring(0, 8);
                list.add(substring);
                System.out.println(list);
            }, String.valueOf(i)).start();

        }
    }
}
