package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gold20056 {
	static int N, M, K;
	static int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static ArrayList<Fire>[][] arr;
	static Queue<Fire> q = new LinkedList<Fire>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			q.add(new Fire(r - 1, c - 1, m, s, d));
		}

		find();
	}

	static void find() {

		for (int t = 0; t < K; t++) {// k번 파이어볼이동
			int len = q.size();
			for (int move = 0; move < len; move++) {
				Fire cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				int w = cur.m;
				int s = cur.s;
				int d = cur.d;
				int nr = r + dc[d] * (s % N);
				int nc = c + dr[d] * (s % N);
				nr = (nr + N) % N;
				nc = (nc + N) % N;
				arr[nr][nc].add(new Fire(nr, nc, w, s, d));

			}

			// 이동 후 합치고 분할
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (arr[row][col].size() == 0)
						continue;
					if (arr[row][col].size() == 1) {// 1개인 칸 큐에 넣음
						q.add(arr[row][col].get(0));
						continue;
					}
					// 2개 이상인 칸
					int weight = 0;
					int fast = 0;
					int odd = 0;
					int even = 0;
					for (int i = 0; i < arr[row][col].size(); i++) {
						weight += arr[row][col].get(i).m;
						fast += arr[row][col].get(i).s;
						if ((arr[row][col].get(i).d) % 2 == 0) {
							even++;
						} else {
							odd++;
						}
					}
					weight /= 5;
					fast /= arr[row][col].size();
					// 질량이 0 삭제
					if (weight == 0)
						continue;
					if (odd == 0 || even == 0) {
						q.offer(new Fire(row, col, weight, fast, 0));
						q.offer(new Fire(row, col, weight, fast, 2));
						q.offer(new Fire(row, col, weight, fast, 4));
						q.offer(new Fire(row, col, weight, fast, 6));
					} else {
						q.offer(new Fire(row, col, weight, fast, 1));
						q.offer(new Fire(row, col, weight, fast, 3));
						q.offer(new Fire(row, col, weight, fast, 5));
						q.offer(new Fire(row, col, weight, fast, 7));
					}
				}
			}

			// 초기화
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					arr[row][col] = new ArrayList<>();
				}
			}

		}

		int result = 0;
		int len = q.size();
		for (int i = 0; i < len; i++) {
			result += q.poll().m;
		}
		System.out.println(result);
	}

	static class Fire {
		int r, c, m, s, d;

		public Fire(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;

		}

		@Override
		public String toString() {
			return "Fire [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}
}
