import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
	
	static class Node{
		int now;
		int left;
		int right;
		public Node(int now, int left, int right) {
			super();
			this.now = now;
			this.left = left;
			this.right = right;
		}
		
	}
	static List<Node>[] tree;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		for(int i=1;i<N+1;i++) {
			tree[i] = new ArrayList<>();
		}
		for(int i=1;i<N+1;i++) {
			String s = br.readLine();
			int a = s.charAt(0)-'A'+1;
			int b = s.charAt(2)-'A'+1;
			int c = s.charAt(4)-'A'+1;
			tree[a].add(new Node(a,b,c));
		}
		preorder(1);
		System.out.println();
		inorder(1);
		System.out.println();
		postorder(1);
			
		
	}
	static void preorder(int st) {
			for(Node node : tree[st]) {
				System.out.print((char)(node.now+'A'-1));
				if(node.left>0)preorder(node.left);
				if(node.right>0)preorder(node.right);
			}
			
	}
	static void inorder(int st) {
		for(Node node : tree[st]) {
			if(node.left>0)inorder(node.left);
			System.out.print((char)(node.now+'A'-1));
			if(node.right>0)inorder(node.right);
		}
		
	}
	static void postorder(int st) {
		for(Node node : tree[st]) {
			if(node.left>0)postorder(node.left);
			if(node.right>0)postorder(node.right);
			System.out.print((char)(node.now+'A'-1));
		}
		
	}
}