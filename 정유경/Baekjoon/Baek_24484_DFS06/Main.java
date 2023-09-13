package Baek_24484_DFS06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R, idx;
	static ArrayList<Integer>[] arr;
	static long[] order, depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		
		//ArrayList 초기화
		for (int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		//간선 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr[u].add(v);
			arr[v].add(u);
		}
		
		//인접 정점 내림차순으로 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(arr[i], Collections.reverseOrder());
		}
		
		order = new long[N+1];
		depth = new long[N+1];
		for (int i = 1; i <= N; i++) {
			order[i] = 0;
			depth[i] = -1;
		}
		
		idx = 1;
		DFS(R,0);
		long sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += order[i] * depth[i];
		}
		System.out.println(sum);
		
	}//main
	
	public static void DFS(int cur, int d) {
		if(arr[cur].size() == 0) return;
		
		order[cur] = idx++;
		depth[cur] = d;
		
		for (int next : arr[cur]) {
			if(depth[next] != -1) continue;
			
			DFS(next, d+1);
		}
	}//DFS
}
