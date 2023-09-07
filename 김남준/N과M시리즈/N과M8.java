  
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N과 M 8 (조건이 주어진 중복 조합)

public class Main {

    static int N, M;
    static int[] answer;
    static boolean[] visit;
    static int[] basket;

    static BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

    // 조건이 주어진 중복조합(N과 M 8)
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new int[M];
        basket = new int[N + 1];
        visit = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            basket[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(basket);

        combination(0, 0);
        bw.flush();
        bw.close();
    }

    private static void combination(int depth, int start) throws IOException {
        if (depth == M) {
            for (int num : answer) {
                bw.write(num + " ");
            }
            bw.newLine();
            return;
        }



        for (int i = start; i < N; i++) {
            answer[depth] = basket[i + 1];
            visit[i] = true;
            combination(depth + 1, i);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        new boj.boj_15657.Main().solution();
    }
}
