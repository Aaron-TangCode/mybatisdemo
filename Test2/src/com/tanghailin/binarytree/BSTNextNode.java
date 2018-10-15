package com.tanghailin.binarytree;

public class BSTNextNode {

    private class BinaryNode{
        int ele;
        BinaryNode left;
        BinaryNode right;
        BinaryNode parent;
        int hash;//cache hashCode
        
        public BinaryNode(int ele) {
            this.ele = ele;
            parent = left = right = null;
        }
        
        @Override
        public boolean equals(Object obj) {
            if(obj == null)
                return false;
            if(!(obj instanceof BinaryNode))
                return false;
            BinaryNode node = (BinaryNode)obj;
            return node.ele == this.ele;
        }
        
        @Override
        public int hashCode() {// 参考《effective java》中覆盖equals方法
            int result = hash;
            if(result == 0){
                result = 17;
                result = result*31 + ele;
                hash = result;
            }
            return result;
        }
        
        @Override
        public String toString() {
            return ele + " "; 
        }
    }
    
    private BinaryNode root;
    
    public void buildTree(int[] eles){
        if(eles == null || eles.length == 0)
            return;
        
        for (int ele : eles) {
            insert(ele);
        }
    }
    private void insert(int ele){
        root = insert(root, ele);
        root.parent = null;
    }
    private BinaryNode insert(BinaryNode root, int ele){
        if(root == null)
            return new BinaryNode(ele);
        if(root.ele > ele){
            root.left = insert(root.left, ele);
            root.left.parent = root;
        }else{
            root.right = insert(root.right, ele);
            root.right.parent = root;
        }
        return root;
    }
    
    //寻找值为ele的那个结点的 下一个结点
    public BinaryNode nextNode(int ele){
        BinaryNode node = find(ele);//找到Node值为ele的结点在BST中的位置
        if(node == null)
            throw new IllegalArgumentException(ele + " not in tree");
        BinaryNode nextNode = null;
        if(node.right != null)
        {
            BinaryNode current = node.right;
            while(current.left != null)
            {
                current = current.left;
            }
            nextNode = current;
        }else if(node.parent != null){//node结点 是 parent 的孩子
            if(node.parent.left != null && node.parent.left.equals(node))// node 是 parent 的左孩子
                nextNode = node.parent;
            else{//node 是 parent的右孩子
                BinaryNode current = node.parent;
                //一直往着右孩子的父结点指针向上走
                while(current.parent.right != null && current.parent.right.equals(current))
                {
                    current = current.parent;
                }
                nextNode = current.parent;
            }
        }else{//node 没有父结点.那它就是BST中的最大的结点---此时它的下一个结点视为null
//            nextNode = node;
            ;
        }
        return nextNode;
    }
    
    //查找二叉树中值为ele的结点,并返回该结点
    private BinaryNode find(int ele){
        if(root == null)
            throw new IllegalStateException("bst is null");
        return find(root, ele);
    }
    
    //采用先序遍历查找值为ele的结点
    private BinaryNode find(BinaryNode root, int ele){
        if(root == null)
            return null;
        if(root.ele == ele)
            return root;
        BinaryNode target = null;
        target = find(root.left, ele);
        if(target == null)//如果左子树中没有值为ele的结点,if成立,在右子树中查找
            target = find(root.right, ele);
        return target;
    }
    
    
    //hapjin test
    public static void main(String[] args) {
        int[] eles = {20,10,30,15,18,26,22,8,40};
        int ele = 20;
//        int[] eles = {20,10,15};
        BSTNextNode bstTree = new BSTNextNode();
        bstTree.buildTree(eles);//构造一棵二叉树查找树
        BinaryNode next = bstTree.nextNode(ele);//查找值为ele结点的下一个结点
        System.out.println(next);
    }
}