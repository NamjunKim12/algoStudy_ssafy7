import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 요약 :
// 간선 정보를 뒤집으면
// 다른 노드에서 STT 노드로 오는 최소 비용을 구할 수 있다. 

// 목표 노드로 갔다가, 원 노드로 돌아와야 한다. 
// 가는 최소 비용은 다익스트라로 쉽게 구할 수 있다. 
// 원 노드로 돌아오는 비용은 어떻게 구할 수 있을까 ?
// N-1 개 노드에 대해서 다익스트라를 하면 구할 수 있을 것이다. 
// (목표 노드가 아닌 다른 노드들)
// 하지만 몹시 비효율 적이다. 
// 이 문제는 다익스트라 2번으로 풀이할 수 있는 문제이다. 

public class Main {
	private static final int INF = Integer.MAX_VALUE;

	static int N, M, X;
	static boolean VISIT[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] toX = new ArrayList[N+1];
		ArrayList<int[]>[] fromX = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			toX[i] = new ArrayList<> ();
			fromX[i] = new ArrayList<> ();
		}
		
		int[] toDist = new int[N+1];
		int[] fromDist = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int fr = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			toX[fr].add(new int[] {to, w});
			fromX[to].add(new int[] {fr, w});
		}	
		
		dijkstra(toX, toDist);
		dijkstra(fromX, fromDist);
		
		int maxTime = Integer.MIN_VALUE;
		
		for (int i = 1; i <= N; i++) {
			if (i == X) continue;
			int time = toDist[i] + fromDist[i];
			if (time > maxTime) maxTime = time;
		}
		
		System.out.println(maxTime);
		
		br.close();
	}
	
	static void dijkstra(ArrayList<int[]>[] Near, int[] dist) {
		// 우선 순위 큐를 만들어 주세요.
		// 이하 초기 세팅 중...
		PriorityQueue<int[]> PQ = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) return (o1[0] > o2[0]) ? 1 : -1;
				return (o1[1] > o2[1]) ? 1 : -1;
			}
		});
		
		VISIT = new boolean[N+1];
		Arrays.fill(dist, INF);
		dist[X] = 0;
		
		PQ.add(new int[] {X, 0});
		
		while (! PQ.isEmpty()) {
			// PQ에서 하나 뽑.
			int[] curr = PQ.poll();
			// 이미 결정된 요소면 안해 
			if (VISIT[curr[0]]) continue;
			
			VISIT[curr[0]] = true; // 이제 결정할거야.
			
			for (int[] next : Near[curr[0]]) {
				if (dist[curr[0]] + next[1] < dist[next[0]]) {
					dist[next[0]] = dist[curr[0]] + next[1];
					PQ.add(new int[] {next[0], dist[next[0]]});
				}
			}
		}
	}
}