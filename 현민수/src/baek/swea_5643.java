import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static class Node implements Comparable<Node>{
		int v;
		int weight;
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static int N;
	static int[][] dist;
	private static final int INF = Integer.MAX_VALUE;
	static List<Node>[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1;tc<T+1;tc++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			dist = new int[N+1][N+1];
			for(int i=0;i<N+1;i++) {
				Arrays.fill(dist[i], INF);
			}
			list = new ArrayList[N + 1];
			for (int i = 0; i < N+1; i++) {
				list[i] = new ArrayList<>();
			}
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list[from].add(new Node(to, 1));
			}
			for(int i=1;i<N+1;i++) {
				dijk(i);
			}
			int ans=0;
			for(int i=1;i<N+1;i++) {
				int temp=0;
				for(int j=1;j<N+1;j++) {
					if(dist[i][j]!=0 && dist[i][j]!=INF) {
						temp++;
					}
					if(dist[j][i]!=0 && dist[j][i]!=INF) {
						temp++;
					}
				}
				if(temp==N-1) {
					ans++;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	static void dijk(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] check = new boolean[N + 1];
		queue.add(new Node(start, 0));

		dist[start][start] = 0;

		while (!queue.isEmpty()) {
			//하나 꺼내서
			Node curNode = queue.poll();
			//정점
			int cur = curNode.v;
			//이 이미 선택됐다면
			if (check[cur] == true)
				continue;
			//선택한걸로 치고
			check[cur] = true;
			//여기서 출발해서 도착할 수 있는 모든 정점들에 대해서
			for (Node node : list[cur]) {
				//이미 알고 있는 거리보다 더 가깝게 도달할 수 있다면
				if (dist[start][node.v] > dist[start][cur] + node.weight) {
					dist[start][node.v] = dist[start][cur] + node.weight;
					queue.add(new Node(node.v, dist[start][node.v]));
				}
			}
		}
		
	}
	

}