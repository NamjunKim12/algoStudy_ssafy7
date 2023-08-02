> PR 제목은 문제명_이름으로 작성해주세요.

magnetic

## 🔗 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14hwZqABsCFAYD&

### ✏ 나의 풀이

- 문제 해석은 어렵지 않았으나 굉장히 복잡해보여서 겁이 났음.

- 행이 아니라 열별로 숫자를 비교해야 했기에 지난번 사용했던 2차배열을 90도 회전하는 방법을 사용해야겠다고 생각함

  [예상 풀이]

  1. 테이블 100*100 배열 생성
  2. 값 입력하여 테이블 값 채우기
  3. 테이블 90도 회전
  4. 특정 값이 1일 때 
     - 오른쪽 값과 바꿈(단, 오른쪽 값이 0이어야 함)
     - 오른쪽 값이 2 또는 1이면 다음열 탐색
     - 99행일 때는 값을 0으로 바꿈(테이블 밑으로 떨어짐)
  5. 특정 값 이 2일때
     - 왼쪽 값과 바꿈(단, 왼쪽 값이 0이어야 함)
     - 왼쪽 값이 2또는 1이면 다음열 탐색
     - 0행일 때는 값을 0으로 바꿈(테이블 밑으로 떨어짐)
  6. 배열이 더이상 변하지 않을 때까지 반복
  7. 배열을 순회하며 오른쪽 값이 2이면 answer +1 => 정답은 answer

```JAVA

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);


		for (int i = 0; i < 10; i++) {// 열 번 반복

			int T = sc.nextInt(); // 100
			int[][] table = new int[100][100]; // 테이블 100*100 배열

			for (int r = 0; r < 100; r++) {
				for (int c = 0; c < 100; c++) {
					table[r][c] = sc.nextInt(); // 테이블에 값 입력!
				}
			}


			int answer = 0;

			for (int c = 0; c < 100; c++) {
				String str = null; // 문자열
				for (int r = 0; r < 100; r++) {
					if (table[r][c] != 0) {
						str += Integer.toString(table[r][c]); // 값을 문자열로 변환
					}
				}
				for (int j = 0; j < str.length() - 1; j++) {
					if (str.substring(j, j + 2).equals("12")) {
						answer += 1;
					}
				}
			}
			System.out.printf("#%d %d\n", i, answer);

		}
	}
}

---------------------------------------------------------------------------------
    // 처음 시도했던 풀이
    
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {// 열 번 반복
			int T = sc.nextInt();

			int[][] table = new int[100][100]; // 테이블 100*100 배열
			for (int r = 0; r < 100; r++) {
				for (int c = 0; c < 100; c++) {
					table[r][c] = sc.nextInt(); // 테이블에 값 입력!
				}
			}

			// 테이블 회전 (시계방향으로 90도)
			int[][] newTable = new int[100][100];
			for (int r = 0; r < 100; r++) {
				for (int c = 0; c < 100; c++) {
					newTable[r][c] = table[r][99 - c];

				}
			}

			int answer = 0; // 답

			boolean isChanged = false; // 배열의 변화 여부 저장
			do {
				// 이제 왼쪽이 N극(2번이 좋아함), 오른쪽이 S극(1번이 좋아함)
				for (int r = 0; r < 100; r++) {
					for (int c = 0; c < 100; c++) { // 이제 각 열을 순회.

						// 값이 1이면 오른쪽 열의 값과 바꿈 
                        //(단,오른쪽 열 값이 1이면 바꾸지 않고 다음 열 탐색
						// c=99이면 값을 0으로 바꿈)
						if (newTable[r][c] == 1) {// 값이 1일때
							if (newTable[r][c + 1] == 1) {
								newTable[r][c] = 1;
							} // 오른쪽 열 값이 1이면 바꾸지 않고 다음 열 탐색

							else if (c == 99) {
								newTable[r][c] = 0;
							} // c=99이면 값을 0으로 바꿈(테이블 아래로 떨어지니까)

							else if(newTable[r][c + 1] == 0){
								newTable[r][c] = newTable[r][c + 1];
								newTable[r][c + 1] = 1;
								isChanged = true;
							}
						} // 오른쪽 열의 값과 바꿈

						// 값이 2이면 왼쪽 열의 값과 바꾼 후에 바로 뒤에 값을 탐색,
						// (단, 왼쪽 열 값이 1일경우에는 바꾸지 않고 다음 열 탐색, 
                        // c=0이면 값을 0으로 바꿈)
						if (newTable[r][c] == 2) {// 값이 2일때
							if (c == 0) {
								newTable[r][c] = 0;
								isChanged = true;
							} // c=0이면 값을 0으로 바꿈

							else if (newTable[r][c - 1] == 1) {
								newTable[r][c] = 2;
							} // 왼쪽 열 값이 1일 경우에는 바꾸지 않고 다음 열 탐색
							else if (newTable[r][c - 1] == 0) {
								newTable[r][c] = newTable[r][c - 1];
								newTable[r][c-1] = 2;
								isChanged = true;
								// 값이 0이면 왼쪽값과 바꿈
							}

						}

					}

				}

			} while (isChanged);

			int[][] result = new int[100][100];
			for (int r = 0; r < 100; r++) {
				for (int c = 0; c < 100; c++) {
					result[r][c] = newTable[r][c];
				}
			}
			for (int r = 0; r < 100; r++) {
				for (int c = 0; c < 100; c++) {
					if (newTable[r][c] == 1 && newTable[r][c + 1] == 2) {
						answer += 1; // 오른쪽 열 값이 2이면 answer +1
					}
				}
			}

			System.out.printf("#%d %d", i, answer);
		}
	}
}


```

### 😎 느낀 점 및 회고

오랜 시간 투자했지만 계속 런타임에러가 발생하여 결국 다른 풀이를 참조하였다..

입력된 문자 중 1과 2만 입력받아 문자열로 변환시킨 후 '12'의 수만 구하면 되는 간단한 문제였다..

더 많은 문제를 풀어보며 여러 풀이방법을 익혀야겠다.

- substring 함수에 대해 알게되었다.



