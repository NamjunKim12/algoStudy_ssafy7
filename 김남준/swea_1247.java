import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
    static int N, answer, I_home, J_home, I_company, J_company;
    static boolean[] visited;

    static int[] order;
    private static int[] I_customers, J_customers;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/swea/swea_1247/input.txt"));
        Scanner sc = new Scanner(System.in);

        sc.nextInt();
        for (int test_case = 1; test_case <= 10; test_case++) {
            answer = Integer.MAX_VALUE;
            N = sc.nextInt();
            order = new int[N];
            visited = new boolean[N];
            I_customers = new int[N];
            J_customers = new int[N];

            I_home = sc.nextInt();
            J_home = sc.nextInt();
            I_company = sc.nextInt();
            J_company = sc.nextInt();

            for (int i = 0; i < N; i++) {
                I_customers[i] = sc.nextInt();
                J_customers[i] = sc.nextInt();
            }

            permutation(0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    static public void permutation(int depth) {
        if (depth == N) {
            int sum = getDistance(I_home, J_home, I_customers[order[0]], J_customers[order[0]]);
            for (int i = 1; i < N; i++) {
                sum += getDistance(I_customers[order[i - 1]], J_customers[order[i - 1]], I_customers[order[i]], J_customers[order[i]]);
            }
            sum += getDistance(I_customers[order[N - 1]], J_customers[order[N - 1]], I_company, J_company);

            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            order[depth] = i;
            visited[i] = true;
            permutation(depth + 1);
            visited[i] = false;
        }
    }

    public static int getDistance(int startI, int startJ, int endI, int endJ) {
        return Math.abs(endI - startI) + Math.abs(endJ - startJ);
    }
}
