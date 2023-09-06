import java.util.Scanner;

public class NM01 {
	static int  N,M;
	static int[] sel;
	static boolean[] visited;
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
	}
	
	public static void perm(int idx, int sidx) {
		
		if(sidx == M) {
			//sysout으로 출력하면 시간초과 뜸
//			for (int i = 0; i < sel.length; i++) {
//				System.out.print(sel[i]+ " ");
//			}
//			System.out.println();
			for(int val: sel) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(idx == M) return;
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[sidx] = i+1;
				perm(idx+1, sidx+1);
				visited[i] = false;
			}
			
		}
		
	}
}
