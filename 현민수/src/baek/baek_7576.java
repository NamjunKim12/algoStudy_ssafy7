import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static class tomato{
		int r;
		int c;
		public tomato(int r,int c) {
			this.r= r;
			this.c = c;
		}
	}
	static ArrayDeque<tomato> que;
	static int[][] map;
	static int R,C;
	static int[] dr,dc;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int startr=0;
		int startc=0;
		que = new ArrayDeque<>();
		dr = new int[] {0,0,-1,1};
		dc = new int[] {1,-1,0,0};
		map = new int[R][C];
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					que.add(new tomato(i,j));
				}
			}
		}

		int ans = 0;
		bfs();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]==0) {
					System.out.println(-1);
					return;
				}
				if(map[i][j]>ans) ans=map[i][j];
			}
		}
		System.out.println(ans-1);
		
	}
	static void bfs() {
		while(!que.isEmpty()) {
			tomato temp = que.poll();
			int r = temp.r;
			int c = temp.c;
			int day = map[r][c];
			for(int i=0;i<4;i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(nr<0 || nc<0 || nr>R-1 || nc>C-1) continue;
				if(map[nr][nc]==0) {
					map[nr][nc]=day+1;
					que.add(new tomato(nr,nc));
				}
			}
		}
	}

}
