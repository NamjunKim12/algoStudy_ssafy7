import java.util.Arrays;
import java.util.Scanner;

// 중복인 문자가 있을 때, 중복인 조합 제외하고 출력하기
// 물론 값을 저장할 때, 배열에 중복인 조합이 있는지를 확인하고 넣어도 되지만
// 생각을 잘 하면 재귀만으로 풀 수 있을 것 같아서 그렇게 시도해봤습니다.

// 방법은 같은 문자가 나오면, idx를 해당하는 같은 문자열 중 제일 마지막으로 이동시키는 것입니다.
// 그러면 같은 문자열로 시작하는 다른 분기를 모두 스킵할 수 있습니다.
// 경우의 수 그림을 직접 그려서 확인하면 더 직관적으로 이해할 수 있습니다.
// 해당하는 마지막 문자열을 방문처리하고 다음 재귀로 들어갑니다. 
// 그러면 다른 중복된 문자는 방문 처리 안되어있으니, 다른 중복된 문자열을 하나씩,한번씩 방문할 수 있습니다. 

public class baek_NM09 {
	static int[] arr, ans;
	static boolean[] visited;
	static int num, N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		num = sc.nextInt();
		
		N = sc.nextInt(); // 타겟 깊이 
		
		arr = new int[num];
		ans = new int[num];
		visited = new boolean[num];
		
		for (int i = 0; i < num; i++)
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr);
		
		rec(0);
		
		sc.close();
	}
	
	public static void rec(int deep)
	{
		if (deep == N)
		{
			for (int i = 0 ; i < N; i++)
			{
				System.out.print(ans[i]);
				System.out.print(" ");
			}
			System.out.println();
			return;
		}
				
		for (int i = 0; i < num; i++)
		{
			if (visited[i]) continue;
			
			// 중복되는 문자열 중 마지막 문자열까지 밀어준다.
			// 이후 deep 에서는 그 전 문자가 마지막 문자열이 된다.
			
			/*
				추가 주석. 
				만약 중복된 문자가 존재한다면 중복된 문자를 쭉 밀어서, 중복된 문자 중 마지막부터 선택합니다.
				그리고 해당 마지막 문자를 방문처리 합니다. 
				만약 다음 중복된 문자가 또 필요하다면, 마찬가지로 쭉 밀면서 >중복된 문자들에서 선택하지 않은 마지막 문자를 선택<합니다.
	
			*/
			
			while (i <num-1 && !visited[i+1] && arr[i] == arr[i+1])
				i++;
			
			visited[i] = true;
			
			ans[deep] = arr[i];
			rec(deep + 1);
			
			visited[i] = false;
		}
	}
}
