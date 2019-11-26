package com.ymd.face.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class MapNotSafeDemo {
    public static void main(String[] args) {
        Map<String ,Integer> map = new HashMap<>();
        for(int i=0; i<10; i++){
            final int temp = i;
            new Thread(()->{
                map.put(temp+"",temp);
                System.out.println(map.get(temp+""));
            },String.valueOf(i+1)).start();
        }
    }
}
