import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// N과 M 11 (중복 수열)
public class Main {

    static int N, M;
    static int[] answer;
    static boolean[] visit;
    static int[] basket;

    static BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

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

        permutation(0);
        bw.flush();
        bw.close();
    }

    private static void permutation(int depth) throws IOException {
        if (depth == M) {
            for (int num : answer) {
                bw.write(num + " ");
            }
            bw.newLine();
            return;
        }

        int before = 0;

        for (int i = 1; i <= N; i++) {

            if(before == basket[i]) continue;
            answer[depth] = basket[i];
            before = basket[i];
            visit[i] = true;
            permutation(depth + 1);
            visit[i] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        new boj.boj_15665.Main().solution();
    }
}

