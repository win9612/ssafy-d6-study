package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver18428 {
	static int n;
	static String[][] arr, temp;
	static boolean[][] visited;
	static boolean result = false;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new String[n][n];
		temp = new String[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				String str = st.nextToken();
				arr[i][j] = str;
			}
		}

		perm(0);
		System.out.println(result ? "YES" : "NO");
	}

	static void perm(int cnt) {
		if (cnt == 3) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					String teacher = arr[i][j];

					if (teacher.equals("T")) {
						for (int k = 0; k < 4; k++) {
							if (find(i + dr[k], j + dc[k], dr[k], dc[k]))
								return;
						}
					}
				}
			}
			result = true;
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				String str = arr[i][j];

				if (str.equals("X") && !visited[i][j]) {
					visited[i][j] = true;

					arr[i][j] = "O";
					perm(cnt + 1);

					if (result)
						return;

					arr[i][j] = "X";
					visited[i][j] = false;
				}
			}
		}

	}

	static boolean find(int i, int j, int r, int c) {

		if (i >= 0 && j >= 0 && i < n && j < n) {
			String str = arr[i][j];
			if (str.equals("O")) {
				return false;
			} else if (str.equals("S")) {
				return true;
			} else {
				return find(i + r, j + c, r, c);
			}
		}
		return false;
	}
}
