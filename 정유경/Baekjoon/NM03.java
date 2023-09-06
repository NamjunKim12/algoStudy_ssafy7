import java.util.Scanner;

public class NM03 {
	static int N,M;
	static boolean[] visited;
	static int[] sel;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sel = new int[M];
		visited = new boolean[N];
		sb = new StringBuilder();
		
		perm(0,0);
		System.out.println(sb);
		
	} //main
	
	public static void perm(int idx, int sidx) {
		if(sidx == M) {
			for (int val : sel){
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(idx == M) return;
		
		
		for (int i = 0; i < N; i++) {
			sel[idx] = i+1;
			perm(idx+1, sidx+1);
			perm(idx+1, sidx);
		}
	} //perm
}
