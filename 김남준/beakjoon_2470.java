import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 두 용액 (골드 5)
public class Main {
    static int N, start_idx, end_idx;
    static long sum_abs, sum, gap, left, right;
    static long[] solution;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        N = Integer.parseInt(br.readLine());
        solution = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solution);

        start_idx = 0;
        end_idx = solution.length - 1;
        gap = Long.MAX_VALUE;

        while (start_idx < end_idx) {
            sum = solution[start_idx] + solution[end_idx];
            sum_abs = Math.abs(sum);
            if (sum_abs < gap) {
                gap = sum_abs;
                left = solution[start_idx];
                right = solution[end_idx];
            }

            if (sum > 0) {
                end_idx--;
            } else {
                start_idx++;
            }
        }

        System.out.print(left + " " + right);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
