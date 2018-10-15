package com.tanghailin.binarytree;


/**
 * @author Aaron
 * @date 2018/10/15 下午8:31
 * @function A、B是两个树，判断B是不是A的子树
 */
public class HasSubTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root2==null) {        //空树不是任意一个树的子结构
            return false;
        }
        if (root1==null) {        //如果A为空，那肯定返回false
            return false;
        }
        if(root2.val==root1.val){        //A和B比较的根节点的值相同

            if (root2.left==null&&root2.right==null) {        //比较的节点是B的尾节点，递归截止
                return true;
            }
            //下面三种比较的是比较的节点完全相同的情况
            if ((root2.left!=null&&root2.right!=null)&&(root1.left!=null&&root1.right!=null)&&root2.left.val==root1.left.val&&root2.right.val==root1.right.val) {
                return HasSubtree(root1.left,root2.left)&& HasSubtree(root1.right,root2.right);
            }else if ((root2.left!=null&&root2.right==null)&&(root1.left!=null&&root1.right==null)&&root2.left.val==root1.left.val) {
                return HasSubtree(root1.left,root2.left);
            }else if ((root2.left==null&&root2.right!=null)&&(root1.left==null&&root1.right!=null)&&root2.right.val==root1.right.val) {
                return HasSubtree(root1.right,root2.right);
            }else{        //比较的节点不同，A向左右子节点移动一个再比较
                if (root1.left!=null&&root1.right!=null) {
                    return  HasSubtree(root1.left,root2)|| HasSubtree(root1.right,root2);
                }else if(root1.left==null&&root1.right!=null){
                    return  HasSubtree(root1.right,root2);
                }else if(root1.left!=null&&root1.right==null){
                    return  HasSubtree(root1.left,root2);
                }else {
                    return false;
                }
            }
            //比较的根节点的值不相同，直接向左右子节点滑动
        }else if(root1.left!=null&&root1.right==null){
            return HasSubtree(root1.left,root2);
        }else if(root1.left==null&&root1.right!=null){
            return HasSubtree(root1.right,root2);
        }else{
            return false;
        }
    }
}
