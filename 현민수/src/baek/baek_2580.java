import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class zero{
		int r;
		int c;
		public zero(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map,ans;
	static int R,C;
	static int count;
	static int last;
	static int flag;
	static ArrayList<zero> sdoku;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		R = 9;
		C = 9;
		map = new int[R][C];
		ans = new int[R][C];
		sdoku = new ArrayList<>();
		for(int i=0;i<9;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) {
					sdoku.add(new zero(i,j));
				}
			}
		}
		last = sdoku.size(); 
		dfs(0);
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		} 
			
	}
	static void dfs(int cnt) {
		if(flag==1)
			return;
		if(cnt==last) {
			flag=1;
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++)
					ans[i][j] = map[i][j];
			System.out.println();
			return;
		}

		int r = sdoku.get(cnt).r;
		int c = sdoku.get(cnt).c;
		for(int j=1;j<=9;j++) {
			if(garo(r,j) && sero(c,j)&& nemo(r,c,j)) {
				map[r][c] = j;
				dfs(cnt+1);
				map[r][c]=0;
			}
		}
	

	}
	
	static boolean garo(int r,int num) {
		for(int i=0;i<9;i++) {
			if(map[r][i]==num) {
				return false;
			}
		}
		return true;
	}
	
	static boolean sero(int c,int num) {
		for(int i=0;i<9;i++) {
			if(map[i][c]==num) {
				return false;
			}
		}
		return true;
	}
	
	static boolean nemo(int r,int c,int num) {
		int mr = r%3;
		int mc = c%3;
		for(int i=r-mr;i<r-mr+3;i++) {
			for(int j=c-mc;j<c-mc+3;j++) {
				if(map[i][j]==num) {
					return false;
				}
			}
		}
		return true;
	}
	
}