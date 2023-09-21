import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dwarfHeightArray = new int[9];
    static int sum = 0;
    static int fake1 = 0, fake2 = 0;
    //    static int[] tmp = new int[7];
//    static int[] realDwarf = new int[7];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void solution() throws IOException {

        for (int i = 0; i < 9; i++) {
            dwarfHeightArray[i] = Integer.parseInt(br.readLine());
            sum += dwarfHeightArray[i];
        }
        Arrays.sort(dwarfHeightArray);

        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - dwarfHeightArray[i] - dwarfHeightArray[j] == 100) {
                    fake1 = dwarfHeightArray[i];
                    fake2 = dwarfHeightArray[j];

                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (fake1 == dwarfHeightArray[i] || fake2 == dwarfHeightArray[i]) continue;
            System.out.println(dwarfHeightArray[i]);
        }


//        combination(0, 0, 0);
//       for (int a : realDwarf) { // 답 출력
//          System.out.println(a);
//      }
    }

    // 조합을 이용한 풀이
//    private static void combination(int depth, int start, int sum) {
//        if (depth == 7) {
//            if (sum == 100) {
//                System.arraycopy(tmp, 0, realDwarf, 0, 7);
//            }
//            return;
//        }
//        // ------>
//        for (int i = start; i < 9; i++) {
//            tmp[depth] = dwarfHeightArray[i];
//            combination(depth + 1, i + 1, sum + dwarfHeightArray[i]);
//        }
//    }


    public static void main(String[] args) throws Exception {
        solution();
    }
}