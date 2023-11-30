package Baek_16234_인구이동;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N,L,R;
	static int[][] arr;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	static int sum;
	static int num;
	static int cnt;
	static List<int[]> list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		sum = 0;
		//배열 입력받기
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		cnt = 0;

		while(true) {
			boolean isTrue = false;
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					if(!visited[i][j]) {
						//연합 찾기
						sum = FindUnion(i,j);
						num = list.size();
						if(num > 1) {
							int after = (int) Math.floor(sum/num);
							//인구 이동
							Move(after);
							isTrue = true;
						}
						if(num == 0) break;
					}
				}
			}
			if(!isTrue) break;
			else cnt++;
		}
		
		System.out.println(cnt);
	}//main
	
	//인구이동
	public static void Move(int after) {
		for (int i = 0; i < list.size(); i++) {
			int[] now = list.get(i);
			arr[now[0]][now[1]] = after;
		}
		
	}
	
	//연합찾기
	public static int FindUnion(int x,int y) {
		Queue<int[]> q = new LinkedList<>();
		list = new ArrayList<>();
		
		q.add(new int[] {x,y});
		visited[x][y] = true;
		list.add(new int[] {x,y});
		
		int sum = arr[x][y];
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now[0];
				int ny = dy[i] + now[1];
				
				//경계조건 확인
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				int diff = Math.abs(arr[nx][ny] - arr[now[0]][now[1]]); //인구 차이
				
				//연합 생성
				if(!visited[nx][ny] && diff >= L && diff <= R) {
					list.add(new int[] {nx,ny});
					visited[nx][ny] = true;
					sum += arr[nx][ny];
					q.add(new int[] {nx,ny});
				}
			}
			
		}
		return sum;

	}
}
