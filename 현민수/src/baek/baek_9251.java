import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String s1 = br.readLine();
		String s2 = br.readLine();
		int R = s1.length();
		int C = s2.length();
		
		int[][] dp = new int[R+1][C+1];
		
		for(int i=1;i<R+1;i++) {
			for(int j=1;j<C+1;j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.println(dp[R][C]);
		
	}
}
