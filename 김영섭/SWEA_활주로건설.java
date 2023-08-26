import java.util.*;
  
public class Solution {
    // 상하좌우 
    static final int[] dr = new int[] {-1, 1, 0, 0};
    static final int[] dc = new int[] {0, 0, -1, 1};
    static int[][] map, mapTilted;
    static boolean[][] constructed;
    static boolean[][] visited;
    static int N, X, ans; //출발 지점.
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for (int t = 1; t <= T; t++)
        {
            N = sc.nextInt();
            X = sc.nextInt();
            ans = 0;
            // 90도 기울어진 맵을 그냥 동시에 받았음
			// 90도 기울인 맵에 똑같은 작업 수행해주면 되니까
            map = new int[N][N];
            mapTilted = new int[N][N];
             
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    int num = sc.nextInt();
                    map[i][j] = num;
                    mapTilted[j][i] = num;
                }
            }
             // 계산.
            Avail();
             
            System.out.println("#" + t + " " + ans);
        }
        sc.close();
    }
     
    public static void Avail() {
        // 행 선택.

		// 해당 칸에 경사로 건설 여부.
		// 처음에는 필요 없다고 생각했는데
		// 2 1 1 1 2 같은 경우, 건설을 체크하지 않으면 
		// 양쪽 기준으로 중복되게 경사로 지을 수 있다고 해버림
        constructed = new boolean[N][N];
         
        next : for (int i = 0; i < N; i++)
        {
            int idx = -1;
             
            while (++idx < N-1)
            { // 다음 칸이랑 같으면 OK
                if (map[i][idx] == map[i][idx+1]) continue;
                 // 두칸 이상 차이나면 불가능. 다음 줄로 넘어감.
                if (Math.abs(map[i][idx] - map[i][idx+1]) > 1) continue next;
                 // 한칸 차이 나는데 경사로 건설 불가능 시 다음 줄로 넘어감
                if (!slopeAvail(i, idx, map[i][idx] - map[i][idx+1]))
                    continue next;
				// 한칸 차이 나고 경사로 건설 가능하면 경사로 길이만큼 idx 뛰어 넘어 간다.
                if (map[i][idx] - map[i][idx+1] > 0)
                    idx += X-1;
            }
             
            if (idx >= N-1)
            {
                ans ++;
            }
        }

		// 90도 기울어진 맵도 한번 해줌.
		// 코드 중복이 너무 심해서 맵을 매개변수로 받는 형태로 새로 짤까 생각도 했는데
		// 일단 푸는걸 목표로 해서 그냥 남김.
        constructed = new boolean[N][N];

        next : for (int i = 0; i < N; i++)
        {
            int idx = -1;
             
            while (++idx < N-1)
            {
                if (mapTilted[i][idx] == mapTilted[i][idx+1]) continue;
                 
                if (Math.abs(mapTilted[i][idx] - mapTilted[i][idx+1]) > 1) continue next;
                 
                if (!slopeTiltedAvail(i, idx, mapTilted[i][idx] - mapTilted[i][idx+1]))
                    continue next;
                if (mapTilted[i][idx] - mapTilted[i][idx+1] > 0)
                    idx += X-1;
            }
             
            if (idx == N-1)
            {
                ans ++;
            }
        }
 
        constructed = new boolean[N][N];
    }
     
    public static boolean slopeAvail(int r, int c, int dir) {
		// dir 은 왼쪽과 오른쪽의 차이.
		// 왼쪽이 높으면
        if (dir > 0)
        {	// 넘어가면 안됨.
            if (c+1 >= N) return false;
            // 경사로 건설 시작 위치는 c+1, c+1부터 X칸만큼 칸이 같아야함.
            int h = map[r][c+1];
             
            for (int i = 1; i <= X; i++)
            { // 값이 달라지면 false, 이미 경사로 지었어도 false
                if (c+i >= N || map[r][c+i] != h) return false; 
                if (constructed[r][c+i]) return false;
                constructed[r][c+i] = true; // 지었다고 표시.
            }
            return true;
        } // 오른쪽이 높으면 
        else // 기준 높이는 해당 칸, 해당 칸부터 X칸만큼의 높이가 같아야함.
        {
            if (c < 0) return false;
             
            int h = map[r][c];
             
            for (int i = 0; i < X; i++)
            {
                if (c-i < 0 || map[r][c-i] != h) return false; 
                if (constructed[r][c-i]) return false;
                constructed[r][c-i] = true;
            }
            return true;
        }
    }
     // 이것도 사실 위에랑 똑같은 함수임.
    public static boolean slopeTiltedAvail(int r, int c, int dir) {
        // 왼쪽이 더 높으면 pos부터 오른쪽으로 x만큼 유지되는지.
             
        if (dir > 0)
        {
            if (c+1 >= N) return false;
             
            int h = mapTilted[r][c+1];
             
            for (int i = 1; i <= X; i++)
            {
                if (c+i >= N || mapTilted[r][c+i] != h) return false;
                if (constructed[r][c+i]) return false;
                constructed[r][c+i] = true;
            }
            return true;
        }
        else // 오른쪽 높다 - 기준 자신 
        {
            if (c < 0) return false;
             
            int h = mapTilted[r][c];
             
            for (int i = 0; i < X; i++)
            {
                if (c-i < 0 || mapTilted[r][c-i] != h) return false; 
                if (constructed[r][c-i]) return false;
                constructed[r][c-i] = true;
            }
            return true;
        }
    }
}