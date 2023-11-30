> 

## 🔗 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/42748

### ✏ 풀이 과정
- 걸린 시간 : `40분` 
- 문제 풀이 과정
  1. 우선 return해야 하는 answer 배열을 만든다.(commands의 크기)
  2. commands의 크기만큼 반복하며 i,j,k를 구한다.
  3. array 배열의 i-1부터 j-1 인덱스 까지의 값을 담기 위한 새로운 배열 newArray를 만든다.
     - 인덱스는 0부터 시작이기 때문에 i와 j에서 1씩 빼준다.
  4. newArray배열에 값을 담는다.
  5. 오름차순으로 정렬한다.
  6. k-1의 값을 answer배열에 넣는다.

```java
package 프로그래머스_K번째수;

import java.util.Arrays;

public class Solution {
	public static int[] solution(int[] array, int[][] commands) {

		int[] answer = new int[commands.length];
		// 리턴에야하는 값이므로 반복문 밖에 선언

		for (int a = 0; a < commands.length; a++) {// commands크기만큼 반복
			int i = commands[a][0];
			int j = commands[a][1];
			int k = commands[a][2];

			int[] newArray = new int[j - i + 1];
			// array 배열의 i-1 부터 j-1까지의 값을 담을 배열

			int idx = 0; // array배열의 i-1 부터 j-1인덱스의 값를 넣어줄 newArray의 인덱스
			for (int b = i - 1; b <= j - 1; b++) {
				newArray[idx++] = array[b];
			} // idx인덱스에 array배열의 i-1 부터 j-1인덱스의 값 입력

			Arrays.sort(newArray);
			//오름차순 정렬
			
			answer[a] = newArray[k - 1];

		} // commands크기만큼 반복
		return answer;
	}// 메소드

//	public static void main(String[] args) {
//		  int[] array = {1, 5, 2, 6, 3, 7, 4};
//		    int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
//		    int[] answer = solution(array, commands);
//		    System.out.println(Arrays.toString(answer));
//	} // 메소드 확인
}

```

### 😎 간단 회고 및 소감

어렵지 않았다. 이제 메소드를 사용하는 방식이 조금은 익숙해진 듯하다.

newArray배열을 생성할 때 array배열의 크기로 만드는 실수가 있었으나 main 함수를 만들어 실행해보며 실수를 찾을 수 있었다. 

쉽다고 느꼈는데, 생각보다 시간이 오래 걸려서 아쉽다. 하지만 아직은 좀 더 정확하게 푸는 연습을 더 해야할 단계라고 생각한다.
