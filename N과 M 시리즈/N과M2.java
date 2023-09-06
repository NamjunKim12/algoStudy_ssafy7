package BAEK_N과M;

// 조합 사용
import java.util.Scanner;

public class N과M2 {
	static int N;
	static int M; 
	static int[] arr; // 1~N 숫자 배열
	static int[] sel; // 숫자를 조합한 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		} // 1부터 N까지의 숫자 배열 만들기

		sel = new int[M]; // 조합한 배열
		
		combination(0,0); // 조합하기

	}// main

	public static void combination(int idx, int sidx) {
		if (sidx == M) { // 조합한 배열의 인덱스가 M이 된다면 sel배열을 완성했다는 의미
			for (int i = 0; i < M; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println(); // 결과를 출력해주고
			return; // 리턴
		} 
		
		if(idx==N)return; // 모든 숫자를 다 조합했으면 리턴
		
		sel[sidx]=arr[idx]; // 해당 숫자를 sel 배열에 넣고
		combination(idx+1, sidx+1); // idx 번째 재료 뽑은거
		combination(idx+1, sidx); // idx 번째 재료 안뽑은거
				
	}// 조합
}
