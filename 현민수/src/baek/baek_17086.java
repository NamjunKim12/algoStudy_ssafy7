import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int[][] map,init;
	static int R,C;
	static int[] dr,dc;
	static class shark{
		int r;
		int c;
		
		public shark(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		dr = new int[] {-1,1,0,0,1,1,-1,-1};
		dc = new int[] {0,0,-1,1,1,-1,1,-1};
		map = new int[R][C];
		init = new int[R][C];
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				init[i][j] = map[i][j];
			}
		}
		
		int ans = 0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]==0) {
					ans = Math.max(ans,bfs(i,j));
//					initMap();
				}
			}
		}
		System.out.println(ans);
		
	}
	static void initMap() {
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++)
				map[i][j] = init[i][j];
	}
	static int bfs(int r, int c) {
		ArrayDeque<shark> que = new ArrayDeque<>();
		que.add(new shark(r,c));
		int cnt=1;
		boolean[][] visit = new boolean[R][C];
		visit[r][c] = true;
		while(!que.isEmpty()) {
			int len = que.size();
			for(int i=0;i<len;i++) {
				shark temp = que.poll();
				r= temp.r;
				c = temp.c;
				for(int j=0;j<8;j++) {
					int nr = r+dr[j];
					int nc = c+dc[j];
					if(nr<0 ||nc<0 ||nr>R-1 ||nc>C-1)continue;
					if(map[nr][nc]==1 && !visit[nr][nc]) {
						return cnt;
					}
					if(!visit[nr][nc]) {
						visit[nr][nc] = true;
						que.add(new shark(nr,nc));
					}

				}
			}
			cnt++;
			
		}
		return cnt;
	}

}
