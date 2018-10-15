package com.tanghailin.binarytree;

import jdk.nashorn.internal.ir.BinaryNode;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * 
 * 功能：
 * 前序遍历
 * 中序遍历
 * 后序遍历
 * 通过一个数组来创建一颗二叉树
 * DFS
 * BFS
 * @author aaron
 *
 */

public class CreateTheBinaryTree {
	public static class Node{
		Node left;
		Node right;
		Node parent;
		int value;
		public Node(int value) {
			super();
			this.value = value;
		}
		
	}
	static TreeSet<Integer> max = new TreeSet<>();//大顶堆，用于存放前面一半的元素，比较小的数
	static TreeSet<Integer> min = new TreeSet<>();//小顶堆，用于存放后面一半的元素，比较大的数
	private static Node root;
	/**
	 * 通过一个数组来创建一颗二叉树
	 * 数组中遇到0，就为空，不创建节点
	 * @param arr
	 * @param index
	 * @return
	 */
	public static Node createBinaryTreeByArray(int[] arr,int index) {
		if(index<arr.length) {
			int value = arr[index];
			if(value!=0) {
				Node node = new Node(value);
				node.left = createBinaryTreeByArray(arr, index*2);
				node.right = createBinaryTreeByArray(arr, index*2+1);
				return node;
			}
		}
		return null;
	}
	/**
	 * 先序遍历：中-左-右
	 * @param root
	 */
	public static void preSearch(Node root) {
		if(root==null) {
			return;
		}
		System.out.print(root.value+" ");
		preSearch(root.left);
		preSearch(root.right);
	}
	/**
	 * 中序遍历：左-中-右
	 * @param root
	 */
	public static void midSearch(Node root) {
		if(root==null) {
			return;
		}
		midSearch(root.left);
		System.out.print(root.value+" ");
		midSearch(root.right);
	}
	/**
	 * 后序遍历：左-右-中
	 * @param root
	 */
	public static void posSearch(Node root) {
		if(root==null) {
			return;
		}
		posSearch(root.left);
		posSearch(root.right);
		System.out.print(root.value+" ");
	}
	/**
	 * DFS:深度优先遍历，用栈来实现,
	 * DFS:和先序遍历一样
	 * @param root
	 */
	public static void DFS(Node root) {
		if(root==null) {
			return;
		}
		
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while(stack.isEmpty()==false) {
			Node cur = stack.pop();
			System.out.print(cur.value+" ");
			if(cur.right!=null) {
				
				stack.push(cur.right);
			}
			if(cur.left!=null) {
				stack.push(cur.left);
			}
		}
	}
	/**
	 * BFS广度优先遍历
	 * @param root
	 */
	public static void BFS(Node root) {
		if(root==null) {
			return;
		}
		LinkedList<Node> queue = new LinkedList<>();
		
		queue.add(root);
		while(queue.isEmpty()==false) {
			Node cur = queue.remove();
			System.out.print(cur.value+" ");
			if(cur.left!=null) {
				queue.add(cur.left);
			}
			if(cur.right!=null) {
				queue.add(cur.right);
			}
		}
	}
	/**
	 * 二叉树的最大深度
	 * @param root
	 * @return
	 */
	public static int maxDepth(Node root) {
		if(root==null) {
			return 0;
		}
//		int leftMaxDepth = maxDepth(root.left)+1;
//		int rightMaxDepth = maxDepth(root.right)+1;
//		
//		return Math.max(leftMaxDepth, rightMaxDepth);
		return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
	}
	/**
	 * 二叉树的最小深度
	 * @param root
	 * @return
	 */
	public static int minDepth(Node root) {
		if(root==null) {
			return 0;
		}
		if(root.left==null) {
			return minDepth(root.right)+1;
		}
		if(root.right==null) {
			return minDepth(root.left)+1;
		}
		int leftMinDepth = minDepth(root.left)+1;
		int rightMinDepth = minDepth(root.right)+1;
		
		return Math.min(leftMinDepth, rightMinDepth);
	}
	/**
	 * 判断一棵树是否是平衡二叉树
	 * @param root
	 * @return
	 */
	public static boolean balanceTree(Node root) {
		if(root==null) {
			return true;
		}else if(Math.abs(maxDepth(root.left)-maxDepth(root.right))>1) {
			return false;
		}else {
			return balanceTree(root.left)&&balanceTree(root.right);
		}
		
	}
	/**
	 * 二叉树的翻转
	 * @param root
	 */
	public static Node reverse(Node root) {
		if(root==null) {
			return null;
		}
		Node tmp = root.left;
		root.left = reverse(root.right);
		root.right = reverse(tmp);
		return root;
	}

