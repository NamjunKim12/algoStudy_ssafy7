package BAEK_1753_최단경로;

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
	// 가중치 담을 배열
	static int[] d;
	// 최댓값
	static int INF = Integer.MAX_VALUE;
	// 간선 정보 담을 리스트
	static List<Node>[] list;
	// 정점 개수
	static int V;
	static boolean[] visited;

	static class Node {
		// idx:끝 노드 , cost:가중치
		int idx, cost;

		public Node(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 정점 개수
		V = Integer.parseInt(st.nextToken());
		// 간선 개수
		int E = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		// 시작 정점
		int K = Integer.parseInt(st.nextToken());

		// 간선 정보 담을 리스트
		list = new ArrayList[V + 1];
		// 초기화
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		// 간선 정보, 가중치
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			// 시작 정점
			int s = Integer.parseInt(st.nextToken());
			// 끝 정점
			int ed = Integer.parseInt(st.nextToken());
			// 가중치
			int value = Integer.parseInt(st.nextToken());
			Node node = new Node(ed, value);
			// 리스트에 추가
			list[s].add(node);
		}

		/*
		 * for (int i = 0; i < list.length; i++) { for (int j = 0; j < list[i].size();
		 * j++) { System.out.println(list[i].get(j).idx + "," + list[i].get(j).cost); }
		 * }
		 */

		// 가중치 담을 배열 모두 최댓값으로 초기화
		d = new int[V + 1];
		Arrays.fill(d, INF);

		// 방문체크할 배열 생성
		visited = new boolean[V + 1];

		dijkstra(K);
	}

	// main

	public static void dijkstra(int start) {
		// 가중치를 기준으로 오름차순 정렬되는 우선순위큐 선언
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost <= 0 ? -1 : 1;
			}
		});
		// pq에 시작점에서 출발하는 노드들을 넣자
		pq.add(new Node(start, 0));
		// 시작점은 가중치 0으로 설정
		d[start] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			// 방문했다면 넘어가고 방문하지 않았다면 방문체크하자
			if (visited[node.idx])
				continue;
			visited[node.idx] = true;

			for (int i = 0; i < list[node.idx].size(); i++) {
				// 연결된 간선노드를 변수에 넣자.
				Node next = list[node.idx].get(i);
				// 연결된 간선의 가중치가 현재 d배열의 가중치보다 작으면 바꾸자.
				if (d[next.idx] > d[node.idx] + next.cost) {
					d[next.idx] = d[node.idx] + next.cost;

					pq.add(new Node(next.idx, d[next.idx]));
				}
			}
		}
		// while

		for (int i = 1; i < d.length; i++) {
			if (visited[i])
				System.out.println(d[i] + " ");
			else
				System.out.println("INF");
		}
	}
	// dijkstra

}
