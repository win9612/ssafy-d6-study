import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14923_미로탈출 {
	static int N, M, map[][], min;
	static Point start, end;
	static List<Point> list;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static class Point {
		int x, y, time;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		st = new StringTokenizer(br.readLine());
		start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		st = new StringTokenizer(br.readLine());
		end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					map[i][j] = 1;
					list.add(new Point(i, j)); // 벽인 곳을 리스트에 담는다.
				}
			}
		}

		min = 987654321;
		for (int i = 0; i < list.size(); i++) { // 벽인 곳을 0으로 바꿔보고 최솟값 출력
			int[][] copy = new int[N][M];

			// 2차원 배열 복사
			for (int j = 0; j < copy.length; j++) {
				System.arraycopy(map[j], 0, copy[j], 0, copy[0].length);
			}
			copy[list.get(i).x][list.get(i).y] = 0; // 벽이 있던 곳을 0으로 바꿔줌

			min = Math.min(bfs(copy), min);
		}
		System.out.println(min - 1); // 처음시작할때 1 넣어줘서 -1

	}

	public static int bfs(int[][] copy) {
		int time = -1; // 탈출할 수 없을 경우 -1

		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(start.x, start.y));
		copy[start.x][start.y] = 1; // 시작 자리에 1 넣어줌

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (copy[now.x][now.y] > min) { // 최솟값보다 현재 copy에 저장된 값이 더 크면 이동할 필요 없음
				return time = min;
			}

			if (now.x == end.x && now.y == end.y) { // 탈출 자리 도착
				return time = copy[now.x][now.y];
			}

			for (int d = 0; d < 4; d++) {
				int nexti = now.x + di[d];
				int nextj = now.y + dj[d];

				// 벽인 곳만 이동
				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && copy[nexti][nextj] == 0) {
					queue.offer(new Point(nexti, nextj));
					copy[nexti][nextj] = copy[now.x][now.y] + 1; // now자리에 있던 값+1을 다음 자리에 넣어줌
				}
			}
			print(copy);
			System.out.println("------------------");
		}
		return time;
	}

	public static void print(int[][] map) {
		for (int[] nn : map) {
			for (int n : nn) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
	}

}
