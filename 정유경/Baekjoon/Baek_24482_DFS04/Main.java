package Baek_24482_DFS04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R;
	static ArrayList<Integer>[] edges;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //정점 개수
		M = Integer.parseInt(st.nextToken());  //간선의 수
		R = Integer.parseInt(st.nextToken());  //시작 정점
		edges = new ArrayList[N+1];
		answer = new int[N+1];
		
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
		
		//정점 집합을 내림차순으로 정렬
		for (int i = 0; i < edges.length; i++) {
			Collections.sort(edges[i], Collections.reverseOrder());
		}
		
		//모든 정답 배열 -1로 초기화
		for (int i = 0; i < answer.length; i++) {
			answer[i] = -1;
		}
		
		DFS(R, 0);
		
		for (int i = 1; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}//main
	
	public static void DFS(int cur, int depth) {
		answer[cur] = depth;
		
		for (int next : edges[cur]) {
			if(answer[next] != -1) continue;
			
			DFS(next, depth+1);
		}
	}
}
