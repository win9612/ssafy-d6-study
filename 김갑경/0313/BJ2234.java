import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2234
// 성곽

public class BJ2234 {

	// 서 북 동 남
	private static final int[][] move = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	private static int n, m, roomN, maxSize;
	private static int[][] map, rooms;
	private static int[] roomSize;
	private static boolean[][] adjRoom;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parse(st.nextToken()); // 가로
		m = parse(st.nextToken()); // 세로
		roomN = 0;
		roomSize = new int[n * m + 1];
		map = new int[m][n];
		rooms = new int[m][n];
		adjRoom = new boolean[n * m + 1][n * m + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = parse(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) {
					// 한 번도 방문한 적 없는 지점을 때 그래프 탐색 시작
					roomN++;
					bfs(new Point(i, j));
				}
			}
		}

//		for (int i = 1; i <= roomN; i++) {
//			for (int j = 1; j <= roomN; j++) {
//				System.out.print(adjRoom[i][j] + " ");
//			}
//			System.out.println();
//		}

		int maxSizeByBrokenWall = 0;
		for (int i = 1; i <= roomN; i++) {
			for (int j = 1; j <= roomN; j++) {
				if (i == j)
					continue;
				if (adjRoom[i][j]) {
					// 이어질 수 있는 방이라면
					maxSizeByBrokenWall = Math.max(maxSizeByBrokenWall, roomSize[i] + roomSize[j]);
				}
			}
		}

		System.out.println(roomN);
		System.out.println(maxSize);
		System.out.println(maxSizeByBrokenWall);

	}

	private static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		rooms[start.i][start.j] = roomN;
		int currAreaSize = 0;

		while (!q.isEmpty()) {
			Point p = q.poll();
			String wall = String.format("%04d", Integer.parseInt(Integer.toBinaryString(map[p.i][p.j])));
			currAreaSize++;
			for (int i = 0; i < 4; i++) {
				int ni = p.i + move[i][0];
				int nj = p.j + move[i][1];

				// 이동할 수 없는 경우 = 방문한 적 있고 벽 있고 인덱스 유효X
				if (!checkIdx(ni, nj)) {
					continue;
				}
				if (rooms[ni][nj] != 0 || wall.charAt(3 - i) == '1') {
					adjRoom[rooms[p.i][p.j]][rooms[ni][nj]] = true;
					continue;
				}

				// 이동할 수 있으면 이동한다.
				q.offer(new Point(ni, nj));
				rooms[ni][nj] = roomN;
			}
		}
		roomSize[roomN] = currAreaSize;
		maxSize = Math.max(maxSize, currAreaSize);
	}

	private static boolean checkIdx(int i, int j) {
		return (i >= 0 && j >= 0 && i < m && j < n);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}

	private static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}
