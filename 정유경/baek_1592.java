package bronze;
import java.util.Scanner;

public class baek_1592 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // N명의 사람들
		int[] people = new int[N+1]; // N자리수 배열 선언
		
		int M = sc.nextInt(); // 공 최대 받을 수 있는 횟수
		int L = sc.nextInt(); // 몇번째로 이동할건지
		
		//people 배열 안에 각자 공 받은 횟수 count
		//배열 안 값이 M과 같아지면 break하고 총 total 출력
		int total = 0; // 총 공이 이동한 횟수
		people[1]++; //1번부터 시작이므로 1 더하고 시작
		int index = 1;
		
		while(true) {
			if(people[index] == M) {
				break;
			}
			//홀수일 경우
			if(people[index] % 2 == 1) {
				index = index+L;
			} 
			//짝수일 경우
			else {
				index = index - L;
			}
			
			if(index > N)
				index = index % N;
			else if(index < 1)
				index = index + N;
			
			people[index]++;
			total++;
		}
		System.out.println(total);
		
	}
}

