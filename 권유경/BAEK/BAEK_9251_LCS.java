package BAEK_9251_LCS;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 첫번째 문자열
		String x = sc.next();
		char[] X = x.toCharArray();

		// 두번째 문자열
		String y = sc.next();
		char[] Y = y.toCharArray();

		// 이차원 배열 생성
		int[][] dp = new int [X.length+1][Y.length+1];
		
		
		for(int i = 1; i<X.length+1; i++) {
			for(int j = 1; j<Y.length+1; j++) {
				// 만약 두 수가 다르다면
				if(X[i-1]!=Y[j-1]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				// 만약 두 수가 같다면
				else {
					dp[i][j] = dp[i-1][j-1]+1;
				}
			}
		}
//		for(int[] a : dp)
//		System.out.println(Arrays.toString(a));
		
		// 답 출력
		System.out.println(dp[X.length][Y.length]);

	}
	// main

}
