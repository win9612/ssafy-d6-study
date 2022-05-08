package day0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BJ17281 {
	static int N, answer, round, score, out;
	static int[][] players;
	static int[] place, permuFront, permuBack, combFront, combBack;
	static boolean[] usedFront, usedBack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		players = new int[N][9];
		place = new int[3];
		permuFront = new int[3];
		permuBack = new int[5];
		combFront = new int[3];
		combBack = new int[5];
		usedFront = new boolean[3];
		usedBack = new boolean[5];
		answer = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combFrontination(1, 0);
		System.out.println(answer);
	}

	public static void combFrontination(int start, int count) {
		if (count == 3) {
			// combFront가 아닌 수 뽑기
			int index = 0;
			for (int i = 1; i < 9; i++) {
				int num = i;
				if (!IntStream.of(combFront).anyMatch(x -> x == num)) {
					combBack[index++] = num;
				}
			}
			// 앞부분 순열 돌리기
			permutationFront(0);
			return;
		}
		for (int i = start; i < 9; i++) {
			combFront[count] = i;
			combFrontination(i + 1, count + 1);
		}
	}

	public static void permutationFront(int count) {
		if (count == 3) {
			// 뒷부분 순열 돌리기
			permutationBack(0);
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (usedFront[i])
				continue;
			permuFront[count] = combFront[i];
			usedFront[i] = true;
			permutationFront(count + 1);
			usedFront[i] = false;
		}
	}

	public static void permutationBack(int count) {
		if (count == 5) {
			// N이닝 동안 점수 계산
			playBaseball();
			return;
		}
		for (int i = 0; i < 5; i++) {
			if (usedBack[i])
				continue;
			permuBack[count] = combBack[i];
			usedBack[i] = true;
			permutationBack(count + 1);
			usedBack[i] = false;
		}
	}

	public static void playBaseball() {
		out = 0;
		score = 0;
		round = 0;
		int index = 0;

		while (round != N) {
			if (index % 9 < 3) { // 앞부분
				getScore(players[round][permuFront[index % 9]]);
			} else if (index % 9 == 3) {
				getScore(players[round][0]); // 1번 선수 = 4번 타자
			} else { // 뒷부분
				getScore(players[round][permuBack[(index - 4) % 9]]);
			}
			index++;
		}
		answer = Math.max(answer, score);
	}

	public static void getScore(int num) {
		switch (num) {
		case 1: // 안타
			if (place[2] == 1)
				score++;
			place[2] = place[1];
			place[1] = place[0];
			place[0] = 1;
			break;
		case 2: // 2루타
			score += place[2] + place[1];
			place[2] = place[0];
			place[1] = 1;
			place[0] = 0;
			break;
		case 3: // 3루타
			score += place[2] + place[1] + place[0];
			place[2] = 1;
			place[1] = 0;
			place[0] = 0;
			break;
		case 4: // 홈런
			score += place[2] + place[1] + place[0] + 1;
			place[2] = 0;
			place[1] = 0;
			place[0] = 0;
			break;
		case 0: // 아웃
			out++;
			if (out == 3) { // 3아웃
				round++; // 이닝 종료
				out = 0;
				place = new int[3];
			}
			break;
		}
	}
}
