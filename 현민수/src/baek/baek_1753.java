import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int v, weight;
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static final int INF = Integer.MAX_VALUE;
	static int V, E, S, A;			
	static List<Node>[] list;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		V = Integer.parseInt(st.nextToken());			
		E = Integer.parseInt(st.nextToken());				
		st = new StringTokenizer(br.readLine()," ");
		S = Integer.parseInt(st.nextToken());				
		
		list = new ArrayList[V + 1];
		dist = new int[V + 1];
		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		//
		Arrays.fill(dist, INF);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[u].add(new Node(v, weight));
		}


		dijkstra(S);
		for(int i=1;i<V+1;i++) {
			if(dist[i]==INF) {
				System.out.println("INF");
			}
			else
				System.out.println(dist[i]);
		}
	}

	static void dijkstra(int st) {
		PriorityQueue<Node> queue = new PriorityQueue<>();

		boolean[] check = new boolean[V + 1];

		queue.add(new Node(st, 0));
		dist[st] = 0;

		while (!queue.isEmpty()) {

			Node curNode = queue.poll();
			int cur = curNode.v;
			if (check[cur] == true)
				continue;
			check[cur] = true;
			for (Node node : list[cur]) {
				if (dist[node.v] > dist[cur] + node.weight) {
					dist[node.v] = dist[cur] + node.weight;
					queue.add(new Node(node.v, dist[node.v]));
				}
			}
		}
	}
}