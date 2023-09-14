package Baek_24483_DFS05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R,idx;
	static ArrayList<Integer>[] edges;
	static long[] order, depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //정점 개수
		M = Integer.parseInt(st.nextToken());  //간선의 수
		R = Integer.parseInt(st.nextToken());  //시작 정점
		edges = new ArrayList[N+1];
		order = new long[N+1];
		depth = new long[N+1];
		
		//초기화
		for (int i = 0; i < edges.length; i++) {
			edges[i]=new ArrayList<>();
		}
		
		//간선 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			edges[u].add(v);
			edges[v].add(u);
		}
		
		//정점 집합을 오름차순으로 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(edges[i]);
		}
		
		for (int i = 0; i <= N; i++) {
			depth[i] = -1;
			order[i] = 0;
		}
		
		idx=1;
		DFS(R, 0);
		
		long sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += order[i] * depth[i];
			
		}
		
		System.out.println(sum);
		
	} //main
	
	public static void DFS(int cur, int d) {
		depth[cur] = d;
		order[cur] = idx;
		
		for (int next : edges[cur]) {
			if(depth[next] != -1) continue;
			
			order[next] = idx++;
			DFS(next, d+1);
		}
	} //DFS
}
