package BFS;
// 약 40분 걸림
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 바이러스_2606 {
	static int N, M;// 컴퓨터 수, 네트워크 연결선 수
	static int V = 1; // 감염 컴퓨터 번호
	static List<Integer>[] list;
	static boolean[] visited;
	static int cnt = 0; // 바이러스 걸린 컴퓨터 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];

		// 이 작업을 꼭 해주어야 함.. 이거 빼먹어서 또 시간 버림
		for(int i = 0; i<list.length; i++) {
			list[i]=new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}

		BFS();
		System.out.println(cnt);

	}// main

	static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.add(V);
		visited[V] = true;
		while (!q.isEmpty()) {
			int n = q.poll();
			for (int i = 0; i < list[n].size(); i++) {
				int t = list[n].get(i);
				if (visited[t] == false) {
					q.add(t);
					visited[t] = true;
					++cnt;
				}
			}
		} // while

	}// BFS

}
