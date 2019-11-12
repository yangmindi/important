package com.ymd.face.thread;

public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01(){
        a = 1;        //语句1
        flag = true;  //语句2
    }

    public void method02(){
        if(flag){
            a = a + 5;//语句3
            System.out.println("*****reValue:"+a);
        }
    }
}
