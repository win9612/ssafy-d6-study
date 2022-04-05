package day0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ19238 {
	static int N, M, fuel, takeR, takeC, distance;
	static int[][] map, location;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		location = new int[M][4];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int taxiR = Integer.parseInt(st.nextToken());
		int taxiC = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int peopleR = Integer.parseInt(st.nextToken());
			int peopleC = Integer.parseInt(st.nextToken());
			int destinationR = Integer.parseInt(st.nextToken());
			int destinationC = Integer.parseInt(st.nextToken());
			location[i][0] = peopleR - 1;
			location[i][1] = peopleC - 1;
			location[i][2] = destinationR - 1;
			location[i][3] = destinationC - 1;
		}
		takeR = taxiR - 1;
		takeC = taxiC - 1;

		int people = 0;
		while (people != M) {
			distance = Integer.MAX_VALUE;
			int nextR = N;
			int nextC = N;
			int restFuel = 0;
			int loc = -1;

			for (int l = 0; l < M; l++) {
				if (location[l][0] != -1) {
					visit = new boolean[N][N];
					int nowD = bfs(takeR, takeC, false, location[l][0], location[l][1]);
					if (distance > nowD && nowD != -1) {
						nextR = location[l][0];
						nextC = location[l][1];
						restFuel = fuel - nowD;
						loc = l;
						distance = nowD;
					} else if (distance == nowD && nowD != -1) {
						if (nextR > location[l][0]) {
							nextR = location[l][0];
							nextC = location[l][1];
							restFuel = fuel - nowD;
							loc = l;
							distance = nowD;
						} else if (nextR == location[l][0]) {
							if (nextC > location[l][1]) {
								nextC = location[l][1];
								restFuel = fuel - nowD;
								loc = l;
								distance = nowD;
							}
						}
					}
				}
			}
			if (loc != -1)
				location[loc][0] = -1; // 승객 처리

			if (restFuel < 0 || distance == Integer.MAX_VALUE) {
				fuel = -1;
				break;
			}

			visit = new boolean[N][N];
			int spend = bfs(nextR, nextC, true, location[loc][2], location[loc][3]);
			restFuel = restFuel - spend;

			if (restFuel < 0 || spend == -1) {
				fuel = -1;
				break;
			}
			restFuel += spend * 2;
			fuel = restFuel;
			people++;
		}
		System.out.println(fuel);
	}

	public static int bfs(int taxiR, int taxiC, boolean people, int locationR, int locationC) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.offer(new Point(taxiR, taxiC, 0));
		visit[taxiR][taxiC] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (!people && now.i == locationR && now.j == locationC) {
				return now.count;
			}
			if (people && now.i == locationR && now.j == locationC) {
				takeR = now.i;
				takeC = now.j;
				return now.count;
			}

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && !visit[nexti][nextj]
						&& map[nexti][nextj] == 0) {
					if (!people && distance >= now.count + 1) { // 가지치기
						queue.offer(new Point(nexti, nextj, now.count + 1));
						visit[nexti][nextj] = true;
					} else if (people) {
						queue.offer(new Point(nexti, nextj, now.count + 1));
						visit[nexti][nextj] = true;
					}
				}
			}
		}
		return -1;
	}

	public static class Point implements Comparable<Point> {
		int i, j, count;

		public Point(int i, int j, int c) {
			this.i = i;
			this.j = j;
			this.count = c;
		}

		@Override
		public int compareTo(Point o) {
			return this.count - o.count;
		}
	}
}
