package Baek_24444_BFS01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R, idx;
	static ArrayList<Integer>[] arr;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		visited = new int[N+1];
		
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
		
		idx = 1;
		BFS(R);
		
		for (int i = 1; i < visited.length; i++) {
			System.out.println(visited[i]);
		}
		
	}//main
	
	public static void BFS(int cur) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(cur);
		visited[cur] = idx++;
		
		while(!q.isEmpty()) {
			int a = q.poll();
			
			for (int next : arr[a]) {
				//이미 방문했다면 continue
				if(visited[next] != 0) continue;
				
				q.offer(next);
				visited[next] = idx++;
			}
		}
		
	} //BFS
}
