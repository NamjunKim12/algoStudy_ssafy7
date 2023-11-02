import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] parents;
	static int[] depth;
	static ArrayList<Integer>[] tree;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		visit = new boolean[N+1];
		depth = new int[N+1];
		tree = new ArrayList[N+1];
		
		for(int i=0;i<N+1;i++) {
			tree[i] = new ArrayList<Integer>();
		}
		Arrays.fill(depth, -1);
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		dfs(1,1,0);
		M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(LCA(a, b)).append(("\n"));
		}
		System.out.println(sb);
	}
	
	 public static void dfs(int current, int dep, int parent){
	        parents[current] = parent;
	        depth[current] = dep;
	        visit[current] = true;
	        for(int next : tree[current]){
	            if(visit[next]) continue;
	            dfs(next, dep+1,current);
	        }
	}

	public static int LCA(int a, int b) {
		while(depth[a]!=depth[b]) {
			if(depth[a]>depth[b]) {
				a = parents[a];
			}
			else {
				b = parents[b];
			}
		}
		while(a != b) {
			a = parents[a];
			b = parents[b];
		}
		return a;
	}
}