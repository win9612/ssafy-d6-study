package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver16956 {

	static int R, C;
	static char[][] arr;
	static boolean flag = true;

	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++)
				arr[i][j] = str.charAt(j);
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 'W') {
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];

						if (nr < 0 || nc < 0 || nr >= R || nc >= C)
							continue;
						
						if (arr[nr][nc] == '.') {
							arr[nr][nc] = 'D';
						} else if (arr[nr][nc] == 'S') {
							System.out.println(0);
							return;
						}
					}
				}
			}
		}

		System.out.println(1);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}

	}

}
