import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시 {
	static int N, M, energy, map[][], count;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static Point taxi;
	static Point[] destination;

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x == o.x) {
				return this.y - o.y;
			} else {
				return this.x - o.x;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행,열 크기
		M = Integer.parseInt(st.nextToken()); // 승객 수
		energy = Integer.parseInt(st.nextToken()); // 초기 연료

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		// 택시 시작 위치
		taxi = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		destination = new Point[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s_x = Integer.parseInt(st.nextToken());
			int s_y = Integer.parseInt(st.nextToken());
			map[s_x][s_y] = i; // 승객의 출발지 위치의 승객 번호 지정
			int d_x = Integer.parseInt(st.nextToken());
			int d_y = Integer.parseInt(st.nextToken());
			destination[i] = new Point(d_x, d_y); // 승객 도착지
		}

		for (int i = 0; i < M; i++) {
			int pNo = findPassenger(taxi); // 승객의 번호
			energy -= count; // 움직인 거리 빼주기
			if (pNo == -1 || energy <= 0) { // 승객 번호가 -1이거나 연료가 없다면 리턴
				System.out.println(-1);
				return;
			}
			int use = go(taxi, pNo); // 목적지로 간다, 목적지로 가는 거리
			if (energy < use || use == -1) { // 현재 있는 연료보다 거리가 더 크면 갈수없음 or 아예 목적지에 갈 수 없음
				System.out.println(-1);
				return;
			}

			energy += use; // 움직인 거리만큼 더해주기
			map[taxi.x][taxi.y] = 0; // 해당 택시 위치 값 0으로 갱신
			taxi = destination[pNo]; // 택시 위치 도착지로 갱신
		}
		System.out.println(energy);
	}

	public static int go(Point taxi, int pNo) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		visited[taxi.x][taxi.y] = true;
		queue.offer(taxi);

		count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point p = queue.poll();

				if (p.x == destination[pNo].x && p.y == destination[pNo].y) { // 목적지에 도착했으면 거리 리턴
					return count;
				}

				for (int d = 0; d < 4; d++) {
					int nexti = p.x + di[d];
					int nextj = p.y + dj[d];
					if (nexti >= 1 && nexti <= N && nextj >= 1 && nextj <= N && !visited[nexti][nextj]
							&& map[nexti][nextj] >= 0) {
						queue.offer(new Point(nexti, nextj));
						visited[nexti][nextj] = true;
					}
				}
			}
			count++;
		}

		return -1; // 갈수 없으면
	}

	public static int findPassenger(Point now) { // 승객 찾기
		Queue<Point> queue = new LinkedList<Point>();
		PriorityQueue<Point> pqueue = new PriorityQueue<Point>(); // 승객 담을 pq
		boolean[][] visited = new boolean[N + 1][N + 1];
		queue.offer(taxi);
		visited[taxi.x][taxi.y] = true;

		count = 0; // 탐색 거리
		if (map[now.x][now.y] > 0) { // 현재 택시 위치에 승객이 있다면
			return map[now.x][now.y];
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point p = queue.poll();

				for (int d = 0; d < 4; d++) {
					int nexti = p.x + di[d];
					int nextj = p.y + dj[d];
					if (nexti >= 1 && nexti <= N && nextj >= 1 && nextj <= N && !visited[nexti][nextj]
							&& map[nexti][nextj] >= 0) {
						if (map[nexti][nextj] > 0) {
							pqueue.offer(new Point(nexti, nextj));
						}
						queue.offer(new Point(nexti, nextj));
						visited[nexti][nextj] = true;
					}
				}
			}
			count++; // 거리 증가
			if (!pqueue.isEmpty()) { // 같은 거리 탐색이 끝나면 손님이 있는지 확인
				taxi = pqueue.poll(); // 있으면 택시 위치 갱신
				return map[taxi.x][taxi.y];
			}
		}
		return -1; // 손님 찾을 수 없을 때
	}

}
