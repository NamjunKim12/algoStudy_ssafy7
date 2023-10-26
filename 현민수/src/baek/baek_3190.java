import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int num = Integer.parseInt(br.readLine());
		int[] dr = new int[] {0,1,0,-1};  // +3왼  +1오
		int[] dc = new int[] {1,0,-1,0};
		
		for(int i=0;i<num;i++) {
			st  = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b]=1;   //사과
		}
		num = Integer.parseInt(br.readLine());
		int ans = 0;
		int dir = 0;
		ArrayDeque<int[]> snake = new ArrayDeque<int[]>();
		snake.add(new int[] {0,0});
		map[0][0] = -1;
		for(int i=0;i<num;i++) {
			st  = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			char b = st.nextToken().charAt(0);
			while(ans<=a) {
				int[] temp = snake.poll();
				int nr = temp[0] + dr[dir];
				int nc = temp[1] + dc[dir];
				
				if(nr<0 || nc<0 || nc>N-1 || nr>N-1 || map[nr][nc]==-1) {
					System.out.println(ans+1);
					return;
				}
				if(map[nr][nc]==1) {
					snake.offerFirst(temp);
					snake.offerFirst(new int[] {nr,nc});
					map[nr][nc]=-1;
				}
				else {
					snake.offerFirst(temp);
					snake.offerFirst(new int[] {nr,nc});
					int[] tail = snake.pollLast();
					map[nr][nc]=-1;
					map[tail[0]][tail[1]] = 0;
				}
				ans++;
			}
			if(b=='L')dir = (dir+3)%4;
			else dir = (dir+1)%4;
		}
		while(true) {
			int[] temp = snake.poll();
			int nr = temp[0] + dr[dir];
			int nc = temp[1] + dc[dir];
			
			if(nr<0 || nc<0 || nc>N-1 || nr>N-1 || map[nr][nc]==-1) {
				System.out.println(ans+1);
				return;
			}
			if(map[nr][nc]==1) {
				snake.offerFirst(temp);
				snake.offerFirst(new int[] {nr,nc});
				map[nr][nc]=-1;
			}
			else {
				snake.offerFirst(temp);
				snake.offerFirst(new int[] {nr,nc});
				int[] tail = snake.pollLast();
				map[nr][nc]=-1;
				map[tail[0]][tail[1]] = 0;
			}
			ans++;
		}
	}
}