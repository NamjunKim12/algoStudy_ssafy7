import java.io.FileInputStream;
import java.util.Scanner;

// 규영이와 인영이의 카드게임

public class Solution {
    static int T, gyu_win, in_win, idx;
    static int[] cards_gyuyoung, cards_inyeong, result;
    static boolean[] check, visited;


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/swea/swea_6808/input.txt"));
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            cards_gyuyoung = new int[9];
            cards_inyeong = new int[9];
            check = new boolean[19];
            result = new int[9];
            visited = new boolean[9];
            gyu_win = 0;
            in_win = 0;

            for (int i = 0; i < 9; i++) {
                cards_gyuyoung[i] = sc.nextInt();
                check[cards_gyuyoung[i]] = true;
            }

            idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (!check[i]) {
                    cards_inyeong[idx++] = i;
                }
            }

            permutation(0);

            System.out.println("#" + test_case + " " + gyu_win + " " + in_win);
        }
    }

    static void permutation(int step) {
        if (step == 9) {
            int gyuScore = 0, inScore = 0;

            for (int i = 0; i < 9; i++) {
                if (cards_gyuyoung[i] > result[i]) {
                    gyuScore += cards_gyuyoung[i] + result[i];
                } else {
                    inScore += cards_gyuyoung[i] + result[i];
                }
            }

            if (gyuScore > inScore) {
                gyu_win++;
            } else if (gyuScore < inScore) {
                in_win++;
            }

            return;
        }

        for (int i = 0; i < 9; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            result[step] = cards_inyeong[i];
            permutation(step + 1);
            visited[i] = false;
        }
    }
}
