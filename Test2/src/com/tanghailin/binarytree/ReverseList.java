package com.tanghailin.binarytree;

/**
 * @author Aaron
 * @date 2018/10/15 下午7:18
 * @function 翻转链表，比如1->2->3->5；变成5->3->2->1
 */
public class ReverseList {

    public static class TreeNode{
        int val;
        TreeNode next;
        public TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode reverseList(TreeNode H){
        if(H==null){
            return null;
        }
        TreeNode newH = null;//新指针
        TreeNode P = H;
        while (P!=null){
            TreeNode tmp = P.next;
            P.next = newH;
            newH = P;
            P = tmp;
        }

        return newH;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        TreeNode cp = reverseList(node1);
        while (cp!=null){
            System.out.println(cp.val);
            cp = cp.next;
        }
    }
}
