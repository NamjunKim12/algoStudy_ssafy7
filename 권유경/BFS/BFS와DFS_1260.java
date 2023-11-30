package BFS;
// 약 한시간 소요
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS와DFS_1260 {
	static List<Integer>[] list; // 인접정점 정보 리스트를 가지는 배열
	static boolean[] visited; // 방문 체크
	static List<Integer> node; // 방문 순서 기록
	static int N, M, V;
	static int cnt = 0; // 방문 순서

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		node = new ArrayList<>();
		list = new ArrayList[N + 1]; // 정점의 번호와 리스트의 인덱스와 맞추기 위해

		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		} // 각 원소리스트 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 양방향이므로 아래처럼 인접 정점의 정보 입력
			list[x].add(y);
			list[y].add(x);
		} // 입력완료

		// list의 각 리스트를 오름차순으로 정렬하자.
		for (int i = 0; i < list.length; i++) {
			Collections.sort(list[i]);
		}
		
		DFS(V);
		for(int i = 0; i<node.size(); i++) {
			System.out.print(node.get(i)+ " ");
		}System.out.println();
		
		visited = new boolean[N + 1]; // 초기화 해줘야 함.. 이거 안해서 시간 버림
		node = new ArrayList<>(); // 초기화 해줘야 함
		
		BFS(V);
		for(int i = 0; i<node.size(); i++) {
			System.out.print(node.get(i)+ " ");
		}System.out.println();

	}// main

	static void DFS(int V) { // 깊이 우선 탐색
		visited[V]=true;
		node.add(V);
		for(int i = 0; i<list[V].size(); i++) {
			int n = list[V].get(i);
			if(visited[n]==false) {
				visited[n]=true;
				DFS(n);
			}
		}
	}

	static void BFS(int V) { // 너비 우선 탐색
		Queue<Integer> q = new LinkedList<>();
		q.add(V);
		visited[V] = true;
		node.add(V);
		while(!q.isEmpty()) {
			int n = q.poll();
			for(int i = 0; i<list[n].size(); i++) {
				int t = list[n].get(i);
				if(visited[t]==false) {
					q.add(t);
					visited[t]=true;
					node.add(t);
				}
			}
		}
		

	}//BFS

//	4 5 1
//	1 2
//	1 3
//	1 4
//	2 4
//	3 4
}
