package day0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ20056 {
	static int N, M, K;
	static ArrayList<ArrayList<ArrayList<FireBall>>> map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList<ArrayList<ArrayList<FireBall>>>();
		for (int i = 0; i < N; i++) { // map 초기화
			map.add(new ArrayList<ArrayList<FireBall>>());
			for (int j = 0; j < N; j++) {
				map.get(i).add(new ArrayList<FireBall>());
			}
		}
		for (int i = 0; i < M; i++) { // M개의 파이어볼
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()); // 행
			int c = Integer.parseInt(st.nextToken()); // 열
			int m = Integer.parseInt(st.nextToken()); // 질량
			int s = Integer.parseInt(st.nextToken()); // 속도
			int d = Integer.parseInt(st.nextToken()); // 방향
			map.get(r - 1).get(c - 1).add(new FireBall(m, s, d));
		}
		for (int i = 0; i < K; i++) { // K번 이동
			ArrayList<ArrayList<ArrayList<FireBall>>> copy = move(map); // 파이어볼 이동
			map = copy;
			ArrayList<ArrayList<ArrayList<FireBall>>> aftercopy = afterMove(map); // 파이어볼 이동 후
			map = aftercopy;
		}
		System.out.println(measureFireBall(map)); // 남은 파이어볼 질량 합
	}

	public static int measureFireBall(ArrayList<ArrayList<ArrayList<FireBall>>> map) {
		int mSum = 0;
		for (ArrayList<ArrayList<FireBall>> row : map) {
			for (ArrayList<FireBall> column : row) {
				for (FireBall fb : column) {
					mSum += fb.m; // 질량 합
				}
			}
		}
		return mSum;
	}

	public static ArrayList<ArrayList<ArrayList<FireBall>>> afterMove(ArrayList<ArrayList<ArrayList<FireBall>>> map) {
		ArrayList<ArrayList<ArrayList<FireBall>>> copy = deepcopy(map);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int fireballCount = copy.get(i).get(j).size(); // 파이어볼 개수
				if (fireballCount >= 2) { // 2개 이상이면 합치기
					int mSum = 0;
					int sSum = 0;
					int isEven = 0;

					for (int k = fireballCount - 1; k >= 0; k--) {
						FireBall fb = copy.get(i).get(j).get(k);
						mSum += fb.m; // 질량 합
						sSum += fb.s; // 속도 합
						if (fb.d % 2 == 0) { // 방향이 짝수일 경우
							isEven++;
						}
						copy.get(i).get(j).remove(k);
					}
					int m = mSum / 5; // 질량 구하기
					if (m != 0) { // 질량이 0이 아닐 경우
						int s = sSum / fireballCount; // 속도 구하기
						if (isEven == fireballCount || isEven == 0) { // 방향이 모두 짝수이거나 홀수인 경우
							for (int d = 0; d < 8; d += 2) { // 0,2,4,6
								copy.get(i).get(j).add(new FireBall(m, s, d));
							}
						} else {
							for (int d = 1; d < 8; d += 2) { // 1,3,5,7
								copy.get(i).get(j).add(new FireBall(m, s, d));
							}
						}
					}
				}
			}
		}
		return copy;
	}

	public static ArrayList<ArrayList<ArrayList<FireBall>>> move(ArrayList<ArrayList<ArrayList<FireBall>>> map) {
		ArrayList<ArrayList<ArrayList<FireBall>>> copy = deepcopy(map);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = map.get(i).get(j).size() - 1; k >= 0; k--) {
					FireBall fb = map.get(i).get(j).get(k);
					int nexti = i + dx[fb.d] * fb.s;
					int nextj = j + dy[fb.d] * fb.s;

					if (nexti >= 0) { // 양수, 0
						nexti %= N;
					} else { // 음수
						nexti = (N + nexti % N) % N;
					}
					if (nextj >= 0) { // 양수, 0
						nextj %= N;
					} else { // 음수
						nextj = (N + nextj % N) % N;
					}
					copy.get(i).get(j).remove(k); // 원래 있던 곳 삭제
					copy.get(nexti).get(nextj).add(fb); // 다른 곳으로 이동
				}
			}
		}
		return copy;
	}

	public static ArrayList<ArrayList<ArrayList<FireBall>>> deepcopy(ArrayList<ArrayList<ArrayList<FireBall>>> map) {
		ArrayList<ArrayList<ArrayList<FireBall>>> copy = new ArrayList<ArrayList<ArrayList<FireBall>>>();

		for (int i = 0; i < N; i++) {
			copy.add(new ArrayList<ArrayList<FireBall>>());
			for (int j = 0; j < N; j++) {
				copy.get(i).add(new ArrayList<FireBall>());
				for (int k = 0; k < map.get(i).get(j).size(); k++) {
					copy.get(i).get(j).add(map.get(i).get(j).get(k)); // deepcopy
				}
			}
		}
		return copy;
	}

	public static class FireBall {
		int m, s, d;

		public FireBall(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
