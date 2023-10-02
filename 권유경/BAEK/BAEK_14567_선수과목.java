package BAEK_14567_선수과목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 과목 수
		int N = Integer.parseInt(st.nextToken());
		// 선수 조건의 수
		int M = Integer.parseInt(st.nextToken());

		// 각 과목의 진입차수 담을 배열
		int[] dist = new int[N + 1];

		// 각 선수 조건을 담을 배열
		int[][] condition = new int[M][2];

		// dist, condition배열 채우기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 선수과목 [0] 듣고 -> [1] 들어야 함
			condition[i][0] = Integer.parseInt(st.nextToken());
			condition[i][1] = Integer.parseInt(st.nextToken());
			// 진입차수 더해주기
			dist[condition[i][1]]++;
		}
		// 입력 완료

		// 학기 배열 만들기
		int[] semester = new int[N + 1];
		int cnt = 1;

		// 위상정렬 사용
		Queue<Integer> q = new LinkedList<>();
		// 진입차수가 0인 숫자 넣기
		for (int i = 1; i <= N; i++) {
			if (dist[i] == 0) {
				q.add(i);
				// 1학기에 이수 가능
				semester[i] = 1;
			}
		}
		// q가 빌 때까지 반복
		while (!q.isEmpty()) {
			int t = q.poll();
			for (int i = 0; i < condition.length; i++) {
				// 만약에 t를 선수과목으로 하는 과목이라면
				if (condition[i][0] == t) {
					// 진입차수를 하나 빼고
					dist[condition[i][1]]--;
					// 선수과목의 학기 +1을 해주자
					semester[condition[i][1]] = Math.max(semester[condition[i][1]], semester[t] + 1);
					// 만약에 진입차수가 0이 되었다면 q에 넣자
					if (dist[condition[i][1]] == 0) {
						q.add(condition[i][1]);
					}
				}
			}
			// for
		}
		// while

		// 정답 출력
		for (int i = 1; i <= N; i++) {
			System.out.print(semester[i] + " ");

		}
	}
	// main
}


/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 과목 수
		int N = Integer.parseInt(st.nextToken());
		// 선수 조건의 수
		int M = Integer.parseInt(st.nextToken());

		// 각 과목의 진입차수 담을 배열
		int[] dist = new int[N + 1];

		// 각 선수 조건을 담을 배열
		int[][] condition = new int[M][2];

		// dist, condition배열 채우기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 선수과목 [0] 듣고 -> [1] 들어야 함
			condition[i][0] = Integer.parseInt(st.nextToken());
			condition[i][1] = Integer.parseInt(st.nextToken());
			// 진입차수 더해주기
			dist[condition[i][1]]++;
		}
		// 입력 완료

		// 학기 배열 만들기
		int[] semester = new int[N + 1];
		int cnt = 1;

		// 위상정렬 사용
		Queue<Integer> q = new LinkedList<>();
		// 진입차수가 0인 숫자 넣기
		for (int i = 1; i <= N; i++) {
			if (dist[i] == 0) {
				q.add(i);
				// 1학기에 이수 가능
				semester[i] = 1;
			}
		}
		// q가 빌 때까지 반복
		while (!q.isEmpty()) {
			int t = q.poll();
			// 현재 학기 중 가장 큰 수를 구해서
			int max = 0;
			for (int i = 0; i < semester.length; i++) {
				max = Math.max(max, semester[i]);
			}
			// +1 해주기
			cnt = max + 1;
			for (int i = 0; i < condition.length; i++) {
				// 만약에 t를 선수과목으로 하는 과목이라면
				if (condition[i][0] == t) {
					// 진입차수를 하나 빼고
					dist[condition[i][1]]--;
					// 만약에 진입차수가 0이 되었다면 q에 넣자
					if (dist[condition[i][1]] == 0) {
						q.add(condition[i][1]);
						semester[condition[i][1]] = cnt;
					}
				}
			}
			// for
		}
		// while

		// 정답 출력
		for (int i = 1; i < N; i++) {
			System.out.print(semester[i] + " ");
		}System.out.print(semester[N]);

	}
	// main
}

*/