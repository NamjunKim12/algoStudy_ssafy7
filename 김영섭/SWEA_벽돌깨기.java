import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
  
public class Solution {
    // 상하좌우 
    static final int[] dr = new int[] {-1, 1, 0, 0};
    static final int[] dc = new int[] {0, 0, -1, 1};
    static int[][] map, newMap;
    static int N, W, H, ans; 
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for (int t = 1; t <= T; t++)
        {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            map = new int[H][W];
            newMap = new int[H][W];
             
            // 초기 map 받아오기 
            for (int i = 0; i < H; i++)
                for (int j = 0; j < W; j++)
                    map[i][j] = sc.nextInt();
             
            mapReset();
                         
            ans = mapCount();
             
            dfs(0, "");
             
            System.out.println("#" + t + " " + ans);
        }
        sc.close();
    }
     
    public static void dfs(int deep, String path) {
        if (deep == N)
        {
            mapReset();
             
            String[] boomPos = path.split(" ");
 
            int cnt = mapCount();
             
            if (cnt == 0)
            {
                ans = cnt;
                return ;
            };
             
            for (int i = 0; i < N; i++)
            {
				// 틀려서 개고생했던 부분. 계속 테케 49개 나오길래 몇 시간 고생했다.
				// path를 받아와서 종료 직전에 문자를 하나씩 돌면서
				// 해당 위치에 폭탄을 떨어트리는 식으로 구상했었다. 
				// 근데 처음에 그 위치를 받는 걸 path.charAt(i) - '0' 로 받았었다. 
				// 주어진 위치가 한자리라면 문제가 안되지만, 두자리면 당연히 안된다 (한자리만 읽어오니까)
				// 저번에도 비슷한 실수를 했던 것 같은데 멍총하게 똑같은 실수를 다시 했다.
				// 그래서 path 전달 시 구분자로 공백 하나 추가해주고 마지막에 split하는 방법으로 바꾸니까 되더라
				
                boom(Integer.parseInt(boomPos[i]));
                mapOrganize(); // 폭탄 터지고나면 맵 정리해주기.
                cnt = mapCount(); // 맵 남은 개수 세주기. 
                if (cnt == 0) // 0이면 더 안한다.
                { 
                    ans = cnt;
                    return ;
                };
            }
            // 현재 최소값과 비교해서 계산하기.
            if (cnt < ans) ans = cnt;
             
            return ;
        }
         // 간단하게 3번의 선택 (중복 선택 허용) 넘겨서 마지막에 계산해서 출력하는 방식으로 구현.
		 // dfs에서 한번 터트리고, 다음 deep에서 다시 터트리고, 다음 Deep에서 다시 터트리고
		 // 하는 방식으로 할 수도 있었겠지만, 맵을 유지하고 새로 저장하고 하는 부분이 너무 힘들 것 같아서
		 // 이렇게 구현했다. 
        for (int i = 0; i < W; i++)
        {
            dfs(deep+1, path+i+" ");
        }
    }
     
	// 이건 새로운 순열의 순서로 폭탄을 터트리기 전에 맵을 다시 로드하는 함수.
    public static void mapReset() {
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                newMap[i][j] = map[i][j];
    }
     // 폭탄이 터진 뒤, 위에 남은 블록을 아래로 떨어트려주는 함수.
    public static void mapOrganize() {
        for (int i = 0; i < W; i++)
        { // 아래부터 돌면서, 블록이 있으면 하나씩 쌓아주는.
            int stacked = H-1;
            for (int j = H-1; j >= 0; j--)
            {
                if (newMap[j][i] != 0)
                {
                    newMap[stacked][i] = newMap[j][i];
                    if (stacked-- != j)newMap[j][i] = 0;
                }
            }
        }
    }
     // Column 위치를 받아 터트리는 함수. 
    public static void boom(int pos) {
        int r = 0;
         // 블록이 나올때까지 떨어트린다. 
        while (r < H && newMap[r][pos] == 0) r++;
         
        // 바닥에 떨어짐.
        if (r == H) return ;
         // 바닥에 안떨어졌으면 벽돌에 맞아야함. 이는 bfs와 같은 방식으로 구현한다.
		 // 해당 칸에 폭탄을 터트린다고 주변 칸들을 먼저 0으로 바꿔버리면 
		 // 주변 칸들이 얼마의 범위로 터져야하는지를 알 수 없다.
		 // 때문에, 먼저 터트려야하는 칸과 그 칸의 값을 큐에 넣어놓고 0으로 바꾸고,
		 // 큐에서 하나씩 뺴서 터트리고, 그 주변 칸도 다시 큐에 넣는 방식이다. 
		 // 이렇게 큐 사이즈가 0일때까지 반복하면 모든 터질 블록이 터졌다는 뜻.
        Queue <int[]> queue = new LinkedList<> ();
         // 맨 처음 타겟 블록을 넣고, 0으로 값을 바꿔준다. 이후 뺄 예정.
        queue.add(new int[] {r, pos, newMap[r][pos]});
        newMap[r][pos] = 0;
         
        while (queue.size() != 0)
        {
		// 터질 블록 하나를 뽑는다. 해당 칸의 값만큼 주변으로 확장하며
		// 주변 터지는 칸의 위치와 값을 큐에 다시 넣고, 해당 칸을 0으로
            int[] info = queue.poll();
            int y = info[0];
            int x = info[1];
            int range = info[2];
             
            int d = -1;
             
            while (++d < range)
            {
                // 값 0이 아니면 터진다.
                for (int i = 0; i < 4; i++)
                { // 범위 벗어나는거 체크
                    if (y+d*dr[i] < 0 || y+d*dr[i] >= H) continue;
                    if (x+d*dc[i] < 0 || x+d*dc[i] >= W) continue;
                     // 터져야한다면 큐에 넣고 값을 0으로 바꿔 터트린다.
                    if (newMap[y+d*dr[i]][x+d*dc[i]] != 0)
                    {
                        queue.add(new int[] {y+d*dr[i], x+d*dc[i], newMap[y+d*dr[i]][x+d*dc[i]]});
                        newMap[y+d*dr[i]][x+d*dc[i]] = 0;
                    }
                }
            }
        }
    }
     // 맵을 출력하는 함수. 점검용으로 잘 사용했음.
    public static void mapPrint() {
        for (int i = 0; i < H; i++)
            System.out.println(Arrays.toString(newMap[i]));
    }
     // 마지막 남은 블록을 세는 함수.
    public static int mapCount() {
        int cnt = 0;
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (newMap[i][j] != 0) cnt++;
         
        return cnt;
    }
}