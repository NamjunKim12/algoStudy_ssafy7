package Baek_14567_선수과목;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //과목의 수
		int M = sc.nextInt(); //선수 조건의 수
		
		int[][] adjArr = new int[N+1][N+1]; //그래프 저장할 배열
		int[] degree = new int[N+1]; //진입차수 저장할 배열
		int[] result = new int[N+1];
		int cnt = 1;
		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			adjArr[A][B] = 1;
			degree[B]++; 
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if(degree[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int a = q.size();
			//한 depth의 사이클을 큐 사이즈로 반복
			for (int k = 0; k < a; k++) {
				int now = q.poll();
				result[now] = cnt;
				
				for (int i = 1; i <= N; i++) {
					if(adjArr[now][i] == 1) {
						adjArr[now][i] = 0;
						degree[i]--;
						
						if(degree[i] == 0) {
							q.add(i);
							
						}
					}
				}
			}
			cnt++;
		}
		
	
		for (int i = 1; i <= N; i++) {
			System.out.print(result[i]+" ");
		}
	}//main
}
