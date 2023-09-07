import java.util.*;
 
public class Solution {
	// 처음에 문제를 이해하느라 개고생했다.
	// 문제 설명이 너무 모호한 것 같다. 물론 틀린 사람 잘못이긴 하지만 ...
	// 문제 이해 시간에 최소 20분은 잡고 꼼꼼히 읽는 습관을 들여야겠다.
	// 연속한 M 칸에서 꿀을 뽑아 먹는다.
	// 근데 이게 사실 M칸이 아닐 수도 있고 연속하지 않을 수도 있다. 
	// 3 2 4 3 1 1 3 에서 M = 3, C = 5인 경우를 생각해보자.
	// "처음 선택하는 영역"이 연속한 M칸이면 된다.
	// 그 안에서 C 보다 작은 만큼 꿀을 골라오면 된다.
	// 즉, 고르는 영역이 3 2 4 일수도 있다. 
	// 그 중 5 만큼만 고르면 된다.
	// 중간 4 3 1 을 고를 수도 있다.
	// 이 중에 4와 1만 따오면 된다.
	// 그러면 "연속한 M" 에서 C 이하의 꿀을 따는 조건이 된다. 
	// 그리고 두 사람은 서로 다른 줄에서 꿀을 골라야 한다.

	// 처음에 문제를 잘못 이해해서, 같은 줄에서도 꿀을 딸 수 있게 한 번
	// 무조건 연속한 M칸을 고르도록 한번, 아예 연속하지 않고 M개의 칸을 고르도록 한번
	// 하여튼 많이 틀렸다.

	// 로직은 각 줄에서 연속한 M칸에서 C 조건을 만족하는 최대 점수를 각각 구해놓고
	// 두 사람이 중복되지 않게 줄을 선택하는 구하는 완탐으로 풀이했다.

    static int N, M, C, ans, R, sum, m;
    static int[][] map;
    static boolean[] visited; // 이 칸을 땄는가?
    static boolean[] visitedLine; // 누군가 이 줄을 방문했는가?
    static int[] maxAns;
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for (int t = 1; t <= T; t++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();
            ans = 0;
             
            map = new int[N][N];
            maxAns = new int[N];
            visitedLine = new boolean[N];
             
			// 맵 받기
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    map[i][j] = sc.nextInt();
             
			// 행 하나를 잡고
            for (R = 0; R < N; R++)
            {	// 해당 칸에서 연속한 M칸을 골라준다.
				// 해당 M칸을 dfs로 전달한다.
				// dfs에서는 해당 M칸에서 최대로 고를 수 있는 꿀을 고른다. 
				// 이때 dfs 내에서는 연속되지 않은 꿀을 고를 수도 있는 것.
                for (int i = 0; i < N; i++)
                {
                    int[] arr = new int[M];
                     
                    for (int j = 0; j < M && i + j < N; j++)
                            arr[j] = map[R][i+j];
                     
                    for (m = 1; m <= M; m++)
                    {	// dfs 내에서 몇 개의 칸을 고를지도 미정이라서
						// 몇개를 고를지를 반복문으로 작성
						// 한개를 고를때의 최대 점수 ... M개를 고를때의 최대 점수...
						// 계산해준다.
						// 따라서 각각의 경우에 방문 처리 초기화.
                        visited = new boolean[M];
                        dfs1(0, arr, 0, 0);
                    }
                }
            }
			// 모든 줄의 최대 점수를 구했다면
			// dfs2에서는 어떤 두 줄을 골라야지 최대 점수를 얻을 수 있는가를 계산한다.
            dfs2(0,0);
 
            System.out.println("#" + t + " " + ans);
        }
        sc.close();
    }
     
    public static void dfs1(int deep, int[] arr, int point, int sum) {
        if (deep == m) // m개 골랐다.
        {	// 점수 계산. 
            if (point > maxAns[R]) maxAns[R] = point;
            return ;
        }
         
        for (int i = 0; i < M; i++)
        {	// 방문 했던가. 꿀이 없던가. 내가 못 챙겨가던가 하면 넘어감.
            if (visited[i] || arr[i] == 0) continue;
            if (sum+arr[i] > C) continue;
             // 챙겼다 처리하고
            visited[i] = true;
             // dfs
            dfs1(deep + 1, arr, point + arr[i]*arr[i], sum+arr[i]);
             // 방문 해제.
            visited[i] = false;
        }
    }
     // 두 줄 고르는 과정.
    public static void dfs2(int deep, int point) {
        if (deep == 2)
        {
            if (point > ans) ans = point;
            return ;
        }
         
        for (int i = 0; i < N; i++)
        {
            if (visitedLine[i]) continue;
            visitedLine[i] = true;
             
            dfs2(deep+1, point + maxAns[i]);
             
            visitedLine[i] = false;
        }
    }
}