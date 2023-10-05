package Baek_1197_최소스패닝트리;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//크루스칼
public class Main {
	static int[] p; //대표 저장할 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		//0: 시작정점 1:끝정점 2:가중치
		int[][] edges = new int[E][3];
		
		for (int i = 0; i < E; i++) {
			edges[i][0] = sc.nextInt();
			edges[i][1] = sc.nextInt();
			edges[i][2] = sc.nextInt();
		}//입력 끝
		
		//크루스칼 1단계: 간선 오름차순 정렬
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		//크루스칼 2단계: V-1개 간선 뽑는데, 사이클 발생안하도록 뽑기
		p = new int[V+1];
		//make-set
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
		
		//검사하면서 뽑기
		int ans = 0; //최소비용 저장
		int pick = 0; //뽑은 간선의 수를 저장
		
		for (int i = 0; i < E; i++) {
			int px = find(edges[i][0]);
			int py = find(edges[i][1]);
			
			//여기 들어왔다는 것은 사이클 형성 안된다는 뜻
			if(px != py) {
				union(px,py);
				ans += edges[i][2];
				pick++;
			}
			
			if(pick == V-1) break;
		}
		System.out.println(ans);
		
	}//main
	
	static void union(int x, int y) {
		p[y] = x;
	}
	
	static int find(int x) {
		if(x != p[x]) {
			p[x] = find(p[x]);
		}
		return p[x];
	}
}