	/**
	 * 寻找指定节点的下一个节点
	 * @param root
	 * @return
	 */
	public static Node nextNode(Node root){
		//空节点
		if (root==null){
			return null;
		}
		//该节点有右孩子，就找右孩子的最左孩子
		if (root.right!=null){
			Node cur = root.right;
			while (cur.left!=null){
				cur = cur.left;
			}
			return cur;
		}
		//该节点没右孩子，但是有父节点
		while (root.parent!=null){
			//该节点是父节点的左孩子
			if (root.parent.left==root){
				return root.parent;
			}
			//该节点是父节点的右孩子
			root = root.parent;
		}
		return null;
	}

	/**
	 * 判断二叉树是否是对称二叉树
	 * 对称二叉树，要值相等，要镜像相等
	 * 如果只有一个节点，那就对称
	 * 如果左右孩子只有其中一个，返回false
	 * 如果左右孩子都有，判断值是否相等
	 * 遍历左子树，先遍历左孩子，后遍历右孩子
	 * 遍历右子树，先遍历右孩子，后遍历左孩子
	 * @param root
	 * @return
	 */
	public static boolean isSymmetrical(Node root){
		if (root==null){
			return true;
		}
		return isSymmetrical(root.left,root.right);
	}
	public static boolean isSymmetrical(Node left,Node right){
		if (left==null&&right==null){
			return true;
		}
		if (left==null){
			return false;
		}
		if (right==null){
			return false;
		}
		return (left.value==right.value)&&isSymmetrical(left.left,right.right)&&isSymmetrical(left.right,right.left);
	}

