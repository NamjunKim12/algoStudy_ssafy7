> 

## 🔗 문제 링크

https://www.acmicpc.net/workbook/view/2052

### ✏ 풀이 과정
- 걸린 시간 : `n분` 
- 문제 풀이 과정
  - 시간초과가 나지 않도록 한다.(BufferedWriter 사용)
  - 중복되는 수열이 만들어지지 않도록 perm메소드 안에 조건문을 만든다.
    - list를 순회하며, 만들어진 수열과 list에 있는 수열과 같은 것이 있다면 return


```java
package BAEK_N과M;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class N과M9 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result; // 결과 저장
	static boolean[] visited;
	static List<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = 
            new BufferedWriter(new Ou tputStreamWriter(System.out));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		} // 입력 완료

		Arrays.sort(arr); // 오름차순 정렬
		list = new ArrayList<>();

		result = new int[M];
		visited = new boolean[N];

		perm(0);

		for(int[] A : list) {
			for(int a : A) {
				bw.write(a+ " ");
			}bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}// main

	static void perm(int idx) {
		if (idx == M) {
			for (int i = 0; i < list.size(); i++) {
				// list 를 순회하며 만들어진 수열과 같은 것이 있다면
				if (Arrays.equals(list.get(i), result)) {
					return; // 리턴
				}
			}// 같은 수열이면 리턴
			
			list.add(result.clone()); // 만들어진 수열을 list에 추가
			return;
		}// 기저조건
		
		
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			result[idx] = arr[i];
			visited[i] = true;
			perm(idx + 1);
			visited[i] = false;
		}
		// 재귀조건

	}// combination
}
```

### 😎 간단 회고 및 소감

- 이전 문제를 풀 때 StringBuilder를 사용해야만 시간초과가 나지 않아서 이 문제도 처음에는 StringBuilder를 사용하여 풀려고 했다. 하지만 StringBuilder에 추가된 모든 값과 result 배열을 비교하는 방법을 찾을 수 없어서 list 를 사용하여 풀었다.

  그래서 시간초과를 나지 않게 하기 위해 BufferedWriter를 처음 사용해보는 기회가 되었다.

- 분명히 수업시간에 배웠는데,, list.add(result) 를 하며 계속 같은 주소값을 추가하는 실수를 했다 ㅠㅠ 그래서 계속 마지막 조합만 list에 남아있는 문제가 생겼다. 이 문제를 해결하느라 1시간 가까이 헤맸다 .. 그 부분이 잘못된 것인 줄 모르고 list를 순회하며 result와 같은 것이면return하는 그 부분을 계속 수정하려 했다. 문제점을 찾느라 시간이 많이 걸려서 조금 짜증났지만, result.clone() 을 하면 따로 temp 배열을 만들 필요 없이 바로 복사해준다는 것을 알아내서 좋았다.

- 다른 사람들의 풀이를 보니 hashset을 많이 사용하여 푼 것을 확인할 수 있었다. hashset은 중복저장을 하지 않는 특징이 있다고 한다.. 배웠던 것 같은데 새롭다. 공부를 더 많이 해야겠다..
