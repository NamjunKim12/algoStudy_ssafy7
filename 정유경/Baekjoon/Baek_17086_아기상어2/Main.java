package Baek_17086_아기상어2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N,M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0,1,1,-1,-1}; //8방향으로 이동할 좌표
	static int[] dy = {0,0,1,-1,1,-1,1,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		
		//배열에 값 저장하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] == 0) {
					//위치 옮길때마다 visited 배열 초기화
					visited = new boolean[N][M]; 
					//안전거리 최대값 갱신
					max = Math.max(max, bfs(i,j));
				}
			}
		}
		System.out.println(max); //안전거리 최대값 출력
		
		
	}//main
	
	public static int bfs(int a, int b) {
		int dist = 1;
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {a,b});
		
		while(!q.isEmpty()) {
			int s = q.size();
			for (int k = 0; k < s; k++) {
				int[] pos = q.poll();
				
				//8방향 탐색
				for (int i = 0; i < 8; i++) {
					int x = dx[i] + pos[0];
					int y = dy[i] + pos[1];
					
					//범위 벗어나면 바로 종료
					if(x<0 || y<0 || x>=N || y>=M || visited[x][y]) continue;
											
					//상어 안만났을 때
					if(arr[x][y] == 0) {
						visited[x][y] = true; //방문처리
						q.offer(new int[] {x,y}); //큐에 추가
					}
					//상어 만나면 바로 종료
					else {
						return dist;
					}
				}
			}
			dist++;
		}
		return dist;
		
	}//bfs
}
