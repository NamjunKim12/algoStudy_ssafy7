import java.util.Scanner;

public class NM02 {
	static int N,M;
	static int[] sel;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sel = new int[M];
		sb = new StringBuilder();
		visited = new boolean[N];
		
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
		} //기저
		
		//재귀 부분
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			for (int j = 0; j <= i; j++) {
				visited[j] = true;
			}
				
			sel[sidx] = i+1;
			perm(idx+1, sidx+1);
			
			for (int j = 0; j <= i; j++) {
				visited[j] = false;
			}
		} //재귀
	} //perm
}
