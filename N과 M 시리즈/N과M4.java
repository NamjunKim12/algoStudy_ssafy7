package BAEK_N과M;
// 조합 사용

import java.util.Scanner;

public class N과M4 {
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
			arr[i] = i + 1;
		} // 1~N 배열

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

		result[sidx] = arr[idx]; // result배열의 sidx 값에 arr배열의 idx값 넣기
		combination(idx, sidx + 1); // result배열의 다음 인엑스 값에 arr배열의 idx값부터 넣기
		combination(idx + 1, sidx); // idx번째 재료 안뽑은 것
		// 재귀조건
		
	}// combination

}
