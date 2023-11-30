package BAEK_1991_트리순회;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	// 노드 개수
	static int N;
	// 루트노드 만들어주기
	static Node head = new Node('A', null, null);

	// 노드 클래스
	static class Node {
		// 현재 노드
		char value;
		// 왼쪽 자식 노드
		Node left;
		// 오른쪽 자식 노드
		Node right;

		public Node(char value, Node left, Node right) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 노드의 개수
		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			char root = sc.next().charAt(0);
			char left = sc.next().charAt(0);
			char right = sc.next().charAt(0);

			insertNode(head, root, left, right);
		}

		preorder(head);
		System.out.println();
		inorder(head);
		System.out.println();
		postorder(head);
		System.out.println();

	}

	private static void insertNode(Node tmp, char root, char left, char right) {
		// 루트 노드(A)라면
		if (tmp.value == root) {
			// 자식 노드가 . 이면 null, 아니라면 새로운 노드 만들어주자
			if (left == '.') {
				tmp.left = null;
			} else {
				tmp.left = new Node(left, null, null);
			}
			if (right == '.') {
				tmp.right = null;
			} else {
				tmp.right = new Node(right, null, null);
			}
		}
		// 루트 노드가 아니라면 루트노드 찾자
		else {
			// tmp의 자식 노드가 null이 아니라면 노드를 넣어주자
			// 재귀를 타면서 루트노드를 찾아주는 과정이다.
			if (tmp.left != null) {
				insertNode(tmp.left, root, left, right);
			}
			if (tmp.right != null) {
				insertNode(tmp.right, root, left, right);
			}
		}
	}
	// main

	// 전위순회(VLR)
	public static void preorder(Node node) {
		// 노드가 null 이면 빠져나와
		if (node == null)
			return;

		// V출력
		System.out.print(node.value);
		// 왼쪽 자식 노드 재귀 L
		preorder(node.left);
		// 오른쪽 자식 노드 재귀 R
		preorder(node.right);

	}
	// predorder

	// 중위순회(LVR)
	public static void inorder(Node node) {
		// 노드가 null 이면 빠져나와
		if (node == null)
			return;
		//왼쪽 자식 노드 재귀 L
		inorder(node.left);
		// V
		System.out.print(node.value);
		//오른쪽 자식 노드 재귀 R
		inorder(node.right);
	}
	// inorder
	
	// 후위순회(LRV)
	public static void postorder(Node node) {
		if(node == null) return;
		//L
		postorder(node.left);
		//R
		postorder(node.right);
		//V
		System.out.print(node.value);
	}
}
