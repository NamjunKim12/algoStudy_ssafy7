package BAEK_N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class N과M12 {
	static int N, M;
	static Integer[] arr;
	static int[] result;
	static LinkedHashSet<String> set = new LinkedHashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 입력완료

		// 내림차순
		Arrays.sort(arr);
		
		// 내림차순을 할 때는 Collections.reverseOrder를 사용
		// Collections는 int, char 등 기본 타입은 사용하지 못하므로 arr 배열을 Integer[] 로 바꿔주어야 사용 가능하다.
		// String은 기본타입이 아니므로 오류 발생 X
		result = new int[M];
		combination(0, 0);

		for(String str : set) {
			System.out.print(str+"\n");
		}
	}

	static void combination(int idx, int sidx) {
		// 기저
		if (sidx == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i] + " ");
			}
			set.add(sb.toString());
			return;
		}
		if (idx == N)
			return;

		// 재귀
		result[sidx]=arr[idx];
		combination(idx, sidx+1);
		combination(idx+1, sidx);
	}
}