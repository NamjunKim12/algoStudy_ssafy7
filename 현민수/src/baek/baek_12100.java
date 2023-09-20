import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int dir,N,ans;
	static int[] dr,dc;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		dr = new int[] {0,0,-1,1}; // 좌 우 위 아래
		dc = new int[] {-1,1,0,0};
		ans=0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move(0);
		System.out.println(ans);
		
		
	}
	static void init(int[][] p, int[][] q) {
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				p[i][j] = q[i][j];
	}
	
	static int maxValue() {
		int temp=0;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(map[i][j]>temp) {
					temp=map[i][j];
				}
		return temp;
	}
	
	static void sum(int dir) {
		
		int [][] sumMap = new int[N][N];

		if(dir==0) {

			for(int i=0;i<N;i++) {
				int idx=0;
				ArrayDeque<Integer> que = new ArrayDeque<Integer>();
				for(int j=0;j<N;j-=dc[dir]) {
					if(map[i][j]==0) continue;
					if(que.isEmpty()) {
						que.offer(map[i][j]);
						continue;
					}
					if(que.peekLast()==map[i][j]) {
						int a = 2*que.pollLast();
						que.offer(a);
						while(!que.isEmpty()) {
							sumMap[i][idx++] = que.poll();
						}
					}
					else {
						que.offer(map[i][j]);
					}
				}
				while(!que.isEmpty()) {
					sumMap[i][idx++] = que.poll();
				}
			}
			
			
		}
		else if(dir==1) { // 우
			for(int i=0;i<N;i++) {
				int idx=N-1;
				ArrayDeque<Integer> que = new ArrayDeque<Integer>();
				for(int j=N-1;j>-1;j-=dc[dir]) {
					if(map[i][j]==0) continue;
					if(que.isEmpty()) {
						que.offer(map[i][j]);
						continue;
					}
					if(que.peekLast()==map[i][j]) {
						int a = 2*que.pollLast();
						que.offer(a);
						while(!que.isEmpty()) {
							sumMap[i][idx--] = que.poll();
						}
					}
					else {
						que.offer(map[i][j]);
					}
				}
				while(!que.isEmpty()) {
					sumMap[i][idx--] = que.poll();
				}
			}
		}
		else if(dir==2) { //위
			for(int i=0;i<N;i++) {
				int idx=0;
				ArrayDeque<Integer> que = new ArrayDeque<Integer>();
				for(int j=0;j<N;j-=dr[dir]) {
					if(map[j][i]==0) continue;
					if(que.isEmpty()) {
						que.offer(map[j][i]);
						continue;
					}
					if(que.peekLast()==map[j][i]) {
						int a = 2*que.pollLast();
						que.offer(a);
						while(!que.isEmpty()) {
							sumMap[idx++][i] = que.poll();
						}
					}
					else {
						que.offer(map[j][i]);
					}
				}
				while(!que.isEmpty()) {
					sumMap[idx++][i] = que.poll();
				}
			}
		}
		else {
			for(int i=0;i<N;i++) {
				int idx=N-1;
				ArrayDeque<Integer> que = new ArrayDeque<Integer>();
				for(int j=N-1;j>-1;j-=dr[dir]) {
					if(map[j][i]==0) continue;
					if(que.isEmpty()) {
						que.offer(map[j][i]);
						continue;
					}
					if(que.peekLast()==map[j][i]) {
						int a = 2*que.pollLast();
						que.offer(a);
						while(!que.isEmpty()) {
							sumMap[idx--][i] = que.poll();
						}
					}
					else {
						que.offer(map[j][i]);
					}
				}
				while(!que.isEmpty()) {
					sumMap[idx--][i] = que.poll();
				}
			}
		}

		init(map,sumMap);
	}
	
	static void move(int cnt) {
		if(cnt==5) {
			
			if(maxValue()>ans) {
				ans = maxValue();
			}
			return;
		}
		int [][] tempMap = new int[N][N];
		init(tempMap,map);
		for(int i=0;i<4;i++) {
			sum(i);
			move(cnt+1);
			init(map,tempMap);
		}
	}
	
	
}