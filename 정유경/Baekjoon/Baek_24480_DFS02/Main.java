package Baek_24480_DFS02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R;
	static ArrayList<Integer>[] arr;
	static int[] answer;
	static boolean[] visited;
	static int idx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		answer = new int[N+1];
		visited = new boolean[N+1];
		
		//초기화
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new ArrayList<>();
		}
		
		//배열 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr[u].add(v);
			arr[v].add(u);
		}
		
		//내림차순 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(arr[i], Collections.reverseOrder());
		}
		
		idx = 0;
		dfs(R);
		
		for (int i = 1; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
		
	}//main
	
	public static void dfs(int cur) {
		answer[cur] = ++idx;
		visited[cur] = true;
		
		for (int next : arr[cur]) {
			if(visited[next]) continue;
			visited[next] = true;
			dfs(next);
		}
	} //dfs
}
