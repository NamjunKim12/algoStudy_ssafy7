import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	static int V, E, STT;
	static int[] DIST;
	static boolean[] VISIT;
	static ArrayList<int[]>[] NEAR;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		STT = Integer.parseInt(br.readLine());
		
		NEAR = new ArrayList[V+1];
		DIST = new int[V+1];
		VISIT = new boolean[V+1];
		
		for (int i = 1; i <= V; i++)
			NEAR[i] = new ArrayList<> ();
		
		// 거리 배열 INF로 초기화.
		Arrays.fill(DIST, INF);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			NEAR[stt].add(new int[] {end, w});
		}
		// 시작점 설정. 
		DIST[STT] = 0;

		// 비용 별 우선순위 설정.
		PriorityQueue<int[]> queue = new PriorityQueue<> (new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) return (o1[0] > o2[0]) ? 1 : -1;
				return (o1[1] > o2[1]) ? 1 : -1;
			}
		});
		
		queue.add(new int[] {STT, 0});
		
		while (! queue.isEmpty()) {
			int[] curr = queue.poll();
			
			if (VISIT[curr[0]]) continue;
			
			VISIT[curr[0]] = true;
			
			for (int[] next : NEAR[curr[0]]) {
				if (DIST[curr[0]] + next[1] < DIST[next[0]]) {
					DIST[next[0]] = DIST[curr[0]] + next[1];
					queue.add(new int[] {next[0], DIST[next[0]]});
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if (DIST[i] == INF) {
				sb.append("INF");
			}
			else {
				sb.append(DIST[i]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}