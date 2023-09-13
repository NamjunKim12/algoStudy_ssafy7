package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 내림차순!!
// 이번에도 해쉬맵으로 풀어보자
public class 알고리즘수업1_24480 {
	static int N, M, R; // 정점 수, 간선 수, 시작정점
	static int[] visited; // 방문 체크 및 방문 순서 기록
	static int cnt; // 방문 순서
	static Map<Integer, ArrayList<Integer>> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		cnt = 1;
		visited = new int[N + 1];
		map = new HashMap<Integer, ArrayList<Integer>>();

		// 맵에 정점과 리스트 추가해주자
		for (int i = 0; i <= N; i++) {
			map.put(i, new ArrayList<>());
		}

		// 간선 정보를 받자
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map.get(x).add(y);
			map.get(y).add(x);
		}

		// 내림차순 정렬하자
		for (int i = 0; i < map.size(); i++) {
			Collections.sort(map.get(i), Collections.reverseOrder());
		}

		dfs(R);
		StringBuilder sb = new StringBuilder();
		for(int i =1; i<visited.length; i++) {
			sb.append(visited[i]+"\n");
		}
		
		System.out.println(sb);
		
	}// main

	public static void dfs(int R) {
		visited[R] = cnt++;
		for (int i = 0; i < map.get(R).size(); i++) {
			if (visited[map.get(R).get(i)] == 0) { // 방문하지 않은 정점이라면
				dfs(map.get(R).get(i)); // 재귀
			}
		}

	}

}
