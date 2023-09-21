import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 처음에는 벽을 이미 방문된 것으로 처리하고, 
	// 활성화된 바이러스도 방문된 것으로 벽처럼 처리했습니다. 
	// (바이러스는 방문할 필요 없으니까 괜찮다고 생각함. )
	// 0 1 1 0 
	// 2 1 1 2
	// 2 1 1 2
	// 0 1 1 0
	// 근데 이런 경우, 두 개의 바이러스를 선택하면
	// 모든 칸에 바이러스를 전염시킬 수 있어야 하는데, 
	// 바이러스를 벽으로 인식해서 못 퍼트리는 경우가 생깁니다. 
	// 때문에 벽처럼 표시하지는 않았고, VMAP이라는 바이러스 위치를 저장하는
	// 배열을 하나 만들어 사용하였습니다. 
	// 입력을 받을 때, 바이러스의 위치를 저장해두고
	// 조합을 이용해 활성화시키고자 하는 바이러스를 선택했습니다.

	// 모든 칸에 바이러스가 전염되었는지 판단하는 기준은
	// 배열을 돌면서 빈칸이 남아있는지 확인하기 보다는
	// 처음 입력 받을 때, 빈칸의 개수 = 전염시켜야하는 칸의 수 라고 생각해서
	// 큐를 돌면서 전염시키는 칸을 cnt 해주어 위 값과 서로 비교하여 판단했습니다.  

	static int N, M, V, ANS;
	static boolean MAP[][];
	static boolean VMAP[][];
	static ArrayList<int[]> VIRUS;
	static int SPACE;
	static int[] COMB;
	static final int[] dr = new int[] {-1, 1, 0, 0};
	static final int[] dc = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		VIRUS = new ArrayList<>();
		SPACE = 0;
		MAP = new boolean[N][N];
		VMAP = new boolean[N][N];
		COMB = new int[M];
		
		ANS = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				switch (st.nextToken().charAt(0)) {
					// 빈칸. 
					case ('0') :
						SPACE ++;
						break;
					// 벽.
					case ('1') :
						MAP[i][j] = true; // 맵에 벽 표시
						break;
					case ('2') :
						VMAP[i][j] = true; // 바이러스라고 표시
						VIRUS.add(new int[] {i, j}); // 바이러스 좌표 기록.
						break;
				}
			}
		}
		
		V = VIRUS.size();
		
		comb(0, 0);
		
		// ANS 갱신 안되었음.
		if (ANS == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ANS);
		
		br.close();
	}
	
	static void comb(int sIdx, int deep) {
		if (deep == M) {
			// 조합 완성되면 역병 검사
			BFS();
			
			return ;
		}
		
		for (int i = sIdx; i < V; i++) {
			COMB[deep] = i;
			int[] pos = VIRUS.get(i);
			MAP[pos[0]][pos[1]] = true;
			comb(i+1, deep + 1);
			MAP[pos[0]][pos[1]] = false;
		}
	}
	
	
	private static void BFS() {
		boolean[][] lMap = new boolean[N][N];
		
		// 맵을 복사해서 이용. 
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < N; j++) 
				lMap[i][j] = MAP[i][j];
		
		// 바이러스 큐 구현. 
		Queue <int[]> virus = new LinkedList<> ();
		
		// 처음 시작 위치 바이러스 넣어주기.
		for (int i = 0; i < M; i++) {
			virus.add(VIRUS.get(COMB[i]));
		}
		
		int dist = 0;
		int delta = 0;
		int space = 0;
		
		while (!virus.isEmpty()) {
			int loopSize = virus.size();
			delta ++;
			for (int i = 0; i < loopSize; i++) {
				int[] curr = virus.poll();
				
				for (int j = 0; j < 4; j++) {
					if (!isIn(curr[0] + dr[j], curr[1] + dc[j])) continue;
					if (lMap[curr[0]+dr[j]][curr[1]+dc[j]])  continue;

					lMap[curr[0]+dr[j]][curr[1]+dc[j]] = true;
					
					virus.add(new int[] {curr[0]+dr[j], curr[1]+dc[j]});
					
					// 0을 만난 경우. 
					// 요게 좀 까다로웠는데
					// 2 2 2
					// 1 1 0
					// 2 2 0
					// 같은 테이스 케이스는 2초가 나와야 합니다.
					// 2 2 2
					// 1 1 0
					// 0 2 0 => 이건 4초
					// 이렇게 바이러스 안에 숨어있는 경우에도 퍼트릴 수 있어야 하므로, 
					// Queue에는 진행 하되, 시간은 증가시켜 주고,
					// 0을 만날 때 총 시간에 더해주어야 합니다 ... 

					if (!VMAP[curr[0]+dr[j]][curr[1]+dc[j]]) {
						dist += delta;
						space ++;
						delta = 0;
					}
				}
			}
		}
//		mapPrint(lMap);
		if (SPACE != space) return ;
		
		if (dist < ANS) ANS = dist;
// 		System.out.println("SPACE : " + SPACE);
// 		System.out.println("space : " + space);
// 		System.out.println("dist : " + dist);
	}

	static void mapPrint(boolean[][] map) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));			
		}
		System.out.println();
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
