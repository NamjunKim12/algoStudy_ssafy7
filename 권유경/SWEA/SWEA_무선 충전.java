package SWEA_무선충전;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// tc개수
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			// 이동시간
			int M = sc.nextInt();
			// BC개수
			int A = sc.nextInt();

			// 마지막에 사용될 최솟값
			int min = 0;

			// 답
			int answer = 0;
			
			// 매 이동시간마다의 성능값 저장(0초부터 계산하므로 M+1)
			int[][] result = new int[2][M + 1];

			// 두 사람의 이동 방향
			int[][] person = new int[2][M + 1];
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= M; j++) {
					person[i][j] = sc.nextInt();
				}
			}

//			for(int[] a : person) {
//				System.out.println(Arrays.toString(a));
//			}

			List<Integer>[] BClist = new ArrayList[A];
			for (int i = 0; i < BClist.length; i++) {
				BClist[i] = new ArrayList<>();
			}
			for (int i = 0; i < A; i++) {
				for (int j = 0; j < 4; j++) {
					// 0: y, 1: x, 2: c(충전범위), 3: p(성능)
					BClist[i].add(sc.nextInt());
				}
			}
			// 입력 완료

//			for (List<Integer> a : BClist) {
//				System.out.println(a);
//			}

			// 두 사람의 좌표를 넣어둔 리스트(for문으로 돌리기 위해 만듦)
			List<Integer>[] list = new ArrayList[2];
			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<>();
			}
			list[0].add(0);
			list[0].add(0);
			list[1].add(9);
			list[1].add(9);
//				for(List<Integer> a : list) {
//					System.out.println(a);
//				}

			// 10*10 지도 만들기
			int[][] map = new int[10][10];

			// 각 BC에 몇 초에 몇 명이 연결되는지 확인할 배열
			int[][] check = new int[A][M + 1];

			// BC1부터 범위를 채워주자
			for (int n = 0; n < A; n++) {
				map = new int[10][10];

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						// n번째 BC의 충전범위 안 이라면
						if (Math.abs(BClist[n].get(1) - 1 - i) + Math.abs(BClist[n].get(0) - 1 - j) <= BClist[n]
								.get(2)) {
							// 만약 그 범위가 n-1번째 BC의 충전 범위 안이라면

							// n번째 BC의 성능값 넣어주기
							map[i][j] = BClist[n].get(3);
						}
					}
				}

//				for(int[] a: map) {
//					System.out.println(Arrays.toString(a));
//				}System.out.println();

				// n번째 BC의 충전범위 채우기 완료

				// 이제 n번째 BC에 몇 초에 누가 범위에 들어오는지 확인할 것임. 1:사람1, 2:사람2, 3:둘 다
				// 0 상 우 하 좌 이동
				int[] dr = { 0, -1, 0, 1, 0 };
				int[] dc = { 0, 0, 1, 0, -1 };

				// 두 명이 이동
				for (int i = 0; i < 2; i++) {
					int r = list[i].get(0);
					int c = list[i].get(1);
					if (map[r][c] != 0)
						check[n][0] = i + 1;
//					System.out.println(r+","+c);
					// 시작위치가 충전범위라면 미리 넣어두자
					// M초만큼 이동
					for (int m = 1; m <= M; m++) {

						int nr = r + dr[person[i][m]];
						int nc = c + dc[person[i][m]];

						// 현위치 갱신
						r = nr;
						c = nc;
						// 사람은 배열을 벗어나지 않도록 이동하므로 범위체크는 하지 않겠음.
						// 충전범위 안에 들어가면
						if (map[r][c] != 0) {
							// check[n][m]에 몇 번 사람과 연결되었는지 기록
							// 아직 연결되지 않은 상태라면 몇 번 사람인지 기록
							if (check[n][m] == 0)
								check[n][m] = i + 1; // i=0부터 시작이고 배열의 기본 값은 0이니까, 확인하기 위해 1을 더해줌
							// 이미 다른 사람이 연결된 상태라면 3을 써주자
							else
								check[n][m] = 3;
						}

					}
				}
			}
			// BC순회
//			for (int[] a : check) {
//				System.out.println(Arrays.toString(a));
//			}
//			System.out.println();

//			[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 2, 0, 0, 0, 0, 0, 0, 0]
//			[2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1]
//			[0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0]

			// 두 사람이 충전되는 시점의 BC의 성능(동일한 초에 두 개 이상의 BC에 두 사람이 충전된다면 BC의 성능이 큰 것으로 갱신)
			int[] twoPeople = new int[M + 1];

			// check 배열을 순회하면서 숫자가 3일 때는 기록, 1 또는 2일때는 result 배열에 해당 BC의 성능 값을 넣어주자
			// 기존 값보다 크다면 갱신
			for (int i = 0; i < check.length; i++) { // check배열 행 순회
				for (int j = 0; j < M + 1; j++) {// check배열 열 순회
					// j초에 누구도 충전되지 않는다면 지나가
					if (check[i][j] == 0)
						continue;
					// j초에 두명 이상 충전될 때, 기존 값보다 크다면 기록해
					else if (check[i][j] == 3) {
						if (twoPeople[j] < BClist[i].get(3))
							twoPeople[j] = BClist[i].get(3);
					}
					// j초에 한 명만 충전될 때,
					else {
						// 만약 사람 1 이 충전될 때, 기존 result배열의 값보다 성능이 크다면 갱신
						if (check[i][j] == 1) {
							if (result[0][j] < BClist[i].get(3))
								result[0][j] = BClist[i].get(3);
						}
						// 만약 사람 2 가 충전될 때, 기존 result배열의 값보다 성능이 크다면 갱신
						if (check[i][j] == 2) {
							if (result[1][j] < BClist[i].get(3))
								result[1][j] = BClist[i].get(3);
						}

					}

				}
			}

			// 이제 다시 check배열을 순회하면서 값이 3일 경우 result배열의 0,1행의 값을 비교하여 더 작은 값보다 성능이 크다면 갱신
			for (int i = 0; i < check.length; i++) { // check배열 행 순회
				for (int j = 0; j < M + 1; j++) {// check배열 열 순회
					if (check[i][j] == 3) {
						if (result[0][j] > result[1][j]) {
							min = result[1][j];
							if (min < BClist[i].get(3)) {
								result[1][j] = BClist[i].get(3);
							}
						} else {
							min = result[0][j];
							if (min < BClist[i].get(3)) {
								result[0][j] = BClist[i].get(3);
							}
						}
					}
				}
			}

			for(int i = 0; i<2; i++) {
				for(int j = 0; j<M+1; j++) {
					answer +=result[i][j];
				}
			}
			System.out.println("#"+tc+" "+answer);
			
		}
		// tc
	}
	// main
}
