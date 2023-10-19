import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] DP;
	static int N, K;
	static List<Integer> coin = new ArrayList<> ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		DP = new int[K+1];
		DP[0] = 1;
		
		for (int i = 0; i < N; i++)
			coin.add(Integer.parseInt(br.readLine()));
		
		Collections.sort(coin);
		
		for (int c : coin) {
			
			if (c > K) break;

			// 이전까지 더해서 만들 수 있던 경우의 수 + 
			// 현재 내가 코인을 하나 추가해서 만들 수 있는 경우의 수.
			for (int i = c; i <= K; i++) {
				DP[i] = DP[i] + DP[i-c];
			}	
		}
		
		System.out.println(DP[K]);
		
		br.close();
	}
}
