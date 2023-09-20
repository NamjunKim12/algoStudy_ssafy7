package Baek_10026_적록색약;

import java.util.Scanner;

public class Main {
	static int N;
	static String[][] arr;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new String[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.substring(j,j+1);
			}
		}
		//색약 아닌 경우
		visited = new boolean[N][N]; //방문 배열 초기화
		int cnt = 0; //색약 아닌 사람
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					DFS(i,j);
					cnt++;
				}
			}
		}
		
		//색약인 경우
		//빨간색을 초록색으로 바꿔주기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j].equals("R")) {
					arr[i][j] = "G";
				}
			}
		}
		visited = new boolean[N][N]; //방문 배열 초기화
		int cnt2 = 0; //색약인 사람이 봤을 때 구역 카운트
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					DFS(i,j);
					cnt2++;
				}
			}
		}
		//출력
		System.out.println(cnt+" "+cnt2);
		
	}//main
	
	public static void DFS(int x, int y) {
		visited[x][y] = true;
		String str = arr[x][y];
		
		for (int i = 0; i < 4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
			
			if(arr[nx][ny].equals(str)) {
				visited[nx][ny] = true;
				DFS(nx,ny);
			}
		}
	}//DFS
}
