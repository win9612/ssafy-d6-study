package gold;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Gold19238_new2 {
	static int N, M, fuel;
	static int[][] arr;
	static Passenger[] passenger;
	static Taxi taxi;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int count; // 도착한 승객 수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		fuel = sc.nextInt();
		arr = new int[N + 1][N + 1];
		passenger = new Passenger[M + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 벽은 -1
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 1) {
					arr[i][j] = -1;
				}

			}
		}
		taxi = new Taxi(sc.nextInt(), sc.nextInt()); // 택시 위치

		// 승객 입력
		for (int i = 1; i <= M; i++) {
			int sr = sc.nextInt();
			int stc = sc.nextInt();
			int er = sc.nextInt();
			int ec = sc.nextInt();
			passenger[i] = new Passenger(i, sr, stc, er, ec);
			arr[sr][stc] = i;// 출발지에 승객번호 붙이기
		}

		for (int i = 0; i < M; i++) {
			drive();
		}
		if (count != M) {
			System.out.println(-1);
		} else {
			System.out.println(fuel);
		}

	}

	private static void drive() {
		int tr = taxi.r;
		int tc = taxi.c;
		if (arr[tr][tc] > 0) {// 도착지나 출발지에 사람이 있는경우
			Passenger cur = passenger[arr[tr][tc]];
			arrive(cur);
			return;
		}

		boolean[][] visited = new boolean[N + 1][N + 1];
		PriorityQueue<Passenger> list = new PriorityQueue<>();
		Queue<Taxi> q = new LinkedList<>();
		q.offer(new Taxi(tr, tc));
		visited[tr][tc] = true;

		int dist = 1;
		while (!q.isEmpty()) {
			int len = q.size();
			for (int i = 0; i < len; i++) {
				Taxi cur = q.poll();
				for (int k = 0; k < 4; k++) {
					int nr = cur.r + dc[k];
					int nc = cur.c + dr[k];

					if (nc < 1 || nr < 1 || nc > N || nr > N || arr[nr][nc] == -1 || visited[nr][nc])
						continue;
					visited[nr][nc] = true;

					if (arr[nr][nc] == 0) {// 길 인경우
						q.offer(new Taxi(nr, nc));
					} else if (arr[nr][nc] > 0) {// 승객이 있으면
						list.offer(passenger[arr[nr][nc]]);
					}
				}

			}

			if (!list.isEmpty())
				break; // 승객 찾았으면 break
			dist++;// 비용증가
		}

		if (list.isEmpty())
			return; // 승객 못태운채로 반복문 나왔으면
		if (fuel - dist < 0)
			return; // 가다가 연료 부족하면
		fuel -= dist;
		arrive(list.poll()); // 가장 가까운 승객 넘김

	}

	private static void arrive(Passenger pass) {

		boolean[][] visited = new boolean[N + 1][N + 1];
		Queue<Taxi> q = new LinkedList<>();
		q.offer(new Taxi(pass.sr, pass.sc));// 출발
		arr[pass.sr][pass.sc] = 0;

		int cost = 1;
		while (!q.isEmpty()) {
			if (cost > fuel) {// 연료 부족
				System.out.println(-1);
				System.exit(0);
			}
			int len = q.size();
			for (int p = 0; p < len; p++) {
//				System.out.println(len+" "+q.size());
				Taxi t = q.poll();

				for (int i = 0; i < 4; i++) {
					int nr = t.r + dc[i];
					int nc = t.c + dr[i];
					if (nc < 1 || nr < 1 || nc > N || nr > N || arr[nr][nc] == -1 || visited[nr][nc])
						continue;
					if (nr == pass.er && nc == pass.ec) {
						count += 1;
						taxi.r = nr;
						taxi.c = nc;
						fuel += cost;
						return;
					} else {
						q.offer(new Taxi(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
			cost += 1;

		}

	}

	static class Taxi {
		int r, c;

		public Taxi(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Passenger implements Comparable<Passenger> {
		int num;
		int sr, sc;
		int er, ec;

		public Passenger(int num, int sr, int sc, int er, int ec) {
			super();
			this.num = num;
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}

		@Override
		public int compareTo(Passenger o) {
			if (this.sr == o.sr) {
				return this.sc - o.sc;

			} else {
				return this.sr - o.sr;
			}
		}

	}

}