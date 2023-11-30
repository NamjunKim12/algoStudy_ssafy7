package BAEK_1766_문제집;
// 위상정렬 사용하자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 문제 개수
		int N = Integer.parseInt(st.nextToken());
		// 정보 개수
		int M = Integer.parseInt(st.nextToken());

		// 진입차수 담을 배열
		int[] degree = new int[N + 1];

		// 정보 담을 리스트
		List<Integer>[] list = new ArrayList[N + 1];

		// 리스트의 각 원소 초기화
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}

		// 리스트에 정보 담기, 진입차수 배열 채우기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int node = Integer.parseInt(st.nextToken());
			list[idx].add(node);
			degree[node]++;
		}
		// 입력 완료

		// 리스트 오름차순하자
		// list는 배열이라 Collections.sort(list, new Comparator<Integer>() 이걸로 바로 정렬할 수 없음
		// for문을 돌면서 배열안에 있는 각 원소들(리스트)를 정렬해주어야 함
		for (int i = 0; i < list.length; i++) {
			Collections.sort(list[i], new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2 < 0 ? -1 : 1;
				}
			});
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		// 진입차수가 0인 것부터 배열에 넣자
		for (int i = 1; i < degree.length; i++) {
			if (degree[i] == 0) {
				pq.add(i);
			}
		}

		// 큐가 빌 때까지 반복하자
		while (!pq.isEmpty()) {
			int t = pq.poll();
			System.out.print(t+" ");
			// t가 먼저 풀려야 풀리는 문제들의 진입차수를 -1 한다.
			for (int i = 0; i < list[t].size(); i++) {
				degree[list[t].get(i)]--;
				// 진입차수가 0이되면 q에 추가한다.
				if (degree[list[t].get(i)] == 0) {
					pq.add(list[t].get(i));
				}
			}
		}
		// while

	}
	// main
}
/*
 package BAEK_1766_문제집;
// 위상정렬 사용하자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
public static void main(String[] args) throws IOException {
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer st = new StringTokenizer(br.readLine());
// 문제 개수
int N = Integer.parseInt(st.nextToken());
// 정보 개수
int M = Integer.parseInt(st.nextToken());

	// 진입차수 담을 배열
	int[] degree = new int[N + 1];

	// 정보 담을 리스트
	List<Integer>[] list = new ArrayList[N + 1];

	// 리스트의 각 원소 초기화
	for (int i = 0; i < list.length; i++) {
		list[i] = new ArrayList<>();
	}

	// 리스트에 정보 담기, 진입차수 배열 채우기
	for (int i = 0; i < M; i++) {
		st = new StringTokenizer(br.readLine());
		int idx = Integer.parseInt(st.nextToken());
		int node = Integer.parseInt(st.nextToken());
		list[idx].add(node);
		degree[node]++;
	}
	// 입력 완료

	// 리스트 오름차순하자
	// list는 배열이라 Collections.sort(list, new Comparator<Integer>() 이걸로 바로 정렬할 수 없음
	// for문을 돌면서 배열안에 있는 각 원소들(리스트)를 정렬해주어야 함
	for (int i = 0; i < list.length; i++) {
		Collections.sort(list[i], new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2 < 0 ? -1 : 1;
			}
		});
	}

	Deque<Integer> dq = new ArrayDeque<>();
	// 진입차수가 0인 것부터 배열에 넣자
	for (int i = 1; i < degree.length; i++) {
		if (degree[i] == 0) {
			dq.add(i);
		}
	}

	// 큐가 빌 때까지 반복하자
	while (!dq.isEmpty()) {
		int t = dq.poll();
		System.out.print(t+" ");
		// t가 먼저 풀려야 풀리는 문제들의 진입차수를 -1 한다.
		for (int i = 0; i < list[t].size(); i++) {
			degree[list[t].get(i)]--;
			// 진입차수가 0이되면 q에 추가한다.
			if (degree[list[t].get(i)] == 0) {
				dq.addFirst(list[t].get(i));
			}
		}
	}
	// while

}
// main
 */
