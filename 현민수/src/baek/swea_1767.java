import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N,len,ans,total;
	static int[][] map;
	static int maxCore;
	static ArrayList<int[]> core;
	static int[] dr,dc;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		dr = new int[] {0,0,1,-1};
		dc = new int[] {1,-1,0,0};
		StringTokenizer st;
		for(int tc=1;tc<T+1;tc++) {
			N =  Integer.parseInt(br.readLine());
			map = new int[N][N];
			total=0;
			core = new ArrayList<int[]>();
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						total++;
						if(i!=0 && j!=0 && i!=N-1 && j!=N-1) {
							core.add(new int[] {i,j});
						}
					}
			}
			}
			
			maxCore = 0;
			ans = Integer.MAX_VALUE;
			len = core.size();
			dfs(0,0);
			System.out.println("#"+tc+" "+(ans-total));
			
		}
	}
	
	static void dfs(int cnt, int count) {
		if((total-cnt)+count<maxCore)
			return;
		if(cnt == len) {
			
			if(count > maxCore) {
				maxCore = count;
				int sum=0;
				for(int i=0;i<N;i++)
					for(int j=0;j<N;j++)
						if(map[i][j]==1) sum++;
				ans = sum;
			}
			else if(count==maxCore) {
				int sum=0;
				for(int i=0;i<N;i++)
					for(int j=0;j<N;j++)
						if(map[i][j]==1) sum++;
				ans = Math.min(ans, sum);
			}

			return;
		}
		int[][] tempMap = new int[N][N];
		init(tempMap,map);
		int r = core.get(cnt)[0];
		int c = core.get(cnt)[1];
		for(int i=0;i<4;i++) {

			if(connect(r,c,i)) {
				dfs(cnt+1,count+1);
				init(map,tempMap);
			}
			else {
				init(map,tempMap);
				dfs(cnt+1,count);
			}
			
			
		}
	}
	
	static boolean connect(int r,int c,int d) {
		int nr = r;
		int nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nc<0 || nr>N-1 || nc >N-1) break;
			if(map[nr][nc]!=0) return false;
			map[nr][nc]=1;
		}
		return true;
	}
	
	static void init(int[][] p, int[][] o) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				p[i][j] = o[i][j];
			}
		}
	}
}