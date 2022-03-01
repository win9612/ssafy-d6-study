import java.io.*;
import java.util.*;

public class Main {

  // 움직이는 미로탈출 - https://www.acmicpc.net/problem/16954
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[8][8];
		for (int i = 0; i < 8; i++)
			map[i] = br.readLine().toCharArray();

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(7, 0));

		int[][] move = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 },
				{ 0, 0 } };

		// 벽을 먼저 이동시키고 욱제를 체크
		for (int time = 1; time < 9; time++) {
			int size = q.size();

			if (size == 0) {
				System.out.println(0);
				return;
			}

			for (int s = 0; s < size; s++) {
				Point p = q.poll();
				for (int i = 0; i < 9; i++) {
					int ni = p.i + move[i][0];
					int nj = p.j + move[i][1];

					if (ni < 0 || nj < 0 || ni >= 8 || nj >= 8)
						continue;

					if (ni - time >= 0 && map[ni - time][nj] == '#')
						continue;
					if (ni - time + 1 >= 0 && map[ni - time + 1][nj] == '#')
						continue;

					if (ni < time) {
						System.out.println(1);
						return;
					}
					q.offer(new Point(ni, nj));
				}
			} // 현재 시간에서 이동할 수 있는 경우의수를 전부 비교
		}

		System.out.println(1);
	}

	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
