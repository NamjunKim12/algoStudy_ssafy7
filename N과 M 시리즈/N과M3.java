package BAEK_N과M;
// perm 메소드 안에 
// System.out.println(); 으로 조합 하나하나를 출력했더니 시간초과가 나서 StringBuilder를 사용하여 조합 하나하나를 추가했더니
// 시간초과가 나지 않았음

import java.util.Scanner;

public class N과M3 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		result = new int[M];
		arr = new int[N];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}

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
		} // 기저조건

		for (int i = 0; i < N; i++) {
			result[idx] = arr[i];
			perm(idx + 1);
		}
	}// perm
}
