package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 어려웠다.
// 리스트를 원소로 가지는 배열을 처음 사용해보았다.
// bufferdReader를 사용했는데, 익숙지 않아 쉽지 않았다.
// 머릿속으로 생각한 것을 코드로 구현하기가 쉽지 않아서 구글링을 하며 여러 bfs방법들을 참고하였다.
// 이 문제에는 적용되지 않는 bfs 코드도 있었어서 많이 헤맸다.
// 이 문제를 푸는 데에만 하루종일 하진 않았지만 3일정도 걸린 것 같다.
// 더 많은 문제를 풀어보며 익숙해져야겠다.

public class 알고리즘수업1_24444 {
	static int N, M, R; // 정점의 수, 간선의 수, 시작정점
	static ArrayList<Integer>[] arr; // 리스트를 원소로 가지는 배열(각 정점과 연결된 정점을 리스트로 받을 것임)
	static boolean[] visited; // 방문체크
	static int[] node; // 방문 순서

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()); // 공백을 기준으로 쪼개기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N+1]; // 정점의 수 + 1 (정점 번호와 인덱스 번호를 맞추기 위해)
		
		for (int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // 공백을 기준으로 쪼개기
			int x = Integer.parseInt(st.nextToken()); // x 와 연결된 
			int y = Integer.parseInt(st.nextToken()); // y
			// 무방향 그래프이므로 
			arr[x].add(y); // x번째 리스트에 x번의 정점과 연결된 정점 y를 추가
			arr[y].add(x); // y번째 리스트에 y번의 정점과 연결된 정점 x를 추가
		}

		visited = new boolean[N + 1]; // _번 정점이 방문했는지 확인하기 위해
		node = new int[N + 1]; // _번 정점이 몇번째로 방문되는지 확인하기 위해
		BFS();

	}// main

	public static void BFS() {
		Queue<Integer> q = new LinkedList<>(); // 큐 만들기
		q.add(R); // 큐에 시작 정점 넣기
		int cnt = 1; // 시작 정점은 방문 번호 1
		visited[R] = true; // 시작 정점 방문체크
		node[R] = cnt; // node 배열의 시작정점 인덱스에 방문번호 넣어주기
		while (!q.isEmpty()) {// 큐가 빌 때까지 반복
			int n = q.poll(); // n은 큐의 맨 앞에서 빼기
			Collections.sort(arr[n]); // n번째 리스트 오름차순 정렬
			for (int i : arr[n]) { // n번과 연결된 정점을 순회
				if (!visited[i]) { // 방문하지 않았다면
					cnt += 1;
					visited[i] = true; // 방문 체크
					q.add(i); // 큐에 추가
					node[i] = cnt; // 방문순서 입력
				}
			}
		}//while
		for (int i = 1; i <= N; i++) {
			System.out.println(node[i]);
		}

	}
}
