package BAEK_N과M;
// 조합

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class N과M10 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result; // 결과 저장
	static LinkedHashSet<String> hashset = new LinkedHashSet<>();

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

		result = new int[M];

		combination(0, 0);
		
		/* Iterator를 사용 HashSet 배열 출력 */
		Iterator iter = hashset.iterator();
		while(iter.hasNext())
			System.out.print(iter.next()+"\n");
		
		bw.flush();
		bw.close();
	}// main

	static void combination(int idx, int sidx) {
		if (sidx == M) { // 조합 하나를 완성하면
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i <result.length; i++) {
				sb.append(result[i]+" ");
			}
			hashset.add(sb.toString());
			// 만들어진 조합을 hashset에 추가(hashset을 사용했기 때문에중복된 배열은 빼고 저장해줌)
			return;
		}
		if (idx == N)
			return;
		// 기저조건

		result[sidx] = arr[idx];
		combination(idx + 1, sidx + 1);
		combination(idx + 1, sidx); // idx번째 수 추가 안한 것

		// 재귀조건

	}// combination
}
