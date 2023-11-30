import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] tree;
	static int[] color;
	static boolean[] visit;
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		color = new int[n+1];
		visit = new boolean[n+1];
		ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<n+1;i++) {
			color[i] = Integer.parseInt(st.nextToken());
		}
		tree = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) {
			tree[i] = new ArrayList<Integer>();
		}
		for(int i=1;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		dfs(1,0);
		System.out.println(ans);
	}
	static void dfs(int n,int c) {
		visit[n] = true;
		if(c != color[n]) {
			ans++;
			c=color[n];
		}
		for(int now:tree[n]) {
			if(!visit[now]) {
				dfs(now,c);
			}
		}
	}
}