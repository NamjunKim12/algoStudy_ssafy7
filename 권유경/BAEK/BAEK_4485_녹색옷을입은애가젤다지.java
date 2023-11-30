package SWEA_4485_녹색옷을입은애가젤다지;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static int[][] dist;

	static class Node {
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = 0;
		while (true) {
			N = sc.nextInt();
			// N=0이면 종료
			if (N == 0)
				return;
			tc++;
			// 동굴 크기

			// 동굴 입력받기
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// 가장 큰 수로 초기화 해 둘 배열
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = 987654321;
				}
			}

			// 처음 시작지점 값은 정해두기
			dist[0][0] = map[0][0];

			dijkstra(0, 0, dist[0][0]);
			System.out.println("Problem "+tc+": "+ dist[N - 1][N - 1]);
		}
		// tc

	}
	// main

	static void dijkstra(int x, int y, int cost) {

		// 도둑루피 크기를 기준으로 오름차순 정렬하는 pq 생성
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost <= 0 ? -1 : 1;
			}
		});

		// 시작 점 넣기
		pq.add(new Node(x, y, cost));

		// pq가 빌 때까지 반복
		while (!pq.isEmpty()) {
			Node now = pq.poll();

			int nowX = now.x;
			int nowY = now.y;
			int nowCost = now.cost;

			// 상하좌우 탐색을 위한 배열 생성
			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			for (int i = 0; i < 4; i++) {
				int nextX = nowX + dr[i];
				int nextY = nowY + dc[i];
				// 다음 자리가 배열 안에 있다면
				if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
					// 그 자리까지의 값 > 현재 자리의 값 + 다음자리의 값 이라면 갱신
					if (dist[nextX][nextY] > dist[nowX][nowY] + map[nextX][nextY]) {
						dist[nextX][nextY] = dist[nowX][nowY] + map[nextX][nextY];
						// 갱신한 자리는 pq에 넣자
						pq.add(new Node(nextX, nextY, dist[nextX][nextY]));
					}
				}
			}
		}
		// while
	}
	// dijkstra
}
