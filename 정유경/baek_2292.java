package bronze;

import java.util.Scanner;

public class baek_2292 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int index = 1; //방 수
		int range = 2; //범위
		
		
		if(N == 1)
			System.out.println(1);
		else {
			// 범위가 N보다 커지기 전까지 반복
			while(range <= N) {
				range += (6*index);
				index++;
			}
			System.out.println(index);
		}
	}
}
