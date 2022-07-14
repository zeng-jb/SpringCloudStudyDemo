package com.zeng.threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class TestThread {

    static Thread thread1;
    static Thread thread2;

    public static void test1(){
        thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("当前正在线程一处理：：：："+i);
                if(i == 5){
                    //唤醒线程2
                    LockSupport.unpark(thread2);
                    //挂起当前线程
                    LockSupport.park();
                }
            }
        });
        thread2 = new Thread(() -> {

            LockSupport.park();
            System.out.println("我是线程2，我告诉你正在消费多少任务：===》》》");
            LockSupport.unpark(thread1);

        });

        thread1.start();
        thread2.start();
    }


    public static void test2(){
        List<Integer> list = new ArrayList<>();

        thread1 = new Thread(() -> {
            for(int i=1;i<=10;i++){
                list.add(i);
                System.out.println("我是线程1，我正在加 "+ i +" 到list");
            }

        });

        thread2 = new Thread(() -> {
            while(true){
                if(list.size() == 5){
                    System.out.println("我是线程2，监听到list当前大小为：：：" + list.size());
                }
            }

        });
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {

        test2();

    }
}
