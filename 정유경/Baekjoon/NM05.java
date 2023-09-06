import java.util.Arrays;
import java.util.Scanner;

public class NM05 {
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
		sel = new int[M];
		visited = new boolean[N];
		sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		perm(0,0);
		System.out.println(sb);
		
	}//main
	
	public static void perm(int idx, int sidx) {
		if(sidx == M) {
			for (int val:sel) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(idx == M) return;
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			sel[idx] = arr[i];
			visited[i] = true;
			perm(idx+1, sidx+1);
			visited[i] = false;
		}
		
	} //perm
}
