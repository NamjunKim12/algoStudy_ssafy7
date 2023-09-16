import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, ans;
	// 맵 만들기 . 
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
			
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		
		for (int i = 0; i < N; i++)
		{	// 맵 입력 .
			for (int j = 0; j < M; j++)
				map[i][j] = sc.next().charAt(0);
		}
		
		bfs();
		
		System.out.println(ans);
		
		sc.close();
	}
	
	public static void bfs()
	{	// 상하좌우대각선
		int[] dr = {1, -1, 0, 0, 1, -1, -1, 1};
		int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
		
		// 모든 칸  브루트포스 
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{	// 상어칸은 확인 X 
				if (map[i][j] == '1') continue;
				
				// queue 초기화 
				Queue<int[]> move = new LinkedList<> ();
				// 방문배열 초기화 
				visited = new boolean[N][M];
				// 현재 시작 좌표 추가 . 
				move.add(new int[] {i, j}); 
				
				visited[i][j] = true;
				// 큐가 빌 때까지 . 
				all : while (move.size() != 0)
				{
					int[] nowPos = move.poll();
					// 주변 확인 . 
					for (int k = 0; k < 8; k++)
					{
						// 맵 벗어나면 패스 
						if (!isIn(nowPos[0]+dr[k], nowPos[1]+dc[k]) || visited[nowPos[0]+dr[k]][nowPos[1]+dc[k]]) continue;
						// 상어 있으면 
						if (map[nowPos[0]+dr[k]][nowPos[1]+dc[k]] == '1')
						{
							// 시작 위치에서 해당 상어까지의 거리 계산 .
							int dist = Math.max(Math.abs(nowPos[0] + dr[k] - i), Math.abs(nowPos[1] + dc[k] - j));
							if (dist > ans)
							{
								// 안전거리가 더 크면 갱신
								ans = dist;
							}
							// 상어 만나고 종료 
							break all;
						}
						// 큐에 인접 위치 넣기 . 
						move.add(new int[] {nowPos[0]+dr[k], nowPos[1]+dc[k]});
						visited[nowPos[0]+dr[k]][nowPos[1]+dc[k]] = true;
					}
				}
			}
		}
	}
	
	public static boolean isIn(int r, int c)
	{
		return (r >= 0 && r < N && c >= 0 && c < M);
	}
}