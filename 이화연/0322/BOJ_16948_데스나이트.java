import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16948_데스나이트 {

	static class Point {
		int x, y, time;

		public Point(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		boolean[][] visited = new boolean[N][N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(r1, c1, 0));
		visited[r1][c1] = true;

		int[] di = { -2, -2, 0, 0, 2, 2 };
		int[] dj = { -1, 1, -2, 2, -1, 1 };

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (now.x == r2 && now.y == c2) {
				System.out.println(now.time); // 도착하면 횟수 출력하고 return
				return;
			}

			for (int i = 0; i < 6; i++) {
				int nexti = now.x + di[i];
				int nextj = now.y + dj[i];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && !visited[nexti][nextj]) {
					visited[nexti][nextj] = true;
					queue.offer(new Point(nexti, nextj, now.time + 1));
				}
			}
		}
		System.out.println(-1); // 이동할 수 없을 때
	}

}
