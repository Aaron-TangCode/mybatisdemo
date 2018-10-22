package com.tanghailin.linkedlist;

/**
 * @author Aaron
 * @date 2018/10/22 下午6:06
 * @function
 */
public class TestKNode {
    public static class Node{
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    //第一个指针向前走k-1步后，第二个指针才开始走；然后知道第一个指针走到尽头后，第二个指针指着的节点就是倒数第k个节点
    public static Node findKthToTail(Node head,int k){
        //如果head为null，或者k得值为0，返回Null
        if(head==null||k==0){
            return null;
        }
        Node ahead = head;
        //如果节点数少于k，那么倒数第k个数，也是null
        for (int i = 0;i<k;i++){
            if(ahead!=null){
                ahead = ahead.next;
            }else{
                return null;
            }
        }

        Node behind = head;

        while(ahead!=null){
            ahead = ahead.next;
            behind = behind.next;
        }

        return behind;
    }
}
