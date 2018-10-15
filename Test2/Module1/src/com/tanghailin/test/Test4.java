package com.tanghailin.test;

/**
 * @author Aaron
 * @date 2018/9/25 下午9:28
 */
public class Test4 {
//    模板4
    private static final int num1 = 1;
    public static final int num2 = 2;
    public static final String str = "wer";
    public static final int num3 = 3;
    //    模板1 psvm
    public static void main(String[] args) {
//        模板2 sout
        System.out.println("模板2");
//        模板2变形 soutp
        System.out.println("args = [" + args + "]");
//         模板2变形 soutm
        System.out.println("Test4.main");
//        模板2变形 soutv
       int num1 = 1;
        System.out.println("num1 = " + num1);
        int num2 = 1;
        System.out.println("num2 = " + num2);
        String str = "qwe";
        System.out.println(str);
//        模板3   fori
        String[] s = new String[]{"q","1","qwe"};
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
//        模板3 iter
        for (String s1 : s) {
            System.out.println(s1);
        }
//        模板3 itar
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            System.out.println("s1 = " + s1);
        }
    }
    public static void test1(){
        System.out.println("Test4.test1");
    }
}
