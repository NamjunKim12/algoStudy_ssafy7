import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int[][] queen;
	static int n,ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<T+1;tc++) {
			ans=0;
			n = Integer.parseInt(br.readLine());
			queen = new int[n][2];
			chess(0);
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	static void chess(int cnt) {
		if(cnt==n) {
			ans++;
			return;
		}
		for(int i=0;i<n;i++) {
			if(check(cnt,i)) {
				chess(cnt+1);
			}			
		}
	}
	
	static boolean check(int idx, int row ) {
		boolean possible = true;
		for(int i=0;i<idx;i++) {
			if(queen[i][1]==row) return false;
			else if(Math.abs(queen[i][1]-row) == Math.abs(idx-queen[i][0])) return false;
		}
		if(possible)
			{
				queen[idx][0]=idx;
				queen[idx][1]=row;
			}
		return possible;
	}

}