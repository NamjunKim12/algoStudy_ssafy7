package Baek_1197_최소스패닝트리;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//프림
public class Main2 {
	static final int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge>{
		int st, ed, w;
		
		public Edge(int st, int ed, int w) {
			this.st = st;
			this.ed = ed;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		//인접 리스트
		List<Edge>[] list = new ArrayList[V+1];
		
		//리스트 생성
		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
			
			list[A].add(new Edge(A,B,W));
			list[B].add(new Edge(B,A,W));
		}//입력 끝
		
		boolean[] visited = new boolean[V+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//임의의 정점 한개 선택하여 시작
		visited[1] = true;
		pq.addAll(list[1]);
		
		int pick = 1; //이미 정점 한개 뽑았으므로
		int ans = 0;
		
		while(pick != V) {
			Edge e = pq.poll();
			
			if(visited[e.ed]) continue;
			
			ans += e.w;
			pq.addAll(list[e.ed]);
			visited[e.ed] = true;
			pick++;
			
		}
		
		System.out.println(ans);
		
		
		
	}//main
}
