package com.tanghailin.juc;

import java.util.concurrent.*;

/**
 * @author Aaron
 * @date 2018/10/22 下午3:10
 * @function 测试CyclicBarrier怎样使用
 */
public class TestCyclicBarrier {
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4,10,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());

    private static final CyclicBarrier cb = new CyclicBarrier(4, new Runnable() {
        @Override
        //唤醒所有线程后，第一个执行的线程
        public void run() {
            System.out.println("寝室四兄弟准备一起出发去球场");
        }
    });

    private static class GoThread extends Thread{
        private final String name;
        public GoThread(String name){
            this.name = name;
        }

        public void run(){
            System.out.println(name+"开始从宿舍出发");
            try {
                Thread.sleep(1000);
                cb.await();//拦截线程
                System.out.println(name+"从楼底下出发");
                Thread.sleep(1000);
                System.out.println(name+"到达操场");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        String[] str = {"小汤","小赵","小高","小吴"};
        for (int i=0;i<4;i++){
            threadPool.execute(new GoThread(str[i]));
        }
        try {
            Thread.sleep(4000);
            System.out.println("四个人一起到达球场，现在开始打球");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2(){
        String[] str = {"小汤","小高","小赵","小吴"};
        String[] str1 = {"大汤","大高","大赵","大吴"};
        for (int i=0;i<4;i++){
            threadPool.execute(new GoThread(str[i]));


        }
        try {
            Thread.sleep(4000);
            System.out.println("四个人一起到达球场，现在开始打球");
            System.out.println("现在对CyclicBarrier进行复用。。。。。。");
            System.out.println("又来了一拨人，看看愿不愿意一起打");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //进行复用
        for (int i=0;i<4;i++){
            threadPool.execute(new GoThread(str1[i]));

        }
        try {
            Thread.sleep(4000);
            System.out.println("四个人一起到达球场，表示愿意一起打球，现在八个人开始打球");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
