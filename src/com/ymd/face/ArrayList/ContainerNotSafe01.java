package com.ymd.face.ArrayList;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ContainerNotSafe01 {
    public static void main(String[] args) {
//        List list = new ArrayList<>();
//        List list = Collections.synchronizedList(new ArrayList<>());
        List list = new CopyOnWriteArrayList();

        for(int i=1; i<=20; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
