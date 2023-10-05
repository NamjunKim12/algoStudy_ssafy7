import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	// 진입 차수
	static int[] degree;
	static Building[] BUILDS;
	
	// 해당 건물을 짓는데 필요한 buildTime
	// 해당 건물을 짓기 위한 이전 건물들을 짓는데 필요한 preTime
	static class Building {
		int buildTime, preTime;
		// 다음 건설할 수 있는 건물.
		ArrayList<Integer> nextTech;
		
		public Building() {
			this.nextTech = new ArrayList<> ();
			// 처음 이전 건물 건설 시간 최소로 설정.
			// 원래 값과 비교해서 갱신 예정.
			this.preTime = 0; 
		}
		
		public void addNext(int tech) {
			this.nextTech.add(tech);
		}
		
		public void setBuildTime(int buildTime) {
			this.buildTime = buildTime;
		}
		
		public void setPreTime(int preTime) {
			this.preTime = preTime;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		N = sc.nextInt();
		degree =new int[N+1];
		BUILDS = new Building[N+1];
		
		for (int i = 1; i <= N; i++) {
			BUILDS[i] = new Building();
		}
		
		for (int i = 1; i <= N; i++) {
			int time = sc.nextInt();
			
			BUILDS[i].setBuildTime(time);
			
			int o = sc.nextInt();
			
			while (o != -1) {
				BUILDS[o].addNext(i);

				// 진입차수 입력
				degree[i]++;
				o = sc.nextInt();
			}
		}
		
		Queue <Integer> queue = new LinkedList<> ();
		
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				queue.add(i);
			}
		}
		
		int nextPreTime = 0;
		
		while (! queue.isEmpty()) {
			int curr = queue.poll();
			
			// 다음 건물의 PreTime = 현 건물의 preTime + 현 건물의 BuildTime
			nextPreTime = BUILDS[curr].preTime + BUILDS[curr].buildTime;
			
			for (int i = 0; i < BUILDS[curr].nextTech.size(); i++) {
				int next = BUILDS[curr].nextTech.get(i);
				
				if (nextPreTime > BUILDS[next].preTime)
					BUILDS[next].setPreTime(nextPreTime);
				
				degree[next] --;
				
				if (degree[next] == 0) queue.add(next);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(BUILDS[i].preTime + BUILDS[i].buildTime);
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		sc.close();
	}
}
