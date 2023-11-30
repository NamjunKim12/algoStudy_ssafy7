package BAEK_N과M;
// visited 사용

import java.util.Arrays;
import java.util.Scanner;

public class N과M5 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result; // 결과 저장
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		} // 입력완료
		
		Arrays.sort(arr); // 사전 순으로 증가하는 순서로 출력해야 하므로 오름차순으로 정렬

		result = new int[M];
		visited = new boolean[N];

		perm(0);
		System.out.println(sb);

	}// main

	static void perm(int idx) {
		if (idx == M) { // result 배열을 다 채웠으면 값 저장
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		// 기저조건

		for(int i =0; i<N; i++) {
			if(visited[i]) continue; // 이미 사용한 숫자라면 패스
			
			result[idx]=arr[i]; // i번째 값을 사용하고
			visited[i]=true; // 사용했다고 표시
			perm(idx+1); // 다음 인덱스 채우기
			visited[i]=false; // i번째 값 원상복구
		}
		// 재귀조건
		
	}// perm

}
