import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L, answer;
    static int[][] map;
    static boolean[] visit;
    static int[] row, col;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public void solution() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        row = new int[N];
        col = new int[N];
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                row[j] = map[j][i];
                col[j] = map[i][j];
            }
            if (checkRoad(row)) answer += 1;
            if (checkRoad(col)) answer += 1;
        }
        System.out.println(answer);
    }

    public static boolean checkRoad(int[] arr) {
        visit = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i - 1]) continue;
            if (Math.abs(arr[i] - arr[i - 1]) > 1) return false;

            if (arr[i] - arr[i - 1] == 1) {
                for (int j = i - 1; j >= i - L; j--) {
                    if (j < 0 || arr[j] != arr[i] - 1 || visit[j]) return false;
                    visit[j] = true;
                }
            }

            if (arr[i] - arr[i - 1] == -1) {
                for (int j = i; j < i + L; j++) {
                    if (j >= N || arr[j] != arr[i] || visit[j]) return false;
                    visit[j] = true;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
