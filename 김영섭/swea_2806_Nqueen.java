import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    static int N, ans;
    static int[] R;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for (int tc = 1; tc <= T; tc++)
        {
            ans = 0;
            N = sc.nextInt();
            // 0번째는 비우고 사용.
			
            R = new int[N+1];
            
			// 초기 값인 0을 아무런 퀸이 놓여있지 않은 경우로
			// 생각하기 위해서 1부터 시작.
			// deep 값이 R[] 에 들어갑니다. 
			// 해당 R의 deep 번째 칸에 퀸이 놓여있음을 표시.
			// 근데 deep이 0부터 시작하게 되면,
			// 0번째 deep에서 놓은 퀸인지, 애초에 놓여지지 않은 칸인지
			// 구분이 안가므로, 테이블 값을 모조리 -1로 바꾸던가
			// 이런 방식으로 작성해야 한다고 생각했습니다.
            nQueen(1);
             
            System.out.println("#" + tc + " " + ans);
        }
         
        sc.close();
    }
     
    public static void nQueen(int deep) {
        if (deep == N+1) {
            ans ++; 
            return ;
        }
        
		
        for (int i = 1; i <= N; i++) {
			// 이미 퀸이 놓여있으면 건너 뜀.
            if (R[i] != 0) continue;
			// 주변과 비교해서, 놓을 수 없으면 건너뜀.
            if (!validCheck(deep, i)) continue;
			// 놓을 수 있으면 해당 열에 deep 번째 행에 놓아본다. 
            R[i] = deep;
			// 해당 행에 놓은 경우를 계속 확인해봄.
            nQueen(deep + 1);
			// 다음 열에 넣어보는 경우로 넘어감 -> 이번 열에 넣는건 취소.
            R[i] = 0;
        }
    }
     
    public static boolean validCheck(int r, int c) {
        for (int i = 1; i <= N; i++)
        {
			// 주변 칸과 비교해서
			// (i==c, 이번 칸은 비교 안함) 
			// (R[i] == 0, 주변 칸이 빈칸이어도 비교 안함)

            if (i == c || R[i] == 0) continue; 
            // 대각선 위치에 놓여있다면 -> dx = dy 놓기 불가능
            if (Math.abs(R[i] - r) == Math.abs(c - i)) return false;
        }
         
        return true;
    }
}