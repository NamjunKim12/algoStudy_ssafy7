import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int N = sc.nextInt();
        // 인덱스로 바로 편하게 접근하기 위해서 +1 했습니다.
        int[] switches = new int[N+1]; 
        
		// 스위치 입력
        for (int i = 1; i <= N; i++)
            switches[i] = sc.nextInt();
		
        int P = sc.nextInt();
        
		// 사람 성별과 넘버 받음
        for (int i = 0; i < P; i++)
        {
            int gender = sc.nextInt();
            int num = sc.nextInt();
             // 남성의 경우.
            if (gender == 1)
            {   // num부터 num씩 건너뛰면서 진행했습니다. 인덱스 N+1로 했으니 등호 포함.
                for (int j = num; j <= N; j += num)
                {
                    if (switches[j] == 1) switches[j] = 0;
                    else switches[j] = 1;
                }
            } // 여성의 경우.
            else
            {	// 현재칸 토글해주기 , 아래에서 진행하면 토글 두번하게 되어서 따로 뺴주었습니다.
                if (switches[num] == 1) switches[num] = 0;
                else switches[num] = 1;
                 // 양쪽으로 1씩 진행하며 토글하기.
                int j = 1;
                while (num+j <= N && num-j > 0)
                {
                    if (switches[num+j] == 1) switches[num+j] = 0;
                    else switches[num+j] = 1;
                     
                    if (switches[num-j] == 1) switches[num-j] = 0;
                    else switches[num-j] = 1;
                    j++;
                }
            }
        }
         
        int i = 0;
         // 20개씩 끊어서 출력하는 부분
        while (20*(i) < N)
        {
            for (int j = 1; j <= 20 && 20*i + j <= N; j ++)
                System.out.print(switches[20*i + j] + " ");
            System.out.println();
            i++;
        }
         
        sc.close();
    }
}