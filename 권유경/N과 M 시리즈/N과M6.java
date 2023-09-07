package BAEK_N과M;
// 조합사용

import java.util.Arrays;
import java.util.Scanner;

public class N과M6 {
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
		for(int i = 0; i<N; i++) {
			arr[i]=sc.nextInt();
		} // 입력 완료

		Arrays.sort(arr); // 오름차순 정렬
		
		result = new int[M];
		
		combination(0,0);
		System.out.println(sb);
		
	}// main

	static void combination(int idx, int sidx) {
		if(sidx == M) { // result 배열의 인덱스 값이 배열을 벗어나면 result배열 값 저장
			for(int i =0 ;i<M; i++) {
				sb.append(result[i]+ " ");
			}sb.append("\n");
			return; // 리턴
		}
		if(idx==N) return; // 모든 숫자를 다 처리했으면 리턴
		// 기저조건
		
		result[sidx]=arr[idx]; // result배열에 idx번째 값 저장하고
		combination(idx+1, sidx+1); // 다음 인덱스에 idx+1 번째 값 저장
		combination(idx+1, sidx); // idx번째 값 사용 X
		// 재귀조건
		
	}// combination

}
