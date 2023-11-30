package SWEA_1251_하나로;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// for문을 통해서 모든 섬이 연결되어있다는 것을 조합을 구할 필요 없이 바로 바로 배열에 저장할 것임

public class Solution2 {
	static int[] p; // 대표자 넣을 배열

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/SWEA_1251_하나로/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 섬의 수

			int[] x = new int[N]; // x좌표 배열
			int[] y = new int[N]; // y좌표 배열

			for (int i = 0; i < N; i++) {
				x[i] = sc.nextInt(); // x좌표
			}

			for (int i = 0; i < N; i++) {
				y[i] = sc.nextInt(); // y좌표
			}

			double tax = sc.nextDouble();
			// 입력 완료

			List<double[]> list = new ArrayList<>(); // 간선 정보, 환경부담금 저장할 리스트
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) { // 이렇게 하면 모든 섬이 연결되어 있다고 가정할 수 있음
					double[] edges = new double[3]; // [0]: 시작 섬 [1]: 끝 섬 [2]: 환경부담금
					edges[0] = i;
					edges[1] = j;
					double dist = (Math.pow(Math.abs((x[i] - x[j])), 2) + Math.pow(Math.abs((y[i] - y[j])), 2)) * tax;
					edges[2] = dist;
					list.add(edges); // 만들어진 배열을 리스트에 추가
				}
			} // edges배열 완성

//			for(int i =0; i<list.size(); i++) {
//					System.out.println(Arrays.toString(list.get(i)));
//				}

			// 오름차순 정렬하자
			Collections.sort(list, new Comparator<double[]>() {

				@Override
				public int compare(double[] o1, double[] o2) {
					return (o1[2] - o2[2] < 0 ? -1 : 1);
				}
			});

//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(Arrays.toString(list.get(i)));
//			}

			p = new int[N];
			for (int i = 0; i < N; i++) {
				p[i] = i;
			} // 대표 섬 선정

			int pick = 0;
			double ans = 0; // 최소 환경부담금
			// 이제 while문으로 대표를 변수에 넣자

			// 크루스칼 알고리즘 사용
			for (int i = 0; i < list.size(); i++) {
				int X = (int) list.get(i)[0];
				int Y = (int) list.get(i)[1];

				if (findset(X) != findset(Y)) { // 대표 섬이 다르다면
					uinon(findset(X), findset(Y)); // 연결하자
					pick++;
					ans += list.get(i)[2];
				}
				if (pick == N - 1)
					break;
			} // for

			System.out.println("#" + tc + " " + Math.round(ans));
		} // tc

	}// main

	static int findset(int x) {
		if (x != p[x])
			p[x] = findset(p[x]);
		return p[x];
	}

	static void uinon(int x, int y) {
		p[y] = x;
	}

}
