package BAEK_N과M;
// 조합사용

import java.util.Arrays;
import java.util.Scanner;

public class N과M8 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result; // 결과 저장
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		} // 입력 완료

		Arrays.sort(arr); // 오름차순 정렬

		result = new int[M];

		combination(0, 0);
		System.out.println(sb);

	}// main

	static void combination(int idx, int sidx) {
		if (sidx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		if (idx == N)
			return;
		// 기저조건

		result[sidx] = arr[idx];
		combination(idx, sidx+1);
		combination(idx+1, sidx);

		// 재귀조건

	}// combination

}
