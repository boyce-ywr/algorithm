package me.boyce.algr.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 递归转非递归的实现
 * 
 * @author boyce
 * @date 2014-3-12 上午11:38:32
 * 
 */
public class RecursiveImpl {

	/**
	 * 二叉树节点
	 * 
	 * @author boyce
	 * @date 2014-3-12 上午10:13:43
	 * 
	 */
	class Node {
		private int data;
		private Node left = null;
		private Node right = null;

		public Node(int data) {
			super();
			this.data = data;
		}

		public Node(int data, Node left, Node right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public boolean hasLeft() {
			return left != null;
		}

		public boolean hasRight() {
			return right != null;
		}

		@Override
		public String toString() {
			return String.valueOf(this.data);
		}

	}

	public void preOrderRecursive(Node nd) {
		if (nd == null)
			return;
		System.out.print(nd + ",");
		preOrderRecursive(nd.left);
		preOrderRecursive(nd.right);
	}

	public void inOrderRecursive(Node nd) {
		if (nd == null)
			return;
		inOrderRecursive(nd.left);
		System.out.print(nd + ",");
		inOrderRecursive(nd.right);
	}

	public void postOrderRecursive(Node nd) {
		if (nd == null)
			return;
		postOrderRecursive(nd.left);
		postOrderRecursive(nd.right);
		System.out.print(nd + ",");
	}

	/**
	 * 前序遍历非递归
	 * 
	 * @param root
	 */
	public void preOrderNonRecursive(Node root) {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.print(cur + ",");
			// 先序遍历的顺序是根，左，右。
			// 由于栈的先入后出的特性，因此先插入右孩子，后插入左孩子，能保证取出来的时候是先左后右
			if (cur.hasRight()) {
				stack.push(cur.right);
			}
			if (cur.hasLeft()) {
				stack.push(cur.left);
			}
		}
	}

