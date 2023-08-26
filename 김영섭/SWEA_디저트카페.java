import java.util.*;
  
public class Solution {
	// 방문처리 하는 과정에서 애를 먹었다.
	// dfs 문제에서 방문처리할 때 내가 현재 위치한 칸을 방문처리했었다.
	// 지금 내가 이 칸을 방문할지 말지를 결정해야하기 때문
	// 근데 이 문제에서는 내가 위치한 (다음칸)을 방문할지 말지 결정해야 한다.
	// 진행 방향으로 방문할 것인지, 90도 꺾은 방향을 방문할 것인지
	// 따라서 내 다음 칸(혹은 꺾은 칸)을 방문한다고 dfs로 넘기고, 다음 칸을 방문처리해서 진행해야 했다. 

	// 진행방향.
    static final int[] dr = new int[] {1, 1, -1, -1};
    static final int[] dc = new int[] {1, -1, -1, 1};
    static int[][] map;
    static boolean[] iAte;
    static int N, ateSize;
    static int R, C, ans; //출발 지점.
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for (int t = 1; t <= T; t++)
        {
            N = sc.nextInt();
            ans = -1;
            // 먹은 음식 초기화.
            ateSize = 0;
             
            map = new int[N][N];
             
            // 맵 받아오기.
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    map[i][j] = sc.nextInt();
             
            for (R = 0; R < N; R++)
            {
                for (C = 0; C < N; C++)
                {
					// 내가 먹은 음식 기록. 최대 100가지의 디저트
                    iAte = new boolean[101];
					// 처음 시작 위치 정함. 처음 음식 먹음 처리.
                    iAte[map[R][C]] = true;
                    dfs(0, R, C);
                    iAte[map[R][C]] = false;
                }
            }
             
             
            System.out.println("#" +t + " " + ans);
        }
        sc.close();
    }
     
    public static void dfs(int deep, int Y, int X) {
         // 3번 꺾었고, 시작 위치에 도달했다면
        if (deep == 3 && R == Y && C == X)
        {    
            int cnt = 0;
			// 내가 먹은 음식 세주기.
            for (boolean ate : iAte)
                if (ate) cnt ++;
             
            if (cnt > ans) ans = cnt;
             
            return ;
        }
         
		// 범위를 벗어나면 종료.
        if (Y < 0 || Y >= N) return ;
             
        if (X < 0 || X >= N) return ;
         
		// 마지막 우 상으로 진행하는 단계.
		// 구분한 이유는, 아래 dfs에서는 deep +1로 진행시켜서 
		// 오류나지 않게 deep < 3으로 설정했으므로. 
        if (deep == 3)
        {
            // 만약 다음이 마지막 이라면 확인 처리 없이 넘어감.
            if (Y+dr[deep] >= 0 && Y+dr[deep] < N && X+dc[deep] >= 0 && X+dc[deep] < N
                    && Y+dr[deep] == R && X+dc[deep] == C)
                dfs(deep, Y+dr[deep], X+dc[deep]);
 
            // 다음이 마지막 아니면, 확인 처리 해줘야 함.
            if (Y+dr[deep] >= 0 && Y+dr[deep] < N && X+dc[deep] >= 0 && X+dc[deep] < N
                    && !alreadyAte(map[Y+dr[deep]][X+dc[deep]]))
            {
                iAte[map[Y+dr[deep]][X+dc[deep]]] = true;
                dfs(deep, Y+dr[deep], X+dc[deep]);
                iAte[map[Y+dr[deep]][X+dc[deep]]] = false;
            }
             
            return ;
        }
         
		// 좌상 진행. 다음으로 진행할 수 있으면 dfs
		// 아래에서 했을 것 같은데 왜 또 썼는지 모르겠다.
        if (deep == 2 && Y+dr[deep+1] >= 0 && Y+dr[deep+1] < N &&
                X+dc[deep+1] >= 0 && X+dc[deep+1] < N && R == Y+dr[deep+1] && C == X+dc[deep+1])
        {
                dfs(deep+1, Y+dr[deep+1], X+dc[deep+1]);
        }
                 
        // 진행방향 진행 가능.
        if (Y+dr[deep] >= 0 && Y+dr[deep] < N && X+dc[deep] >= 0 && X+dc[deep] < N)
        {	// 진행방향 먹었으면
            if (alreadyAte(map[Y+dr[deep]][X+dc[deep]]))
            {	// 꺾을 수 있으면 (꺾는 칸 먹지 않았으면) 꺾고.
                if (deep < 3 && Y+dr[deep+1] >= 0 && Y+dr[deep+1] < N
                        && X+dc[deep+1] >= 0 && X+dc[deep+1] < N
                        && !alreadyAte(map[Y+dr[deep+1]][X+dc[deep+1]]))
                {
                    iAte[map[Y+dr[deep+1]][X+dc[deep+1]]] = true;
                    dfs(deep+1, Y+dr[deep+1], X+dc[deep+1]);
                    iAte[map[Y+dr[deep+1]][X+dc[deep+1]]] = false;
                }
                else // 먹었으면 종료.
                    return ;
            }
            else
            {	// 진행방향 진행 가능하면 진행.
                iAte[map[Y+dr[deep]][X+dc[deep]]] = true;
                dfs(deep, Y+dr[deep], X+dc[deep]);
                iAte[map[Y+dr[deep]][X+dc[deep]]] = false;
            }
        }
         
        // 진행 가능해도 꺾을 수 있음. 꺾을 수 있으면 꺾음.
        if (deep < 3 && Y+dr[deep+1] >= 0 && Y+dr[deep+1] < N &&
                X+dc[deep+1] >= 0 && X+dc[deep+1] < N &&
                !alreadyAte(map[Y+dr[deep+1]][X+dc[deep+1]]))
        {}
            iAte[map[Y+dr[deep+1]][X+dc[deep+1]]] = true;
            dfs(deep+1, Y+dr[deep+1], X+dc[deep+1]);
            iAte[map[Y+dr[deep+1]][X+dc[deep+1]]] = false;
        }
         
    }
     
    public static boolean alreadyAte(int dessert)
    {
        return iAte[dessert];
    }
}