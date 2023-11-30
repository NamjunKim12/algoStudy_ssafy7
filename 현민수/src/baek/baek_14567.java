import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int[] degree;
	
	static class Node{
		int v;
		Node next;
		public Node(int v, Node next) {
			super();
			this.v = v;
			this.next = next;
		}
		
	}
	static int N,M;
	static Node[] adjList;
	static int[] ans;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N+1];
		degree = new int[N+1];
		ans = new int[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			degree[to]++;
			adjList[from] = new Node(to,adjList[from]);
		}
		topo();

		StringBuilder sb = new StringBuilder();
		for(int i=1;i<N+1;i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
	static void topo(){
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int cnt=1;
		
		for(int i=1;i<N+1;i++) {
			if(degree[i]==0)que.offer(new int[] {i,cnt});
		}
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			ans[cur[0]] = cur[1];
			
			for(Node temp = adjList[cur[0]]; temp != null; temp=temp.next) {
				if(--degree[temp.v]==0) {
					que.offer(new int[] {temp.v,cur[1]+1});
				}
			}
			
		}
	}

}