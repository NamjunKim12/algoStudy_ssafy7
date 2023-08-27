import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N, K, ans, maxH;
	static int[][] map;
	static boolean[][] visited;
	static boolean flag;
	// 상 하 좌 우 
	static final int[] dr = new int[] {-1, 1, 0, 0};
	static final int[] dc = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++)
		{
			N = sc.nextInt();
			K = sc.nextInt();
			maxH = 0; 
			ans = 0;
			
			map = new int[N][N];
			visited = new boolean[N][N]; 
			flag = false;
			
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < N; j++)
				{
					map[i][j] = sc.nextInt();
					if (map[i][j] > maxH) maxH = map[i][j];
				}
			}
			
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < N; j++)
				{
					if (map[i][j] == maxH)
					{
						visited[i][j] = true;
						dfs(i, j, 1, map[i][j] + " ");
						visited[i][j] = false;
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
		sc.close();
	}
	
	public static void dfs(int r, int c, int deep, String path) {
		Queue <int[]> dest = new LinkedList<> ();
		boolean thisDeepChecked = false;
		
		for (int i = 0; i < 4; i++)
		{	
			// 범위 안에 있고, 방문하지 않았음.
			if (isIn(r + dr[i], c + dc[i]) && !visited[r + dr[i]][c + dc[i]])
			{
				if (map[r][c] > map[r+dr[i]][c+dc[i]])
					dest.add(new int[] {r + dr[i], c + dc[i]});
				else if (!flag && map[r][c] > map[r+dr[i]][c+dc[i]] - K)
					dest.add(new int[] {r + dr[i], c + dc[i]}); 
			}				
		}
		// 더 향할 수 있는 곳이 없다
		if (dest.isEmpty())
		{
			if (deep > ans) ans = deep;
			return ;
		}
		
		while (!dest.isEmpty())
		{
			int[] pos = dest.poll();
			int destR = pos[0];
			int destC = pos[1];
			int tmp = map[destR][destC];
			
			// 토건 공사해 지나갈 수 있으면 
			if (map[r][c] <= map[destR][destC])
			{
				if (flag)
				{
					flag = false;
					return;
				}
				thisDeepChecked = true;
				map[destR][destC] = map[r][c] - 1;
				flag = true;
			}
			visited[destR][destC] = true;
			dfs(destR, destC, deep + 1, path + map[destR][destC]+ " ");
			visited[destR][destC] = false;
			if (thisDeepChecked)
			{
				map[destR][destC] = tmp;
				flag =false;
			}
		}
	}
	
	public static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) return true;
		return false;
	}
}