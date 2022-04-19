package day0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18428 {
	static int N;
	static boolean answer;
	static String[][] map;
	static int[] comb;
	static ArrayList<Point> blank;
	static ArrayList<Point> teacher;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		comb = new int[3];
		blank = new ArrayList<Point>();
		teacher = new ArrayList<Point>();
		answer = false;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken();
				if (map[i][j].equals("X")) {
					blank.add(new Point(i, j));
				} else if (map[i][j].equals("T")) {
					teacher.add(new Point(i, j));
				}
			}
		}
		combination(0, 0);
		System.out.println(answer ? "YES" : "NO");
	}

	public static void see(String[][] map) {
		Queue<Point> queue = new LinkedList<>();
		queue.addAll(teacher);

		while (!queue.isEmpty()) { // 모든 선생이 상하좌우 감시
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) { // 4방 탐색
				int nexti = now.i;
				int nextj = now.j;

				while (true) {
					nexti += dx[d];
					nextj += dy[d];

					if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= N || map[nexti][nextj].equals("O")) {
						break;
					}
					if (map[nexti][nextj].equals("S")) { // 학생이 선생한테 걸리면 "NO"
						answer = false;
						return;
					}
				}
			}
		}
		answer = true; // 학생이 걸리지 않았으므로 "YES"
	}

	public static void combination(int start, int count) {
		if (answer) // "YES"일 경우 감시를 피할 수 있으므로 return
			return;
		if (count == 3) { // 3개의 장애물 설치
			String[][] copy = deepcopy(map);
			for (int i = 0; i < 3; i++) {
				copy[blank.get(comb[i]).i][blank.get(comb[i]).j] = "O";
			}
			see(copy); // 감시하기
			return;
		}
		for (int i = start; i < blank.size(); i++) {
			comb[count] = i;
			combination(i + 1, count + 1);
		}
	}

	public static String[][] deepcopy(String[][] map) {
		String[][] copy = new String[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	public static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
