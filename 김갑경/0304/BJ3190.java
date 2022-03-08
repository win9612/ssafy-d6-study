import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/3190
public class Main {

	private static int n, time;
	private static int dir = 0; // 우:0, 하:1, 좌:2, 상:3
	private static final int[][] move = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	private static int[][] map;
	private static Deque<Point> dq = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = parse(br.readLine()); // 보드의 크기
		map = new int[n][n]; // 0: 빈 칸, 1: 사과, 2: 뱀
		map[0][0] = 2;
		int k = parse(br.readLine()); // 사과의 개수
		dq.addFirst(new Point(0, 0));
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[parse(st.nextToken()) - 1][parse(st.nextToken()) - 1] = 1; // 사과의 위치
		}

		int l = parse(br.readLine()); // 방향 변환 횟수
		for (int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = parse(st.nextToken()); // x초 후에
			char c = st.nextToken().charAt(0); // 변경할 방향
			if (!move(x - time, c)) {
				System.out.println(time + 1);
				return;
			}
		}

		move(100, 'D');
		System.out.println(time + 1);
	}

	private static boolean move(int x, char c) {
		while (x-- > 0) {
			Point head = dq.peekFirst();
			int ni = head.i + move[dir][0];
			int nj = head.j + move[dir][1];

			if (!checkIdx(ni, nj)) {
				return false;
			}

			// 한 칸 앞으로 이동한다.
			dq.offerFirst(new Point(ni, nj));
			if (map[ni][nj] == 0) { // 사과 아니면 길이 줄어들음
				Point tail = dq.pollLast();
				map[tail.i][tail.j] = 0;
			}

			time++;
			map[ni][nj] = 2;
		}

		// 방향 변경
		if (c == 'D') {
			dir = (dir + 1) % 4;
		} else {
			dir = (dir + 3) % 4;
		}
		return true;
	}

	private static boolean checkIdx(int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= n || map[i][j] == 2)
			return false;
		return true;
	}

	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}

}
