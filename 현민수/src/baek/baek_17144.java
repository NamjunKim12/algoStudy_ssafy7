package baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17144_G4_미세먼지안녕_현민수 {
	
	
	static int R,C,T;
	static int[][] map;
	static int ans;
	static ArrayDeque<dust> que;
	static int[] dr,dc;
	static int[][] cleaner;
	static class dust{
		int r,c,value;

		public dust(int r, int c, int value) {
			super();
			this.r = r;
			this.c = c;
			this.value = value;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());  // 바퀴 수
		dr = new int[] {0,0,-1,1};
		dc = new int[] {1,-1,0,0};
		cleaner = new int[2][2];
		map = new int[R][C];
		int idx = 0;
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					cleaner[idx][0] = i;
					cleaner[idx++][1] = j;
				}
			}
		}
		que = new ArrayDeque<>();
		for(int tc=0;tc<T;tc++) {
			for(int i=0;i<R;i++)
				for(int j=0;j<C;j++)
					if(map[i][j]>0)
						que.add(new dust(i,j,map[i][j]));
			spread();  // 확산
				
			turn();//회전

		}
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++)
				if(map[i][j]>0)
					ans+=map[i][j];
		System.out.println(ans);
		
	}
	static void spread() {
		while(!que.isEmpty()) {
			dust temp = que.poll();
			int cnt=0;
			int r = temp.r;
			int c = temp.c;
			int v = temp.value;
			int add = v/5;
			for(int i=0;i<4;i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr<0 || nc<0 || nr>R-1 || nc>C-1 || map[nr][nc]==-1)continue;
				map[nr][nc] += add;
				map[r][c] -= add;
			}
		}
	}
	static void turn() {
		//위에
		int topR = cleaner[0][0];
		int topC = cleaner[0][1]+1;
		int[] topDr = new int[] {0,-1,0,1};
		int[] topDc = new int[] {1,0,-1,0};
		int dir = 0;
		int temp = map[topR][topC];
		map[topR][topC] = 0;
		while(true) {
			int nr = topR + topDr[dir];
			int nc = topC + topDc[dir];
			
			if(nr<0 || nc <0 || nr>R-1 || nc >C-1) {
				dir++;
				continue;
			}
		
			if(map[nr][nc]==-1) {
				break;
			}
			int t = map[nr][nc];
			map[nr][nc] = temp;
			temp = t;
			topR = nr;
			topC = nc;
		}
		//밑에
		int botR = cleaner[1][0];
		int botC = cleaner[1][1]+1;
		int[] botDr = new int[] {0,1,0,-1};
		int[] botDc = new int[] {1,0,-1,0};
		dir = 0;
		temp = map[botR][botC];
		map[botR][botC] = 0;
		while(true) {
			int nr = botR + botDr[dir];
			int nc = botC + botDc[dir];
			
			if(nr<0 || nc <0 || nr>R-1 || nc >C-1) {
				dir++;
				continue;
			}
		
			if(map[nr][nc]==-1) {
				
				break;
			}
			int t = map[nr][nc];
			map[nr][nc] = temp;
			temp = t;
			botR = nr;
			botC = nc;
		}
	}
	
}
