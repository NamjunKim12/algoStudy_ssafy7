package BAEK_N과M;
// swap 을 사용하였더니 사전순으로 정렬이 되지 않았음.
import java.util.Scanner;

public class N과M1 {
	static int N; // N개중에
	static int M; // M개의 숫자 조합
	static int[] arr; // 숫자 배열
	static int[] result; // 결과 저장
	static boolean[] visited; // 사용 여부 확인

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 1부터 N 중에
		M = sc.nextInt(); // M개의 숫자 고르기

		result = new int[M];
		// M개의 숫자조합을 저장해야 하므로 크기는 M
		
		visited = new boolean[N]; 
		// 1부터 N까지의 숫자 중의 사용 여부를 확인해야 하므로 크기는 N
		
		arr = new int[N];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		} // 1부터 N까지의 배열 만들기

		perm(0);

	}// main

	public static void perm(int idx) {
		// 기저조건
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println(); // 저장한 순열 출력
			return;
		}

		// 재귀조건
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue; // 이미 사용한 숫자라면 다음 숫자 확인하자.

			result[idx] = arr[i]; // 사용하지 않은 숫자라면 result 배열에 값을 넣어주자.
			visited[i] = true; // 사용한 숫자를 사용했다고 표시하자.
			perm(idx + 1); // 다음 인덱스의 값을 채우자.
			visited[i] = false; // 결과를 출력했으면 원상복구 시키자.
		}
	}// perm

}
