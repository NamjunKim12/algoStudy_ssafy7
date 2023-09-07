import java.util.Scanner;

public class NM04 {
	static int N,M;
	static boolean[] visited;
	static int[] sel;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N];
		sel = new int[M];
		sb = new StringBuilder();
		
		perm(0,0);
		System.out.println(sb);
		
	} //main
	
	public static void perm(int idx, int sidx) {
		//기저 부분
		if(sidx == M) {
			for (int val : sel) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(idx == M) return;
		
		//재귀 부분
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			for (int j = 0; j < i; j++) {
				visited[j] = true;
			}
			sel[idx] = i+1;
			perm(idx+1, sidx+1);
			
			for (int j = i; j < N; j++) {
				visited[j] = false;
			}
		}
	} //perm
}
