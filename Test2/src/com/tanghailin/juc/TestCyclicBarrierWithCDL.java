package com.tanghailin.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aaron
 * @date 2018/10/22 下午3:39
 * @function 用CountDownLatch实现CyclicBarrier
 */
public class TestCyclicBarrierWithCDL {
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(4);
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4,10,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());

    private static class GoHead extends Thread{
        private final String name;
        public GoHead(String name){
            this.name = name;
        }
        public void  run(){
            System.out.println(name+"开始出发");
            COUNT_DOWN_LATCH.countDown();

            try {
                Thread.sleep(1000);
                COUNT_DOWN_LATCH.await();
                System.out.println(name+"从楼底下出发");
                Thread.sleep(1000);
                System.out.println(name+"到达操场");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] str = {"A","B","C","D"};
        for (int i=0;i<4;i++){
            THREAD_POOL_EXECUTOR.execute(new GoHead(str[i]));
        }
        try {
            Thread.sleep(4000);
            System.out.println("四个人一起到达球场，现在开始打球");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
