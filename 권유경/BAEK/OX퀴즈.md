> PR 제목은 문제명_이름으로 작성해주세요.

OX퀴즈

## 🔗 문제 링크

https://www.acmicpc.net/problem/8958

### ✏ 나의 풀이

- 조건문과 반복문으로 풀 수 있을 것 같다.

- 쉬운 난이도 같다.

  

  [예상풀이]

1. 우선 testCase 개수 입력받고
2. testCase 개수만큼 순회하는 for문 작성
3. for문 안에 testCase 개수만큼 OX퀴즈 결과 입력
4. 입력한 결과(문자열)의 범위 지정 (0<결과<80)
5. 각 결과를 순회하며 "0"을 만나면 +1 후 처음으로 돌아오기
   만약 X를 만나면 +1 하지 않고, 만난 X의 위치부터 순회하기
6. 나온 값을 더하기(앞에 int sum = 0; 선언 미리 해둘 것)

```JAVA

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 테스트 케이스 개수 입력
		int testCase = sc.nextInt();
		sc.nextLine();

		// testCase만큼 순회하는 for문 작성
		for (int i = 0; i < testCase; i++) {
			int sum = 0; // 결과 점수 선언
			String ox_result = sc.nextLine(); // ox 결과 입력
			String[] result = ox_result.split(""); // 입력받은 결과 값만큼의 배열 생성

			int cnt = 0; // 연속되는 "O"의 개수

			for (int j = 0; j < result.length; j++) { // 결과값의 개수만큼 순회하며 OX확인
				if (result[j].equals("O")) { // 만약 j번째 결과값이 "O"라면
					cnt++; // "O"의 개수 증가
					sum = sum + cnt;  // 구한 "O"의 개수만큼 누적 더하기
				}else { // X를 만나면
					cnt=0; // X를 만나면 cnt = 0
				}

			}
			System.out.println(sum);
		}

	}

}


```

### 😎 느낀 점 및 회고

두 시간 가량 풀었는데, 계속 에러가 나서 결국 답을 확인했다.

- 입력받은 결과(문자열)를 담은 result배열을 생성할 때에는 split을 해야 배열의 각 인덱스에 문자열 하나하나를 넣을 수 있다는 것을 알게 되었다.

- 또한 긴 문자열을 입력할 때에는 nextInt(); 가 아닌 nextLine():을 써야 한다는 것도 알게 되었다.

- 이 문제는 총 5번의 결과값을 입력받고 그 5번의 결과값을 통해 점수를 구해야 하는데, 왜인지 4번째 결과값의 점수까지만 구해졌다.

  알고보니 테스트 케이스 개수를 입력한 후 sc.nextLine();을 해주지 않았기 때문이었다.

  sc.nextLine();을 해주지 않으면 테스트 케이스 개수를 입력한 후 enter를 누를 때 그 enter를 첫번째 결과값이라고 인식하기 때문이다. 

- 예상풀이 5.에서는 "X"를 만났을 때 순회한다고 했는데 그 조건은 없어도 됐기 때문에 코드로 작성하지 않았다.
- 연속되는 "O"의 개수 int cnt = 0; 을 for문 안에 작성했어서 답이 잘 출력되지 않았었다. 변수 선언을 어디에 해야하는지 잘 생각하고 해야겠다.

쉬운 문제라고 생각했지만 이제 막 공부를 시작한 나에게는 그리 쉽지 않았다..
