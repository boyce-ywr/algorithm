package me.boyce.algr.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * �ݹ�ת�ǵݹ��ʵ��
 * 
 * @author boyce
 * @date 2014-3-12 ����11:38:32
 * 
 */
public class RecursiveImpl {

	/**
	 * �������ڵ�
	 * 
	 * @author boyce
	 * @date 2014-3-12 ����10:13:43
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
	 * ǰ������ǵݹ�
	 * 
	 * @param root
	 */
	public void preOrderNonRecursive(Node root) {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.print(cur + ",");
			// ���������˳���Ǹ������ҡ�
			// ����ջ�������������ԣ�����Ȳ����Һ��ӣ���������ӣ��ܱ�֤ȡ������ʱ�����������
			if (cur.hasRight()) {
				stack.push(cur.right);
			}
			if (cur.hasLeft()) {
				stack.push(cur.left);
			}
		}
	}

	/**
	 * ��������ķǵݹ�ʵ��
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
	 * ��������ķǵݹ�ʵ��
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
				// ����Ѿ������ˣ�����
				if (stack.isEmpty())
					break;
				// ���ջ����Ԫ���Ǹ��ڵ㣬���ʾû���ֵܽڵ㣬ֱ��������ڵ�
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
	 * �ݹ��ӡȫ����
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
	 * �ǵݹ��ӡȫ����
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
			// ���������£����������
			if (status <= maxStatus - p && p < arr.length - 1) {
				// ����λ��
				swap(arr, p, p + statusArr[p]);
				p++;
			}
			// �����޷����£����Ի���
			else {
				while (true) {
					// ��ǰ��������
					statusArr[p] = 0;
					// ������һ��
					p--;
					if (p >= 0) {
						// ����һ��֮����Ҫ�Ƚ�������
						swap(arr, p, p + statusArr[p]);
						// ��һ������״̬
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
	 * �ݹ��ӡ�ѿ�����
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
	 * �ǵݹ��ӡ�ѿ�����
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
			// ���������£����������
			if (statusArr[p] <= lst.get(p).length - 1 && p < lst.size() - 1) {
				p++;
			}
			// �����޷����£����Ի���
			else {
				while (true) {
					// ��ǰ��������
					statusArr[p] = 0;
					// ������һ��
					p--;
					if (p >= 0) {
						// ��һ������״̬
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
