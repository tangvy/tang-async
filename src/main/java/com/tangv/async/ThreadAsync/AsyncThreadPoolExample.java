package com.tangv.async.ThreadAsync;

import java.util.concurrent.*;

/**
 * author:   tangwei
 * Date:     2020/12/22 18:16
 * Description: Future异步执行。具有返回值
 */
public class AsyncThreadPoolExample {

    private static final int AVAILABLE_PROCESSOR = Runtime.getRuntime().availableProcessors();
    private static  final ThreadPoolExecutor EXCUTOR = new ThreadPoolExecutor(
            AVAILABLE_PROCESSOR,
            AVAILABLE_PROCESSOR*2,
            1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static String doSthA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----do something A-----");
        return "task A done...";
    }

    public static String dosthB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----do something B-----");
        return "task B done...";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> resultA = EXCUTOR.submit(() -> doSthA());
        Future<String> resultB = EXCUTOR.submit(() -> dosthB());
        System.out.println(resultA.get());
        System.out.println(resultB.get());
    }

}