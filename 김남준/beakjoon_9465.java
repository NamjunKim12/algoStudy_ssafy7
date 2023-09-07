import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, len;
    static int[][] board, acc;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tc:
        for (int test_case = 1; test_case <= N; test_case++) {

            len = Integer.parseInt(br.readLine());
            int[][] board = new int[2][len + 1];
            int[][] acc = new int[2][len + 1];

            for (int i = 0; i < 2; i++) {
                String[] inputs = br.readLine().split(" ");
                for (int j = 1; j <= len; j++) {
                    board[i][j] = Integer.parseInt(inputs[j - 1]);
                }
            }

            acc[0][1] = board[0][1];
            acc[1][1] = board[1][1];

            for (int i = 2; i <= len; i++) {
                acc[0][i] = Math.max(acc[1][i - 1], acc[1][i - 2]) + board[0][i];
                acc[1][i] = Math.max(acc[0][i - 1], acc[0][i - 2]) + board[1][i];
            }
            System.out.println(Math.max(acc[0][len], acc[1][len]));
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
