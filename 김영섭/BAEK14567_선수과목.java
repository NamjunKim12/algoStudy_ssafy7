import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	// 진입 차수 
	static int[] DEGREE;
	// 이수 학기 기록
	static int[] SEMESTER;
	static ArrayList<Integer>[] NEXT;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		DEGREE = new int[N+1];
		SEMESTER = new int[N+1];
		NEXT = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++)
			NEXT[i] = new ArrayList<> ();
			
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int post = Integer.parseInt(st.nextToken());
			
			NEXT[pre].add(post);
			DEGREE[post]++;
		}
			
		Queue <Integer> queue = new LinkedList<> ();
		
		for (int i = 1; i <= N; i++)
			if (DEGREE[i] == 0) queue.add(i);
		
		int semester = 0;
		
		// 큐 사이즈 끝날 때마다 Semester ++  
		// 큐 사이즈 끝난다 -> 한 학기 종료
		while (! queue.isEmpty()) {
			semester++;
			
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				int curr = queue.poll();
				SEMESTER[curr] = semester;
				
				for (int next : NEXT[curr]) {
					DEGREE[next]--;
					if (DEGREE[next] == 0) {
						queue.add(next);
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(SEMESTER[i]);
			sb.append(' ');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
