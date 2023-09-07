package BAEK_N과M;

import java.util.Arrays;
import java.util.Scanner;

public class N과M7 {
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

		perm(0);
		System.out.println(sb);

	}// main

	static void perm(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		// 기저조건

		for (int i = 0; i < N; i++) {
			result[idx] = arr[i];
			perm(idx+1);

		}// 재귀조건
		
		
	}// combination

}
