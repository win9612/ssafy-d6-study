import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17281
// ⚾
public class BJ17281 {

	private static int[] batter;
	private static int[][] innings;
	private static int n, max, idx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		batter = new int[10];
		innings = new int[n][10];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		batter[4] = 1;

		go(2);
		System.out.println(max);
	} // end main

	private static int score() {
		int score = 0;

		for (int inning = 0; inning < n; inning++) {
			int out = 0;

			int[] field = new int[4];

			while (out < 3) {
				idx = (++idx >= 10) ? idx % 9 : idx;

				// 안타?2루타? 등등인지
				int command = innings[inning][batter[idx]];
				if (command == 0) {
					// 아웃인 경우
					out++;
					continue;
				}

				// 아웃이 아닌 경우
				switch (command) {
				case 1:
					score += field[2];
					field[2] = field[1];
					field[1] = field[0];
					field[0] = 1;
					break;
				case 2:
					score += field[1] + field[2];
					field[2] = field[0];
					field[1] = 1;
					field[0] = 0;
					break;
				case 3:
					score += field[0] + field[1] + field[2];
					field[2] = 1;
					field[1] = 0;
					field[0] = 0;
					break;
				case 4:
					score += field[0] + field[1] + field[2] + 1;
					field[2] = 0;
					field[1] = 0;
					field[0] = 0;
					break;
				}

			} // 3진아웃
		}
		return score;
	} // end score()

	private static void go(int cnt) {
		if (cnt > 9) {
			idx = 0;
			max = Math.max(max, score());
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (batter[i] != 0)
				continue;

			batter[i] = cnt;
			go(cnt + 1);
			batter[i] = 0;
		}
	} // end go(순서찾기)
}
