import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시 {
	static int N, M, energy, map[][], passenger[][], pIdx;
	static boolean[] pVisited;
	static int startRowMin = 0, startColMin = 0;
	static int arriveRow = 0, arriveCol = 0;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static Point taxi;

	static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		energy = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine()); // 택시 위치
		taxi = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), energy);

		passenger = new int[M + 1][5]; // 승객 출발, 도착위치
		pVisited = new boolean[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 4; j++) {
				passenger[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = -1; // 연료 바닥나거나 출발지나 목적지로 이동 못할 때
		for (int m = 1; m <= M; m++) {
			// 현재 택시 위치에서 가장 가까운 승객 찾기
			minDistance(taxi);

			// 택시 위치에서 최단 거리 승객 출발지로 이동 하기
			int count = bfs(startRowMin, startColMin);
			if (count != -1) {
				energy -= count;
				list.add(pIdx);

				// 승객 출발지에서 승객 도착지로 이동 하기
				count = bfs(arriveRow, arriveCol);
				if (count != -1) {
					energy = energy - count + (count * 2);
					for (Integer i : list) {
						if (i == pIdx) {
							list.remove(i);
							pVisited[i] = true; // 이미 방문한 승객 처리
							break;
						}
					}
					ans = energy;
				} else {
					System.out.println(-1);
					return;
				}
//				if (!list.isEmpty()) {
//					ans = -1;
//				}
			} else {
				System.out.println(-1);
				return;
			}

		}
		System.out.println(ans);
	}

	static ArrayList<Integer> list = new ArrayList<Integer>();

	public static int bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(taxi.x, taxi.y, 0));
		boolean[][] visited = new boolean[N + 1][N + 1];
		visited[taxi.x][taxi.y] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (energy - now.cnt < 0) { // 연료가 0보다 작으면 리턴
				return -1;
			}

			if (now.x == r && now.y == c) {
				taxi = new Point(r, c, 0); // 택시 위치 갱신
				return now.cnt;
			}

			for (int d = 0; d < 4; d++) {
				int nexti = now.x + di[d];
				int nextj = now.y + dj[d];
				if (nexti >= 1 && nexti <= N && nextj >= 1 && nextj <= N && !visited[nexti][nextj]
						&& map[nexti][nextj] != 1) {
					queue.offer(new Point(nexti, nextj, now.cnt + 1));
					visited[nexti][nextj] = true;
				}
			}
		}
		return -1;
	}

	public static void minDistance(Point taxi) {
		int min = 987654321;
		pIdx = 0;

		for (int i = 1; i <= M; i++) { // 최단 거리 비교
			int absX = Math.abs(passenger[i][1] - taxi.x);
			int absY = Math.abs(passenger[i][2] - taxi.y);
			if (!pVisited[i]) { // 방문하지 않은 승객들만 비교
				if (min > absX + absY) {
					min = absX + absY;
					startRowMin = passenger[i][1];
					startColMin = passenger[i][2];
					arriveRow = passenger[i][3];
					arriveCol = passenger[i][4];
					pIdx = i;
				} else if (min == absX + absY) { // 만약 최단거리가 같으면
					if (startRowMin > passenger[i][1]) { // 행 번호 더 작은 걸로
						startRowMin = passenger[i][1];
						startColMin = passenger[i][2];
						arriveRow = passenger[i][3];
						arriveCol = passenger[i][4];
						pIdx = i;
					} else if (startRowMin == i) { // 행번호도 같으면 열 번호 작은걸로
						if (startColMin > passenger[i][2]) {
							startRowMin = passenger[i][1];
							startColMin = passenger[i][2];
							arriveRow = passenger[i][3];
							arriveCol = passenger[i][4];
							pIdx = i;
						}
					}
				}
			}
		}
	}

	public static void print(int[][] map) {
		for (int[] mm : map) {
			for (int m : mm) {
				System.out.print(m + " ");
			}
			System.out.println();
		}
	}
}
