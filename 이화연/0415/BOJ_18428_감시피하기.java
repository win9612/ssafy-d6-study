import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_18428_감시피하기 {
	static int N, map[][], ans;
	static ArrayList<Point> list, teacher;
	static boolean[] isSelected;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<Point>(); // 빈 칸 좌표
		teacher = new ArrayList<Point>(); // 선생님 좌표

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				String s = st.nextToken();
				if (s.equals("X")) { // 아무것도 존재하지 않음
					map[i][j] = 0;
					list.add(new Point(i, j));
				} else if (s.equals("T")) { // 선생님
					map[i][j] = 1;
					teacher.add(new Point(i, j));
				} else { // 학생
					map[i][j] = 2;
				}
			}
		}
		ans = 0; // 피할 수 없을때로 세팅
		isSelected = new boolean[list.size()];
		combi(0, 0);
		System.out.println(ans == 1 ? "YES" : "NO");

	}

	public static void combi(int cnt, int idx) {
		if (cnt == 3) {
			int[][] copy = new int[N][N];
			for (int i = 0; i < N; i++) { // copy
				for (int j = 0; j < N; j++) {
					copy[i][j] = map[i][j];
				}
			}

			for (int i = 0; i < list.size(); i++) { // 장애물 설치
				if (isSelected[i]) {
					copy[list.get(i).x][list.get(i).y] = 3;
				}
			}

			if (check(copy)) {
				ans = 1;
			}
			return;
		}

		if (idx >= list.size()) {
			return;
		}

		isSelected[idx] = true;
		combi(cnt + 1, idx + 1);

		isSelected[idx] = false;
		combi(cnt, idx + 1);
	}

	public static boolean check(int[][] copy) { // 감시
		for (Point p : teacher) {
			for (int d = 0; d < 4; d++) {
				int nexti = p.x;
				int nextj = p.y;
				while (true) {
					nexti += di[d];
					nextj += dj[d];
					if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= N || copy[nexti][nextj] == 3) {
						break;
					}
					if (copy[nexti][nextj] == 2) { // 학생을 만나면
						return false;
					}
				}
			}
		}
		return true;
	}

}
