package BAEK_N과M;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class N과M9 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result; // 결과 저장
	static boolean[] visited;
	static List<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		} // 입력 완료

		Arrays.sort(arr); // 오름차순 정렬
		list = new ArrayList<>();

		result = new int[M];
		visited = new boolean[N];

		perm(0);

		for(int[] A : list) {
			for(int a : A) {
				bw.write(a+ " ");
			}bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}// main

	static void perm(int idx) {
		if (idx == M) {
			for (int i = 0; i < list.size(); i++) {
				// list 를 순회하며 만들어진 수열과 같은 것이 있다면
				if (Arrays.equals(list.get(i), result)) {
					return; // 리턴
				}
			}// 같은 수열이면 리턴
			
			list.add(result.clone()); // 만들어진 수열을 list에 추가
			return;
		}// 기저조건
		
		
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			result[idx] = arr[i];
			visited[i] = true;
			perm(idx + 1);
			visited[i] = false;
		}
		// 재귀조건

	}// combination
}
