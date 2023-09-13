package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 내림차순 방문!
public class 알고리즘수업2_24445 {
	static int N, M, R; // 정점 수, 간선 수, 시작 정점
	static ArrayList<ArrayList<Integer>> list; // 간선 정보를 담을 리스트
	static int[] visited; // 방문체크 및 방문순서

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()); // readLine은 스트링 값과 엔터를 포함하여 읽어오는 방식
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		visited = new int[N+1];
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		} // 정점의 수만큼 리스트 원소 추가

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 무방향 그래프이므로
			list.get(x).add(y);
			list.get(y).add(x);
		} // 입력 완료

		// 리스트의 각 리스트들을 내림차순하자 (Collections.reverseOrder())
		for (int i = 0; i < list.size(); i++) {
			Collections.sort(list.get(i), Collections.reverseOrder());
		}

		bfs();

	}// main

	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(R); // 시작정점 큐에 추가
		int cnt = 1; // 방문 순서
		visited[R] = cnt; // 방문순서 기록
		while (!q.isEmpty()) { // 큐가 빌 때까지 반복
			int n = q.poll(); // 큐에서 맨 앞의 값 가져오기
			for (int i = 0; i < list.get(n).size(); i++) {
				if (visited[list.get(n).get(i)] == 0) { // 방문하지 않은 곳이라면
					q.add(list.get(n).get(i)); // 큐에 추가
					cnt += 1;
					visited[list.get(n).get(i)] = cnt; // 방문순서 기록
				}
			}
		} // while
		for (int i = 1; i < visited.length; i++) {
			System.out.println(visited[i]);
		}
	}

}
