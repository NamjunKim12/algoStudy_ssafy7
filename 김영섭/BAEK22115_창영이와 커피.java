import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] caff = new int[K+1];

        ArrayList<Integer> coffee = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            coffee.add(Integer.parseInt(st.nextToken()));

        Collections.sort(coffee);

        for (int c : coffee) {
            if (c > K) break;
            for (int i = K; i - c >= 0; i--) {
                if (i == c) caff[i] = 1;
                else if (caff[i-c] == 0) continue;
                else if (caff[i] != 0) caff[i] = Math.min(caff[i-c] + 1, caff[i]);
                else caff[i] = caff[i-c] + 1;
            }
        }

        if (caff[K] == 0 && K != 0) System.out.println(-1);
        else System.out.println(caff[K]);

        br.close();
    }
}