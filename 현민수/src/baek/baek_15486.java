import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class table{
		int time;
		int profit;
		public table(int time, int profit) {
			this.time = time;
			this.profit = profit;
		}
		@Override
		public String toString() {
			return "table [time=" + time + ", profit=" + profit + "]";
		}
	}
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[] dp = new int[N+2];
		table[] consult = new table[N+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			consult[i] = new table(t,p);
		}
		
		for(int i=1;i<N+1;i++) {
			int time = consult[i-1].time;
			int profit = consult[i-1].profit;
			dp[i] = Math.max(dp[i], dp[i-1]);
			if(i+time<N+2)
			{
				dp[i+time] = Math.max(dp[i+time], profit+dp[i]);
			}
			
		}
		System.out.println(Math.max(dp[N], dp[N+1]));
		
		
	}
}