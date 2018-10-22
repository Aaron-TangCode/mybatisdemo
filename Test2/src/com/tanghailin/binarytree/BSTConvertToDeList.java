package com.tanghailin.binarytree;

/**
 * @author Aaron
 * @date 2018/10/16 下午2:25
 * @function BST转换为一个双向链表
 */
public class BSTConvertToDeList {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode convert(TreeNode root){
        if(root==null){
            return null;
        }
        if(root.left!=null){
            //先转换左子树，获得转换后的头指针
            TreeNode leftHead = convert(root.left);
            // 获得指向左子树的最后一个元素的指针
            while (leftHead.right!=null){
                leftHead = leftHead.right;
            }
            // 与root链接，注意双向
            root.left = leftHead;
            leftHead.right = root;
        }

        if (root.right!=null){
            //先转换右子树，获得转换后的头指针
            TreeNode rightHead = convert(root.right);
            //获得指向右子树的最后一个元素的指针
            while (rightHead.left!=null){
                rightHead = rightHead.left;
            }
            //与root连接，注意双向
            rightHead.left = root;
            root.right = rightHead;
        }

        //找出双向链表的头指针（左子树的最左边）
        while (root.left!=null){
            root = root.left;
        }
        //返回双向链表的头指针
        return root;
    }
}
