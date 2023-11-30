import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + "]";
		}
		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	static int V,E;
	static int[] dist;
	static List<Node>[] list;
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		list = new ArrayList[V+1];
		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to,weight));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end  = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(dist[end]);
	}
	
	
	
	static void dijkstra(int st) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		boolean[] check = new boolean[V+1];
		que.add(new Node(st,0));
		dist[st] = 0;
		while(!que.isEmpty()) {
			Node temp = que.poll();
			int cur = temp.vertex;
			if(check[cur])continue;
			check[cur] = true;
			for(Node node:list[cur]) {
				if(dist[node.vertex] > dist[cur] + node.weight) {
					dist[node.vertex] = dist[cur] + node.weight;
					que.add(new Node(node.vertex,dist[node.vertex]));
				}
			}
		}
	}

}