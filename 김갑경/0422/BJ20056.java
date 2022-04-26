import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/20056
public class BJ20056 {

	private static int n, m, k;
	private static final int move[][] = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };
	private static final int ndir[][] = { { 0, 2, 4, 6 }, { 1, 3, 5, 7 } };
	private static int[][][] map;
	private static Queue<Fireball> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parse(st.nextToken()); // 맵 크기
		m = parse(st.nextToken()); // 파이어볼 개수
		k = parse(st.nextToken()); // 명령 개수

		map = new int[n][n][5]; // 개수 질량 속도 방향

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			Fireball fb = new Fireball(parse(st.nextToken()) - 1, parse(st.nextToken()) - 1, parse(st.nextToken()),
					parse(st.nextToken()), parse(st.nextToken()));

			q.offer(fb);

			change(fb.r, fb.c, fb.m, fb.s, fb.d, 1);
		}

		while (k-- > 0) {
			// 1. 파이어볼 움직이기
			moveFire();
			// 2. 합쳐진 파이어볼 분해하기
			divideFire();
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans += map[i][j][1];
			}
		}
		System.out.println(ans);
	}

	private static void divideFire() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j][0] == 0) {
					continue;
				} else if (map[i][j][0] == 1) {
					q.offer(new Fireball(i, j, map[i][j][1], map[i][j][2], map[i][j][4]));
				} else {
					int nm = map[i][j][1] / 5;
					if (nm == 0) {
						for (int d = 0; d < 5; d++) {
							map[i][j][d] = 0;
						}
						continue;
					}
					int ns = map[i][j][2] / map[i][j][0];
					int flag = (map[i][j][0] == Math.abs(map[i][j][3])) ? 0 : 1;
					for (int d = 0; d < 4; d++) {
						q.offer(new Fireball(i, j, nm, ns, ndir[flag][d]));
					}

					map[i][j][0] = 4;
					map[i][j][1] = nm * 4;
					map[i][j][2] = ns * 4;
					map[i][j][3] = 4 * (flag == 0 ? 1 : -1);
					map[i][j][4] = flag == 0 ? 12 : 16;
				}
			}
		}
	}

	private static void moveFire() {
		while (!q.isEmpty()) {
			Fireball fb = q.poll();

			change(fb.r, fb.c, fb.m, fb.s, fb.d, -1);

			// 움직인다.
			int nr = (fb.r + move[fb.d][0] * fb.s + 10000 * n) % n;
			int nc = (fb.c + move[fb.d][1] * fb.s + 10000 * n) % n;

			change(nr, nc, fb.m, fb.s, fb.d, 1);
		}
	}

	private static void change(int r, int c, int m, int s, int d, int flag) {
		map[r][c][0] += 1 * flag; // 개수
		map[r][c][1] += m * flag; // 질량
		map[r][c][2] += s * flag; // 속도
		map[r][c][3] += ((d % 2 == 0) ? 1 : -1) * flag; // 방향플래그
		map[r][c][4] += d * flag; // 방향
	}

	private static class Fireball {
		int r, c, m, s, d; // 질량, 속도, 방향

		Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
