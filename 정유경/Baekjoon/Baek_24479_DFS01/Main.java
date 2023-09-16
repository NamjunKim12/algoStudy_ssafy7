package Baek_24479_DFS01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R;
	static ArrayList<Integer>[] edges;
	static boolean[] visited;
	static int[] answer;
	static int idx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //정점 개수
		M = Integer.parseInt(st.nextToken());  //간선의 수
		R = Integer.parseInt(st.nextToken());  //시작 정점
		edges = new ArrayList[N+1];
		visited = new boolean[N+1];
		answer = new int[N+1];
		
		//초기화
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new ArrayList<>();
		}
		
		//간선 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); 
			int v = Integer.parseInt(st.nextToken()); 
			edges[u].add(v);
			edges[v].add(u);
		}
		
		//오름차순 정렬
		for(int i=1; i<=N; i++) {
			Collections.sort(edges[i]);
		}
		
		idx = 1;
		dfs(R);
		
		for (int i = 1; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	} //main
	
	public static void dfs(int cur) {
		answer[cur] = idx++;
		visited[cur] = true;
		
		for(int next : edges[cur]) {
			if(visited[next]) continue;
			visited[next] = true;
			dfs(next);
		}
	} //dfs
}
