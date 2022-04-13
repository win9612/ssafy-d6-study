package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gold17281 {

	static int N;
	static int[][] inning;
	static boolean[] select;
	static int[] lineUP;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		inning = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		select = new boolean[10];
		lineUP = new int[10];

		select[4] = true;
		lineUP[4] = 1;

		result = 0;
		perm(2);
		System.out.println(result);
	}

	public static void perm(int num) {
		if (num == 10) {
			baseBall();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (select[i]) {
				continue;
			}
			select[i] = true;
			lineUP[i] = num;
			perm(num + 1);
			select[i] = false;
		}
	}

	public static void baseBall() {
		int score = 0;
		int startPlayer = 1;

		for (int i = 1; i <= N; i++) {
			int outCount = 0;
			boolean[] base = new boolean[4];

			inningLoop: while (true) {

				for (int j = startPlayer; j <= 9; j++) {
					int action = inning[i][lineUP[j]]; // 타자 행동 스위치 기준

					s: switch (action) {
					case 0: // 아웃
						outCount++;
						break s;
					case 1: // 1루타
						for (int k = 3; k >= 1; k--) { // 3루부터 탐색
							if (base[k]) {
								if (k == 3) { // base 3이 true면 주자가 있다는거니까 홈으로
									score++; // 홈에 왔으니 점수 추가하고
									base[k] = false; // 3루는 비우기
								} else {
									// 1,2루는 1칸씩 이동
									base[k] = false;
									base[k + 1] = true;
								}
							}
						}
						base[1] = true; // 홈은 1루로 가니까 base1 true
						break s;
					case 2: // 2루타
						for (int k = 3; k >= 1; k--) {
							if (base[k]) {
								if (k == 3 || k == 2) {
									score++;
									base[k] = false;
								} else {
									base[k] = false;
									base[k + 2] = true;
								}
							}
						}
						base[2] = true;
						break s;
					case 3: // 3루타
						for (int k = 3; k >= 1; k--) {
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}

						base[3] = true; // 홈에서 3루로 진루.
						break s;
					case 4: // 홈런
						for (int k = 1; k <= 3; k++) {
							if (base[k]) { // 어떤 베이스에 주자가 있으면
								score++; // 주자 1명 당 1점씩 획득
								base[k] = false; // 그 자리 비우기
							}
						}

						score++; // 타자 점수 추가
						break s;
					}
					if (outCount == 3) { // 아웃 카운트가 3개 일 경우
						// startPlayer를 그 다음 타자로 초기화 함.
						startPlayer = j + 1;
						if (startPlayer == 10) {
							startPlayer = 1;
						}
						break inningLoop;
					}
				}
				startPlayer = 1;
			}
		}

		result = Math.max(result, score);
	}
}