	/**
	 * 中序遍历的非递归实现
	 * 
	 * @param root
	 */
	public void inOrderNonRecursive(Node root) {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.peek();
			while (cur.hasLeft()) {
				cur = cur.left;
				stack.push(cur);
			}
			while (!stack.isEmpty()) {
				cur = stack.pop();
				System.out.print(cur + ",");
				if (cur.hasRight())
					break;
			}
			if (cur.hasRight())
				stack.push(cur.right);
		}
	}

	/**
	 * 后序遍历的非递归实现
	 * 
	 * @param root
	 */
	public void postOrderNonRecursive(Node root) {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		do {
			Node cur = stack.peek();
			if (cur.hasRight()) {
				stack.push(cur.right);
			}
			if (cur.hasLeft()) {
				stack.push(cur.left);
			}
			if (!cur.hasLeft() && !cur.hasRight()) {
				cur = stack.pop();
				System.out.print(cur + ",");
				// 如果已经到顶了，结束
				if (stack.isEmpty())
					break;
				// 如果栈顶的元素是父节点，则表示没有兄弟节点，直接输出父节点
				while (!stack.isEmpty()
						&& (stack.peek().left == cur || stack.peek().right == cur)) {
					cur = stack.pop();
					System.out.print(cur + ",");
				}
			}
		} while (!stack.isEmpty());
	}

	private static <T> void swap(T[] arr, int i, int j) {
		if (i == j)
			return;
		T tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * 递归打印全排列
	 * 
	 * @param arr
	 */
	public void fullPermutationRecersive(Character[] arr, int from) {
		if (from >= arr.length) {
			for (Character c : arr) {
				System.out.print(c + ",");
			}
			System.out.println();
		}
		for (int i = from; i < arr.length; i++) {
			swap(arr, i, from);
			fullPermutationRecersive(arr, from + 1);
			swap(arr, i, from);
		}
	}

	/**
	 * 非递归打印全排列
	 * 
	 * @param set
	 */
	public void fullPermutationNonRecersive(Character[] arr) {
		int[] statusArr = new int[arr.length];
		int maxStatus = arr.length - 1;
		int p = maxStatus;
		for (int i = 0; i < arr.length; i++) {
			statusArr[i] = 0;
		}
		do {
			if (p == arr.length - 1) {
				for (Character c : arr) {
					System.out.print(c + ",");
				}
				System.out.println();
			}
			int status = statusArr[p];
			// 还可以往下，则继续往下
			if (status <= maxStatus - p && p < arr.length - 1) {
				// 交互位置
				swap(arr, p, p + statusArr[p]);
				p++;
			}
			// 否则无法往下，尝试回退
			else {
				while (true) {
					// 当前级别重置
					statusArr[p] = 0;
					// 回退上一级
					p--;
					if (p >= 0) {
						// 回上一级之后，需要先交换回来
						swap(arr, p, p + statusArr[p]);
						// 上一级更新状态
						if (statusArr[p] < maxStatus - p) {
							statusArr[p] = statusArr[p] + 1;
							break;
						}
					} else {
						break;
					}
				}
			}
		} while (p >= 0 && statusArr[p] <= maxStatus);
	}

	/**
	 * 递归打印笛卡尔积
	 * 
	 * @param lst
	 */
	public void descartesRecursive(List<Character[]> lst, int from, char[] buf) {
		if (from == lst.size()) {
			System.out.print("(");
			for (int i = 0; i < from - 1; i++) {
				System.out.print(buf[i] + ",");
			}
			System.out.println(buf[from - 1] + ")");
			return;
		}
		for (Character c : lst.get(from)) {
			buf[from] = c;
			descartesRecursive(lst, from + 1, buf);
		}
	}

	/**
	 * 非递归打印笛卡尔积
	 * 
	 * @param lst
	 */
	public void descartesNonRecursive(List<Character[]> lst) {
		int[] statusArr = new int[lst.size()];
		int p = lst.size() - 1;
		for (int i = 0; i < lst.size(); i++) {
			statusArr[i] = 0;
		}
		do {
			if (p == lst.size() - 1) {
				Character[] t = lst.get(lst.size() - 1);
				StringBuilder sb = new StringBuilder();
				sb.append("(");
				int i = 0;
				for (; i < lst.size() - 1; i++) {
					sb.append(lst.get(i)[statusArr[i]] + ",");
				}
				for (Character c : t) {
					System.out.println(sb.toString() + c + ")");
				}
			}
			// 还可以往下，则继续往下
			if (statusArr[p] <= lst.get(p).length - 1 && p < lst.size() - 1) {
				p++;
			}
			// 否则无法往下，尝试回退
			else {
				while (true) {
					// 当前级别重置
					statusArr[p] = 0;
					// 回退上一级
					p--;
					if (p >= 0) {
						// 上一级更新状态
						if (statusArr[p] < lst.get(p).length - 1) {
							statusArr[p] = statusArr[p] + 1;
							break;
						}
					} else {
						break;
					}
				}
			}
		} while (p >= 0 && statusArr[p] <= lst.get(p).length - 1);
	}

	public Node makeTree() {
		Node n8 = new Node(8);
		Node n4 = new Node(4);
		Node n5 = new Node(5, n8, null);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n3 = new Node(3, n6, n7);
		Node n2 = new Node(2, n4, n5);
		Node n1 = new Node(1, n2, n3);
		return n1;
	}

	public static void main(String[] args) {
		RecursiveImpl ps = new RecursiveImpl();
		Node root = ps.makeTree();
		ps.preOrderRecursive(root);
		System.out.println();
		ps.inOrderRecursive(root);
		System.out.println();
		ps.postOrderRecursive(root);
		System.out.println();
		System.out.println();
		ps.preOrderNonRecursive(root);
		System.out.println();
		ps.inOrderNonRecursive(root);
		System.out.println();
		ps.postOrderNonRecursive(root);
		System.out.println();
		System.out.println();

		Character[] arr = new Character[] { 'a', 'b', 'c', 'd' };
		ps.fullPermutationRecersive(arr, 0);
		System.out.println();
		ps.fullPermutationNonRecersive(arr);
		System.out.println();

		List<Character[]> lst = new ArrayList<>();
		lst.add(new Character[] { '1', '2', '3' });
		lst.add(new Character[] { 'a', 'b', 'c' });
		lst.add(new Character[] { '@', '#', '$' });
		ps.descartesRecursive(lst, 0, new char[3]);
		System.out.println();
		ps.descartesNonRecursive(lst);
	}
}
