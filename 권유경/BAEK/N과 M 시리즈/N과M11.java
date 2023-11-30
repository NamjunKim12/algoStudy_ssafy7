package BAEK_N과M;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class N과M11 {
	static int N, M;
	static int[] arr;
	static int[] result;
	static boolean[] visited;
	static LinkedHashSet<String> hashset = new LinkedHashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		result = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순
		Arrays.sort(arr);

		perm(0);

		for (String str : hashset) {
			bw.write(str+"\n");
		}
		bw.flush();
		bw.close();
	}// main

	static void perm(int idx) {
		// 기저
		if (idx == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i] + " ");
			}
			hashset.add(sb.toString());
			return;
		}

		// 재귀
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			result[idx] = arr[i];
			perm(idx + 1);
		}
	}
}