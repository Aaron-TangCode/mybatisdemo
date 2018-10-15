package com.tanghailin.binarytree;

import javax.sound.midi.Soundbank;

/**
 * @author Aaron
 * @date 2018/10/14 下午8:36
 * @function 把两个单调递增的链表，合并成一个单调不减的链表
 */
public class MergeList {
    public static class TreeNode{
        int value;
        TreeNode next;
        public TreeNode(int value){
            this.value = value;
        }
    }

    public static TreeNode recursive(TreeNode node1,TreeNode node2){
        if(node1==null){
            return node2;
        }

        if (node2==null){
            return node1;
        }
        TreeNode head = null;
        if (node1.value<=node2.value){
            head = node1;
            head.next = recursive(node1.next,node2);
        }else {
            head = node2;
            head.next = recursive(node2.next,node1);
        }

        return head;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);

        node1.next = node3;
        node3.next = node4;
        node4.next = node6;
        node6.next = node10;

        node2.next = node5;
        node5.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node11;

        TreeNode head = recursive(node1,node2);
        while (head!=null){
            System.out.println(head.value);
            head = head.next;
        }
    }
}