	/**
	 * 按之字形打印二叉树
	 * 偶数层：用栈先存右子树，后存左子树
	 * 奇数层：用栈先存左子树，后存右子树
	 * @param root
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> zhiZiXingPrint(Node root) {
		int level = 1;
		//奇数栈
		Stack<Node> s1 = new Stack<>();
		//偶数栈
		Stack<Node> s2 = new Stack<>();

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		s1.push(root);
		while (!s1.isEmpty()||!s2.isEmpty()){
			//奇数行
			if(level%2!=0){
				ArrayList<Integer> tmp = new ArrayList<>();
				while (!s1.isEmpty()){
					Node cur = s1.pop();
					//栈：先进后出，因为要先打印右子树，所以先存左子树
					if (cur!=null){
						tmp.add(cur.value);
						s2.push(cur.left);
						s2.push(cur.right);
					}

				}
				if(!tmp.isEmpty()){
					list.add(tmp);
					level++;
				}
			}else{//偶数行
				ArrayList<Integer> tmp = new ArrayList<>();
				while (!s2.isEmpty()){
					Node cur = s2.pop();
					if (cur!=null){
						tmp.add(cur.value);
						s1.push(cur.right);
						s1.push(cur.left);
					}

				}
				if (!tmp.isEmpty()){
					list.add(tmp);
					level++;
				}
			}
		}
		return list;
	}

	/**
	 * 把二叉树打印成多行
	 * @param root
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> printMultiRow(Node root) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		int size = queue.size();//第n次的size(n=1)
		int count = 0;//第n次的count(n=1)
		while (!queue.isEmpty()){
			ArrayList<Integer> tmp = new ArrayList<>();
			size = queue.size();//第n次的size(n>1)
			count = 0;//第n次的count(n>1)
			while (count<size){
				Node cur = queue.poll();
				count++;
				tmp.add(cur.value);
				if (cur.left!=null){
					queue.add(cur.left);
				}
				if (cur.right!=null){
					queue.add(cur.right);
				}

			}
			list.add(tmp);

		}
		return list;
	}

	/**
	 * 以前序遍历的方式去序列化二叉树
	 * @param root
	 * @return
	 */
	public static String serializable(Node root){
		if(root==null){
			return "#,";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(root.value+",");
		sb.append(serializable(root.left));
		sb.append(serializable(root.right));
		return sb.toString();
	}

	/**
	 * 以前序遍历的方式去反序列化二叉树
	 * @param str
	 * @return
	 */
	public static Node deSerializable(String str){
		String[] arr = str.split(",");
		LinkedList<String> queue = new LinkedList<>();
		for (int i=0;i<arr.length;i++){
			queue.add(arr[i]);
		}
		return preOrder(queue);
	}
	public static Node preOrder(LinkedList<String> queue){
		//把值读取出来
		String val = queue.poll();
		//如果值等于#，就是null
		if (val.equals("#")){
			return null;
		}
		Node node  = new Node(Integer.parseInt(val));
		//遍历左子树
		node.left = preOrder(queue);
		//遍历右子树
		node.right = preOrder(queue);
		return node;
	}

	/**
	 * 搜索二叉树的第K个节点
	 * @param root
	 * @param k
	 * @return
	 */
	public static Node kthNode(Node root,int k){
		if(root==null){
			return null;
		}
		//创建一个双向队列
		List<Node> queue = new ArrayList<>();
		//中序遍历搜索二叉树,暂存在队列中
		inOrder(root,queue);
		if (k<=queue.size()&&k>0){
			return queue.get(k-1);
		}else {
			return null;
		}



	}

	/**
	 * 中序遍历
	 * @param root
	 * @param queue
	 */
	public static void inOrder(Node root,List<Node> queue){
		if (root==null){
			return;
		}
		if (root.left!=null){
			inOrder(root.left,queue);
		}
		queue.add(root);
		if (root.right!=null){
			inOrder(root.right,queue);
		}
	}

	/**
	 * 用两个堆保存数据，保持两个堆的数据保持平衡（元素个数相差不超过1）
	 * 大顶堆存放的数据要比小顶堆的数据小
	 * 当两个堆中的元素为偶数个时，将新的元素加入到大顶堆，如果加入的数据，比小顶堆的最小元素大，
	 * 先将该元素插入小顶堆，然后将小顶堆的最小元素插入到大顶堆。
	 * 当两个堆红的元素为奇数个时，将新的元素加入到小顶堆，如果加入的数据，比大顶堆的最大元素小，
	 * 先将该元素插入大顶堆，然后将大顶堆的最大属于插入到小顶堆。
	 * @param num
	 */
	public static void insert(Integer num){
		if((max.size()+min.size())%2==0){//小顶堆和大顶堆的元素和是偶数
			if(min.size()>0&&num>min.first()){
				min.add(num);
				num = min.first();
				min.remove(num);
			}
			max.add(num);
		}else{//小顶堆和大顶堆的元素和是奇数
			if(max.size()>0&&num<max.last()){
				max.add(num);
				num = max.last();
				max.remove(num);
			}
			min.add(num);
		}
	}

	/**
	 * 当两个堆的元素和为奇数时，中位数在大顶堆的堆顶；
	 * 当两个堆的元素和为偶数时，中位数在大顶堆的堆顶和小顶堆的堆顶元素之和除以2
	 * @return
	 */
	public static Double getMedian(){
		int size = max.size()+min.size();
		if(size==0){
			return 0.0;
		}
		if (size%2==0){
			return (max.last()+min.first())/2.0;
		}else{
			return (double)max.last();
		}
	}
	/** 
     *                  13
     *                 /  \
     *               65    5
     *              /  \    \
     *             97  25   37
     *            /    /\   /
     *           22   4 28 32
     */
	public static void main(String[] args) {
		int[] arr = new int[] {0,13,65,5,97,25,0,37,22,0,4,28,0,0,32,0};
//		int[] arr = new int[] {0,13,65,5,0};
		Node root = createBinaryTreeByArray(arr, 1);
		System.out.print("先序遍历："+"  ");
		preSearch(root);
		System.out.println();
		System.out.print("中序遍历："+"  ");
		midSearch(root);
		System.out.println();
		System.out.print("后序遍历："+"  ");
		posSearch(root);
		System.out.println();
		System.out.print("DFS："+"  ");
		DFS(root);
		System.out.println();
		System.out.print("BFS："+"  ");
		BFS(root);
		System.out.println();
		System.out.print("树的最大深度："+maxDepth(root));
		System.out.println();
		System.out.print("树的最小深度："+minDepth(root));
		System.out.println();
		System.out.print("该树是否是平衡二叉树" + balanceTree(root));
		System.out.println();
		System.out.print("二叉树翻转");
//		reverse(root);
		System.out.println("先序遍历：");
		preSearch(root);
		System.out.println();
		System.out.print("是不是对称的二叉树"+isSymmetrical(root));
		System.out.println();
		System.out.print("按之字型打印二叉树--方式1：");
		ArrayList<ArrayList<Integer>> list = zhiZiXingPrint(root);
		for (ArrayList<Integer> l:list) {
			for (Integer i:l){
				System.out.print(i+" ");
			}
		}
		System.out.println();
		System.out.print("把二叉树打印成多行");
		ArrayList<ArrayList<Integer>> list2 = printMultiRow(root);
		for (ArrayList<Integer> l:list2) {
			for (Integer i:l){
				System.out.print(i+" ");
			}
		}
		System.out.println();
		System.out.print("以前序遍历的方式序列化二叉树：");
		System.out.println(serializable(root));
		System.out.print("以前序遍历的方式反序列化二叉树：");
		System.out.println(deSerializable(serializable(root)));
		System.out.print("二叉树搜索树的第K个节点");
		Node node = kthNode(root,1);
		System.out.println(node.value);
	}
}
