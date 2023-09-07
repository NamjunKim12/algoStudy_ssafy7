import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class NM11 {
	static int N,M;
	static int[] arr;
	static int[] sel;
	static boolean[] visited;
	static LinkedHashSet<String> set;
	
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
		set = new LinkedHashSet<>();
		
		perm(0,0);
		for(String val:set) {
			System.out.println(val);
		}
	}//main
	
	public static void perm(int idx, int sidx) {
		//기저부분
		if(sidx == M) {
			StringBuilder sb = new StringBuilder();
			for (int val : sel) {
				sb.append(val).append(" ");
			}
			set.add(sb.toString());
			sb.append("\n");
			return;
		}
		if(idx == M) return;
		
		//재귀부분
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			sel[idx] = arr[i];
			perm(idx+1, sidx+1);
		}
		
	}//perm
}
