package com.tanghailin.binarytree;

import java.util.ArrayList;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(4);
        list1.add(5);
        list1.add(6);
        list1.add(7);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(0,4);
        list2.add(0,5);
        list2.add(0,6);
        list2.add(0,7);

        for (Integer l1:list1) {
            System.out.print(l1+" ");
        }
        System.out.println();

        for (Integer l2:
             list2) {
            System.out.print(l2+" ");
        }
        System.out.println();
    }
}
