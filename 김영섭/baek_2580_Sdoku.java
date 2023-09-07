import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] table = new int[9][9];
	static List <int[]> blanks = new ArrayList<> ();
	static int blankSize;
	static boolean ansGet;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int i = 0; i < 9; i++) {
			String[] tmp = br.readLine().split(" ");
			
			for (int j = 0; j < 9; j++) 
			{
				table[i][j] = tmp[j].charAt(0) - '0';
				// 매번 빈칸이 어딘지 확인하는건 비효율적이라고 생각해서
				// 한번 돌 때, 빈칸의 좌표를 ArrayList에 저장해두었습니다.
				if (table[i][j] == 0) blanks.add(new int[] {i, j});
			}
		}
		// 답이 나오는지 아닌지 확인, 이거 안하면 무수히 많은 case 출력됨.
		// 가장 첫 케이스 출력하고 toggle 해서 나머지 case는 모두 건너뜀.
		ansGet = false;
		
		blankSize = blanks.size();
		
		// 첫 번째 빈칸부터 시작.
		dfs(0);
		
		// 정답 출력.
		System.out.println(sb.toString());
		
		br.close();
	}
	
	public static void dfs(int deep) {
		if (deep == blankSize && !ansGet) {
			// 답 하나 만들었으면 더 하면 안되니까 toggle
			ansGet = true;
			// 정답 출력
			tablePrint();
			return ;
		}
		
		// deep 번째 0인 칸 하나 선택.
		int[] pos = blanks.get(deep);
		int r = pos[0], c = pos[1];
		
		// 해당 칸에 1-9까지 수 넣어보기.
		for (int i = 1; i <= 9; i++) {
			// 만약 첫 답이 나왔으면 종료 
			if (ansGet) return ;
			// 해당 칸에, i라는 (1-9) 값이 들어갈 수 없다면 건너뜀.
			if (!isValid(r, c, i)) continue;
			
			// 해당 칸에 i라는 값 넣어봄.
			table[r][c] = i;
			// 넣은 케이스 쭉 탐색.
			dfs(deep + 1);
			// 안넣기로 함. 취소하고 다음 값으로 넘어감.
			// 예를 들어, 2 3 5 라는 값이 들어갈 수 있다면,
			// 2 들어가는 케이스를 위에서 쭉 확인해보고, 
			// 다음으로 4 들어가는 케이스를 넣기 위해서
			// 2 넣은 행위를 취소.
			table[r][c] = 0;
		}
	}
	
	public static boolean isValid(int r, int c, int num) {
		for (int i = 0; i < 9; i++) {
			// 행과 열 확인하면서 같은 숫자 있으면 false
			if (table[r][i] == num) return false;
			if (table[i][c] == num) return false;
		}
		
		// 해당 칸이 속한 3x3 확인하면서
		// 같은 숫자 있으면 false
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (table[(r/3)*3 + i][(c/3)*3 + j] == num)
					return false;
			}
		}
		
		return true;
	}
	
	public static void tablePrint() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(table[i][j]);
				sb.append(' ');
			}
			sb.append('\n');
		}
	}
}
