package BAEK_10282_해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int INF = Integer.MAX_VALUE;
	static int[] dist;
	static List<Node>[] list;
	static int n;
	// 총 감염 컴퓨터 수
	static int cnt;
	// 마지막 컴퓨터가 감염된 시간
	static int lastComputer;

	static class Node {
		int computer, second;

		public Node(int computer, int second) {
			super();
			this.computer = computer;
			this.second = second;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// tc개수
		int T = Integer.parseInt(st.nextToken());
		// tc만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			// n:컴퓨터 수, d:의존성 수, c:해킹당한 컴터 번호
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 거리를 저장할 배열
			dist = new int[n + 1];
			// 가장 큰 값으로 설정해두기
			Arrays.fill(dist, INF);
			// 의존성 정보를 저장할 리스트 배열
			list = new ArrayList[n + 1];
			// 리스트 배열의 각 원소 초기화
			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<>();
			}
			// 의존성 입력 받기
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				Node node = new Node(a, s);
				list[b].add(node);
			}
			// 입력 완료

			cnt = 0;
			lastComputer = 0;
			// 다익스트라 사용할 거임
			dijkstra(c);
			System.out.println(cnt + " " + lastComputer);
		}
		// tc

	}

	// main
	static void dijkstra(int start) {
		// 방문체크할 배열 생성
		boolean[] visited = new boolean[n + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			// pq를 초를 기준으로 오름차순 정렬
			@Override
			public int compare(Node o1, Node o2) {
				return o1.second - o2.second <= 0 ? -1 : 1;
			}
		});
		// 가장먼저 감염된 컴퓨터를 넣자
		pq.add(new Node(start, 0));
		// dist 배열의 값도 바꿔주자
		dist[start] = 0;
		// while문 시작(pq가 빌 때까지
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			// 방문체크(이미 방문했다면 쳐내고 아니라면 방문체크)
			if (visited[now.computer])
				continue;
			visited[now.computer] = true;
			// 감염된 컴퓨터 수 증가
			cnt++;
			lastComputer = dist[now.computer];
			// 감염 컴퓨터와 의존성이 있는 컴퓨터를 확인하자
			for (int i = 0; i < list[now.computer].size(); i++) {
				Node next = list[now.computer].get(i);
				if (dist[next.computer] > dist[now.computer] + next.second) {
					dist[next.computer] = dist[now.computer] + next.second;
					pq.add(new Node(next.computer, dist[next.computer]));
				}
			}
		}
		// while

	}
	// dijkstra
}
