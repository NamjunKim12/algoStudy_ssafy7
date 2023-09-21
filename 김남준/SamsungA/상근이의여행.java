import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M;
    static boolean visit[];
    static ArrayList<Integer>[] graph;
    static Queue<Integer> queue;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public void solution() throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            visit = new boolean[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graph[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int departure = Integer.parseInt(st.nextToken());
                int arrival = Integer.parseInt(st.nextToken());

                graph[departure].add(arrival);
                graph[arrival].add(departure);
            }

            System.out.println(N - 1);
            //System.out.println(bfs());
        }
    }

//    public static int bfs() {
//        queue = new LinkedList<>();
//        int answer = 0;
//
//        queue.add(1);
//        visit[1] = true;
//
//        while (!queue.isEmpty()) {
//            int Vertex = queue.poll();
//
//            for (int nextVertex : graph[Vertex]) {
//                if (!visit[nextVertex]) {
//                    queue.add(nextVertex);
//
//                    visit[nextVertex] = true;
//                    answer += 1;
//                }
//            }
//        }
//
//        return answer;
//    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}