package Baek_7569_토마토;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int M,N,H, cnt;
	static int[][][] arr;
	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	static boolean[][][] visited;
	static Queue<int[]> q;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt(); //가로
		N = sc.nextInt(); //세로
		H = sc.nextInt(); //높이
		arr = new int[N][M][H];
		visited = new boolean[N][M][H];
		
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
						arr[i][j][k] = sc.nextInt();
				}
			}
		}
		q = new LinkedList<>();
		cnt = -1;
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
						if(arr[i][j][k]==1 && !visited[i][j][k]) {
							q.add(new int[] {i,j,k});
							visited[i][j][k] = true;
						}
				}
			}
		}
		BFS();
		
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
						if(arr[i][j][k]==0 && !visited[i][j][k]) {
							cnt = -1;
						}
				}
			}
		}
		System.out.println(cnt);
		
	}//main
	
	public static void BFS() {
		while(!q.isEmpty()) {
			int a = q.size();
			for (int k = 0; k < a; k++) {
				int[] cur = q.poll();
				
				for (int i = 0; i < 6; i++) {
					int nx = dx[i] + cur[0];
					int ny = dy[i] + cur[1];
					int nz = dz[i] + cur[2];
					
					if(nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H || visited[nx][ny][nz] || arr[nx][ny][nz] == -1) continue;
					
					if(arr[nx][ny][nz] == 0 && !visited[nx][ny][nz]) {
						visited[nx][ny][nz] = true;
						q.add(new int[] {nx,ny,nz});
					}
				}
			}
			cnt++;
		}
	}//BFS
}
