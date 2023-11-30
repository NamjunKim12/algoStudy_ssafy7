package Baek_1753_최단경로;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Node{
		int v,w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	private static final int INF = 1234567;
	
	static int V,E,K;
	static List<Node>[] adjList;
	static int[] dist;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		K = sc.nextInt(); //시작 정점의 번호
		adjList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		for (int i = 0; i < E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			
			adjList[u].add(new Node(v,w));
		}
		
		
		dijkstra(K);
		System.out.println(Arrays.toString(dist));
		for (int i = 1; i <= V; i++) {
			if(dist[i] == 1234567) {
				System.out.println("INF");
			}else {
				System.out.println(dist[i]);
			}
		}
	}//main
	
	static void dijkstra(int start) {
		boolean[] visited = new boolean[V+1];
		dist[start] = 0;
		
		for (int i = 0; i < V-1; i++) {
			int min = INF;
			int idx = -1;
			
			for (int j = 0; j < V; j++) {
				if(!visited[j] && min > dist[j]) {
					min = dist[j];
					idx = j;
				}
			}
			if(idx == -1) break; //다익스트라의 다른점(갈 길이 없으면 탐색 종료)
			
			visited[idx] = true;
			
			for (int j = 0; j < adjList[idx].size(); j++) {
				Node curr = adjList[idx].get(j);
				
				if(!visited[curr.v] && dist[curr.v] > dist[idx]+curr.w) {
					dist[curr.v] = dist[idx]+curr.w;
				}
			}
		}
	}
}	
