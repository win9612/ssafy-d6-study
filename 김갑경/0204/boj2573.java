import java.io.*;
import java.util.*;

// 백준 2573
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 행 수
		int m = Integer.parseInt(st.nextToken()); // 열 수

		int[][] move = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

		int[][] map = new int[n][m];
		int year = 0; // 바퀴 수 저장
		int tot = 0;

		// 입력부
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					tot++;
			}
		} // end Input

		boolean isMelt = false;
		while (!isMelt) {
			boolean[][] visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0 || visited[i][j])
						continue;

					int curr = 0;
					int next = tot;
					Queue<Point> q = new LinkedList<>();
					q.add(new Point(i, j));
					while (!q.isEmpty()) {
						Point p = q.poll();
						int x = p.x;
						int y = p.y;

						if (visited[y][x])
							continue;

						visited[y][x] = true;
						curr++;

						// 4방향 탐색
						for (int k = 0; k < 4; k++) {
							int ny = y + move[k][0];
							int nx = x + move[k][1];

							if (ny >= n || nx >= m || ny < 0 || nx < 0) // 인덱스 유효성 검사
								continue;

							if (visited[ny][nx] || map[ny][nx] >= 1) {
								// 빙산인 경우 : 큐에 넣기
								q.add(new Point(ny, nx));
							} else {
								// 바다인 경우: 현재높이 감소
								if (map[y][x] >= 1)
									map[y][x]--;
							}
						} // end 4방탐색

						if (map[y][x] == 0)
							next--;
					} // end BFS

					if (tot != curr) {
						System.out.println(year);
						return;
					}
					year++;

					tot = next;
					if (tot == 0) {
						// 다 녹음 > 분리영역 찾을 수 없음: while문을 탈출하여 0 출력
						isMelt = true;
					}
				}
			} // 2중for문
		} // isSeperated
		System.out.println("0"); // 한꺼번에 녹을 경우 0 출력
	} // end main
} // end class

class Point {
	int x, y;

	Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}
