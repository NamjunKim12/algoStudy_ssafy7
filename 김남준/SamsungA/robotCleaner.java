import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, row, col, d, answer;
    static int[][] map;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 북 동 남 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public void solution() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ///////////////구현부

        loop:
        while (true) {
            //현재칸이 청소되지 않은 경우
            if (map[row][col] == 0) {
                answer += 1;
                map[row][col] = 99;
            }

            boolean allCleaned = true;
            // 4칸 중 청소되지 않은 칸이 있는지 판별
            for (int i = 0; i < 4; i++) {
                int newRow = row + dr[i];
                int newCol = col + dc[i];

                if (checkValidMap(newRow, newCol)) {
                    if (map[newRow][newCol] == 0) {
                        allCleaned = false;
                        break;
                    }
                }
            }
            // 4방향이 모두 청소된 경우
            if (allCleaned) {
                int tmpDir = -1;
                if (d < 2) tmpDir = d + 2;
                else tmpDir = d % 2;

                int newRow = row + dr[tmpDir];
                int newCol = col + dc[tmpDir];

                if (checkValidMap(newRow, newCol)) {
                    if (map[newRow][newCol] != 1) {
                        row = newRow;
                        col = newCol;
                    } else break loop;
                }
            }

            // 4방향 중 청소되지 않은 칸이 있는 경우
            if (!allCleaned) {
                if (0 < d) d -= 1;
                else d = 3;

                int newRow = row + dr[d];
                int newCol = col + dc[d];

                if (checkValidMap(newRow, newCol)) {
                    if (map[newRow][newCol] == 0) {
                        row = newRow;
                        col = newCol;
                    }
                }
            }
        }
        ////////////// 출력부
        System.out.println(answer);
    }

    public static boolean checkValidMap(int row, int col) {
        return 0 <= row && 0 <= col && row < N && col < M ? true : false;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}