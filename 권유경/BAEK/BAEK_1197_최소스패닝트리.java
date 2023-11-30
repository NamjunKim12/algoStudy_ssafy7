package BAEK_1197_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] p;

	static class Node {
		int st, ed, value;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 정점의 개수
		int V = Integer.parseInt(st.nextToken());
		// 간선의 개수
		int E = Integer.parseInt(st.nextToken());
		// 간선 정보 담을 리스트
		List<Node> list = new ArrayList<>();

		// 간선 정보 담기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			Node node = new Node();
			node.st = Integer.parseInt(st.nextToken());
			node.ed = Integer.parseInt(st.nextToken());
			node.value = Integer.parseInt(st.nextToken());
			list.add(node);
		}

		// 가중치를 기준으로 리스트 오름차순
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value <= 0 ? -1 : 1;
			}
		});

		// 크루스칼 알고리즘으로 합치자

		// 대표자 선정
		p = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
		// 간선 수
		int pick = 0;
		int ans = 0;

		for (int i = 0; i < list.size(); i++) {
			if (findset(list.get(i).st) != findset(list.get(i).ed)) {
				union(findset(list.get(i).st), findset(list.get(i).ed));
				pick++;
				ans += list.get(i).value;
			} 
			if (pick == V - 1)
				break;
		}
		System.out.println(ans);

	}
	// main

	static void union(int x, int y) {
		p[y] = x;
	}

	static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}
}
