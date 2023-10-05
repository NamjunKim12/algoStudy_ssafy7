import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
   static int[] p;
   static int[] rank;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int V = Integer.parseInt(st.nextToken()); // 정점의 개수
      int E = Integer.parseInt(st.nextToken()); // 간선의 개수

      int[][] edges = new int[E][3];
      for (int i = 0; i < E; i++) {
         // 정점의 정보와 가중치를 입력받고, 그 값을 edges 배열에 저장한다.
         st = new StringTokenizer(br.readLine());
         edges[i][0] = Integer.parseInt(st.nextToken()); // 정점
         edges[i][1] = Integer.parseInt(st.nextToken()); // 정점
         edges[i][2] = Integer.parseInt(st.nextToken()); // 가중치
      } // 입력완료

      // 이제 가중치를 기준으로 오름차순 정렬
      Arrays.sort(edges, new Comparator<int[]>() {
         @Override
         public int compare(int[] o1, int[] o2) {
            if (o1[2] == o2[2] && o1[0] == o2[0])
               return o1[1] - o2[1];
            if (o1[2] == o2[2]) return o1[0] - o2[0];
            return o1[2] - o2[2];
         }
      });

      p = new int[V + 1]; // 대표자 배열

      for (int i = 1; i <= V; i++) {
         p[i] = i;
      } // 대표자 선정

      int pick = 0; // 연결된 간선의 수
      int ans = 0; // 가중치의 합
      
      rank = new int[V+1];

         for (int i = 0; i < E; i++) {
            int px = findset(edges[i][0]);
            int py = findset(edges[i][1]);
            // 우선 대표자가 같은지 확인
            if (px != py) { // 만약 대표자가 같지 않다면
               // x팀에 y를 넣는다 => y의 대표를 x의 대표로
               union(px, py);
               pick++;
               ans += edges[i][2];
            }
            if (pick == V - 1)
                break;
         }
         
      System.out.println(ans);

   }// main

   static void union(int x, int y) {
      link(x, y);
   }

   static void link(int x, int y) {
      if(rank[x] > rank[y]) {
         p[y]=x;
      }else {
         p[x]=y;
         if(rank[x]==rank[y]) {
            rank[y]++;
         }
      }
   }

   static int findset(int x) {
      if (x != p[x])
         p[x] = findset(p[x]);
      return p[x];
   }
}
