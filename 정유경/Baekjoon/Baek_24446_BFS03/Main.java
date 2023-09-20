package Baek_24446_BFS03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R;
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	static long[] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		depth = new long[N+1];
		
		//초기화
		for (int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		//간선 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr[u].add(v);
			arr[v].add(u);
		}
		
		//정점 번호 오름차순 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(arr[i]);
		}
		
		for (int i = 0; i <= N; i++) {
			depth[i] = -1;
		}
		
		BFS(R,0);
		
		for (int i = 1; i <= N; i++) {
			System.out.println(depth[i]);
		}
	} //main
	
	public static void BFS(int cur, int d) {		
		Queue<Integer> q = new LinkedList<>();
		q.offer(cur);
		
		visited[cur] = true;
		depth[cur] = d;
		
		while(!q.isEmpty()) {
			for(int i=q.size(); i>0; i--) {
				int a = q.poll();
				depth[a] = d;
				
				for (int next : arr[a]) {
					if(visited[next]) continue;
						
					visited[next] = true;
					q.offer(next);
				}
			}
			d++;
		}
		
		
	} //BFS
}
