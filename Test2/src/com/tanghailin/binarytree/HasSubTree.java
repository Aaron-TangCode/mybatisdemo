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
    public static boolean hasSubTree(TreeNode root1,TreeNode root2){
        //如果AB其中一个为null,那就返回false
        if (root1==null){
            return false;
        }
        if (root2==null){
            return false;
        }
        if (root1.val==root2.val){
            //如果AB的节点值相等，如果B没子树，那就直接返回true；
            if (root2.left==null&&root2.right==null){
                return true;
            }
            //如果AB的节点值相等，如果B有子树，那么继续比较子树

            //B有左右节点
            if ((root2.left!=null&&root2.right!=null)&&(root1.left!=null&&root1.right!=null)&&root1.left.val==root2.left.val&&root1.right.val==root2.right.val){
                return hasSubTree(root1.left,root2.left)&&hasSubTree(root1.right,root2.right);
            }else if(root2.left!=null&&root1.left!=null&&root1.left.val==root2.left.val){
                //B只有左子树
                return hasSubTree(root1.left,root2.left);
            }else if(root2.right!=null&&root1.right!=null&&root2.right.val==root1.right.val){
                //B只有右子树
                return hasSubTree(root1.right,root2.right);
            }else{
                //如果根节点不相等，那么就比较子节点
                if(root1.left!=null&&root1.right!=null){
                    return hasSubTree(root1.left,root2)||hasSubTree(root1.right,root2);
                }else if(root1.left!=null&&root1.right==null){
                    return hasSubTree(root1.left,root2);
                }else if(root1.left ==null&&root1.right!=null){
                    return hasSubTree(root1.right,root2);
                }else{
                    return false;
                }
            }
            //如果根节点的值不同，直接向左右节点移动比较
        }else if(root1.left!=null&&root1.right==null){
            return hasSubTree(root1.left,root2);
        }else if(root1.left==null&&root1.right!=null) {
            return hasSubTree(root1.right, root2);
        }else{
            return false;
        }

    }
}
