import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    static int M, N;
    static List<List<Integer>> topology;
    static int[] answer;
    static int[] parentNumber;

    public void solution() throws Exception {
        topology = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parentNumber = new int[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i <= N; i++)
            topology.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            topology.get(A).add(B);
            parentNumber[B] += 1;
        }

        topologicalSort();

        for (int i = 1; i <= N; i++) System.out.print(answer[i] + " ");
    }

    static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++)
            if (parentNumber[i] == 0) {
                queue.offer(i);
                answer[i] = 1;
            }

        while (!queue.isEmpty()) {
            int number = queue.poll();

            for (int i : topology.get(number)) {
                parentNumber[i]--;

                if (parentNumber[i] == 0) {
                    queue.offer(i);
                    answer[i] = answer[number] + 1;
                }
            }
        }
    }

    public static void main
            (String[] args) throws Exception {
        new Main().solution();
    }
}public class beakjoon_14567 {
    
}
