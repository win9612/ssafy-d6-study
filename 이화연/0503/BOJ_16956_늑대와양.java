import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16956_늑대와양 {
	static int R, C;
	static char[][] map;
	static int[] di = { -1, 1, 0, 0 }; // 상,하,좌,우
	static int[] dj = { 0, 0, -1, 1 };

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열

		map = new char[R][C];
		Queue<Point> queue = new LinkedList<Point>();
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'W') { // 늑대 위치
					queue.offer(new Point(i, j));
				}
			}
		}

		boolean flag = true;
		while (!queue.isEmpty() && flag) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nexti = now.r + di[d];
				int nextj = now.c + dj[d];

				if (nexti < 0 || nexti >= R || nextj < 0 || nextj >= C)
					continue;
				if (map[nexti][nextj] == '.') { // 빈칸이면 울타리 세우기
					map[nexti][nextj] = 'D';
				} else if (map[nexti][nextj] == 'S') { // 상,하,좌,우에 양이 인접해 있다면 늑대가 양한테 갈 수 있음
					flag = false;
				}
			}
		}

		if (flag) {
			System.out.println(1);
			print();
		} else {
			System.out.println(0);
		}

	}

	public static void print() {
		for (char[] cc : map) {
			for (char c : cc) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
