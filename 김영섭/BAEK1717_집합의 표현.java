import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int B[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		
        StringTokenizer st = new StringTokenizer(br.readLine());
        
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		B = new int[N+1];
		
		for (int i = 0; i <= N; i++) {
			B[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			if (0 == Integer.parseInt(st.nextToken())) {
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			else {
				String res = (find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken()))) ? "YES" : "NO";
				sb.append(res);
                sb.append('\n');
			}
		}
		
        System.out.println(sb.toString());
		br.close();
	}
	
	// 대표자 찾기 함수.
	public static int find(int node) {
		if (node == B[node]) {
			return node;
		}
		// 최적화 
		// 최적화 안하면 4472ms / 최적화 하면 416ms
		return (B[node] = find(B[node]));
	}
	
	public static void union(int nodeA, int nodeB) {
		int a = find(nodeA);
		int b = find(nodeB);
		
		if (a != b) {
			B[b] = a;
		}
	}
}