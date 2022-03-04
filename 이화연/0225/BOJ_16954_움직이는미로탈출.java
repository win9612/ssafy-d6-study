import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16954_움직이는미로탈출 {
	static int[] di = { -1, -1, -1, 0, 0, 0, 1, 1, 1 }; // 상하좌우,대각선,그대로
	static int[] dj = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static ArrayList<char[]> chess;
	static boolean[][] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		chess = new ArrayList<char[]>();
		for (int i = 0; i < 8; i++) {
			chess.add(br.readLine().toCharArray());
		}

		ans = 0;
		bfs(7, 0); // 가장 왼쪽 아랫 칸에서 시작

		System.out.println(ans);

	}

	public static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(i, j));

		while (!queue.isEmpty()) {
			visited = new boolean[8][8];
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				Point current = queue.poll();

				if (current.x == 0 && current.y == 7) { //도착지점에 도착
					ans = 1;
					return;
				}
				if (chess.get(current.x)[current.y] == '#') //벽일때 이동 불가
					continue;

				for (int d = 0; d < 9; d++) {
					int nexti = current.x + di[d];
					int nextj = current.y + dj[d];

					// 이동하려는 칸의 인덱스가 유효하고 빈칸일때
					if (nexti >= 0 && nexti < 8 && nextj >= 0 && nextj < 8 && chess.get(nexti)[nextj] == '.'
							&& !visited[nexti][nextj]) {
						visited[nexti][nextj] = true;
						queue.offer(new Point(nexti, nextj));
					}
				}
			}
			chess.remove(7);
			chess.add(0, new char[] { '.', '.', '.', '.', '.', '.', '.', '.' });
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
