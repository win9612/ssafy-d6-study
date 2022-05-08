import java.io.*;
import java.util.*;

public class BJ16956 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = parse(st.nextToken());
		int c = parse(st.nextToken());

		char[][] map = new char[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}

		final int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'W') {
					for (int d = 0; d < 4; d++) {
						int ni = i + move[d][0];
						int nj = j + move[d][1];

						if (!(ni >= 0 && nj >= 0 && ni < r && nj < c))
							continue;

						if (map[ni][nj] == 'S') {
							System.out.println(0);
							return;
						} else if (map[ni][nj] == '.') {
							map[ni][nj] = 'D';
						}
					}
				}
			}
		}
		System.out.println(1);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
