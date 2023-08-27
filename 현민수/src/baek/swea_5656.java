import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	static int N,W,H,ans;
	static int[][] map;
	static int[] dr,dc;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		dr = new int[] {0,0,-1,1};
		dc = new int[] {1,-1,0,0};
		
		for(int tc=1; tc<T+1;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			ans = Integer.MAX_VALUE;
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			shot(0);
		
			System.out.println("#"+tc+" "+ans);
		}

	}
	
	static void init(int[][] p , int[][] o) {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				p[i][j] = o[i][j];
			}
		}
	}
	
	static void delete(int start) {
		
		int r = 0;
		int c = start;
		ArrayDeque<int[]> que = new ArrayDeque<int[]>();
		ArrayDeque<int[]> deList = new ArrayDeque<int[]>();
		boolean[][] visit = new boolean[H][W];
		
		while(true) {
			if(r>H-1)break;
			else if(map[r][c]!=0)break;
			r++;
		}

		if(r>H-1) return;
		que.offer(new int[] {r,c});
		
		while(!que.isEmpty()) {
			int[] temp =que.poll();
			int tempr = temp[0];
			int tempc = temp[1];
			deList.offer(new int[] {tempr,tempc});
			int range = map[tempr][tempc]-1;
			for(int i=0;i<4;i++) {
				for(int j=1;j<range+1;j++) {
					int nr = tempr + j*dr[i];
					int nc = tempc + j*dc[i];
					if(nr<0 || nc<0 || nr>H-1 || nc>W-1)continue;
					if(!visit[nr][nc]) {
						que.offer(new int[] {nr,nc});
						visit[nr][nc] = true;
					}
					
				}
			}
		}
		
		while(!deList.isEmpty()) {
			int[] zero = deList.poll();
			int nr = zero[0];
			int nc = zero[1];
			map[nr][nc]=0;
		}	
	}
	
	static void off() {
		for(int i=0;i<W;i++) {
			String s="";
			for(int j=0;j<H;j++) {
				if(map[j][i]!=0) {
					s+=Integer.toString(map[j][i]);
					map[j][i]=0;
				}
			}
			int len = s.length()-1;
			int idx = H-1;
			for(int j=len;j>-1;j--) {
				
				map[idx--][i] = Character.getNumericValue(s.charAt(j));
				
			}
		}
		
	}
	static void shot(int cnt) {
		if(cnt==N) {	
			int sum=0;
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(map[i][j]>0) {
						sum++;
					}
				}
			}
			ans = Math.min(ans, sum);
			return;
		}
		
		int[][] tempMap = new int[H][W];
		init(tempMap,map);
		for(int i=0;i<W;i++) {
			delete(i);// 벽돌깨기
			off();		//벽돌내리기
			shot(cnt+1);// 다음벽돌

			init(map,tempMap);   // 맵초기화
		}
		
	}

}