package com.tanghailin.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Aaron
 * @date 2018/10/21 上午10:27
 * @function 测试CountDownLatch的用法
 * 用法一：一个线程等待n个线程执行之后，再执行
 * 用法二：n个线程并行执行，注意不是并发执行。
 */
public class TestCountDownLatch {
    static int threadCount = 10;
    public static void main(String[] args) {
        test2();
    }

    /**
     * 用法一
     */
    public static void test1(){
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        //创建线程
        for(int i=0;i<threadCount;i++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+Thread.currentThread().getId()+"开始执行");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getId()+"执行结束");

                    countDownLatch.countDown();//threadCount减1
                }
            }).start();
        }
        try {
            countDownLatch.await();//直到threadCount等于0的时候才开始继续执行

            System.out.println("所有线程执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //用法二：
    public static void test2(){
        CountDownLatch runnerPrepare = new CountDownLatch(1);
        CountDownLatch runnerRun = new CountDownLatch(1);
        CountDownLatch commanderPrepare = new CountDownLatch(10);
        CountDownLatch commanderRun = new CountDownLatch(10);

        System.out.println("选手们进场");
        ExecutorService service = Executors.newCachedThreadPool();

        System.out.println("裁判员发出准备指令");

        runnerPrepare.countDown();

        for (int i=0;i<10;i++){
            final int j = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("运动员" + j + "进行准备");
                    try {
//                        runnerPrepare.await();
                        runnerPrepare.await(10, TimeUnit.MILLISECONDS);
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("运动员" + j + "准备完成"+commanderPrepare.getCount());
                        commanderPrepare.countDown();
                    }
                    try {
                        runnerRun.await();
                        long time = (long)(Math.random()*1000);
                        Thread.sleep(time);
                        System.out.println("经历了"+time+"秒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        commanderRun.countDown();
                        System.out.println("运动员"+j+"到达终点");
                    }

                }
            });
        }
        try {
            commanderPrepare.await();
            System.out.println("都准备好，裁判员发出开始指令");
            runnerRun.countDown();
            commanderRun.await();
            System.out.println("比赛结束");
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
