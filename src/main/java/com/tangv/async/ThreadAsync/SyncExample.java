package com.tangv.async.ThreadAsync;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author:   tangwei
 * Date:     2020/12/22 17:28
 * Description: 顺序执行与简单线程异步执行
 */
public class SyncExample {

    private static final int AVAILABLE_PROCESSOR = Runtime.getRuntime().availableProcessors();
    private static  final ThreadPoolExecutor EXCUTOR = new ThreadPoolExecutor(
            AVAILABLE_PROCESSOR,
            AVAILABLE_PROCESSOR*2,
            1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void doSthA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----do something A-----");
    }

    public static void dosthB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----do something B-----");
    }

    public static void main(String[] args) throws InterruptedException {
        /*long a = System.currentTimeMillis();
        doSthA();
        dosthB();
        long b = System.currentTimeMillis();
        System.out.println("顺序执行耗时："+(b-a)/1000.0);
        Thread task = new Thread(() -> {
            doSthA();
            },"dd");
        task.start();
        dosthB();
        task.join();
        System.out.println("异步耗时："+ (System.currentTimeMillis()-b)/1000.0);*/

        long c = System.currentTimeMillis();
        EXCUTOR.execute(() -> {
            doSthA();
        });
        EXCUTOR.execute(() -> {
            dosthB();
        });
        System.out.println(System.currentTimeMillis()-c);
        Thread.currentThread().join();
    }

}