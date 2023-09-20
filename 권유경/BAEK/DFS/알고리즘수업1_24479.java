package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// hashmap으로 풀어보자
// 큐를 사용하지 않아도 된다!
public class 알고리즘수업1_24479 {
	static int N, M, R;
	static int[] visited;
	static Map<Integer, ArrayList<Integer>> map; // 간선 정보 담아줄 맵
	static int cnt;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		visited = new int[N + 1];
		map = new HashMap<Integer, ArrayList<Integer>>();
		cnt = 1;
//		q = new LinkedList<>();
		// 맵에 정점과 리스트 추가
		for (int i = 0; i <= N; i++) {
			map.put(i, new ArrayList<>());
		}

		// 간선 정보 담기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map.get(x).add(y);
			map.get(y).add(x);
		}

		// 맵의 각 리스트 오름차순 정렬
		for (int i = 0; i < map.size(); i++) {
			Collections.sort(map.get(i));
		}
		dfs(R);
		System.out.println(Arrays.toString(visited));
		for (int i = 1; i < visited.length; i++) {
			System.out.println(visited[i]);
		}
	}// main

	public static void dfs(int R) {
//		Queue<Integer> q = new LinkedList<>();
//		q.add(R); // 큐에 시작 정점 담기
		visited[R] = cnt++; // 방문순서 기록
//		System.out.println(Arrays.toString(visited));
//		while (!q.isEmpty()) { // 큐가 빌 때까지 반복
//			int n = q.poll(); // n 은 큐 맨 앞에서 뽑은 정점
			for (int i = 0; i < map.get(R).size(); i++) { // 정점 n과 연결된 정점만큼 순회
				if (visited[map.get(R).get(i)] == 0) { // 정점 i를 방문하지 않은 상태라면
					dfs(map.get(R).get(i)); // 재귀
				}
			}
//		} // while

	}// dfs

}
