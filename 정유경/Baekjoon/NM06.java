import java.util.Arrays;
import java.util.Scanner;

public class NM06 {
	static int N,M;
	static int[] arr;
	static int[] sel;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		sel = new int[M];
		visited = new boolean[N];
		sb = new StringBuilder();
		
		perm(0,0);
		System.out.println(sb);
		
	}//main

	
	public static void perm(int idx, int sidx) {
		//기저조건
		if(sidx == M) {
			for (int val : sel) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
		}
		
		if(idx == M) return;
		
		//재귀
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			sel[idx] = arr[i];
			perm(idx+1, sidx+1);
			for(int j=i+1; j<N; j++) {
				visited[j] = false;
			}
		}
	}
}
