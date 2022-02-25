import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16954_움직이는미로탈출 {
	static char[][] chess;
	static int[] di = { -1, -1, -1, 0, 1, 1, 1, 0, 0 }; // 좌상, 상, 우상, 우 ,우하, 하, 좌하, 좌, 그대로
	static int[] dj = { -1, 0, 1, 1, 0, -1, -1 - 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		chess = new char[8][8]; // 8x8 체스판
		for (int i = 0; i < 8; i++) {
			chess[i] = br.readLine().toCharArray();
		}

		dfs(7, 0); // 가장 왼쪽 아랫 칸에서 시작

	}

	public static void dfs(int i, int j) {
		System.out.println(i + "," + j);
		if (i == 0 && j == 7) { // 가장 오른쪽 윗 칸에 도착했을때 끝내기
//			System.out.println(1);
			return;
		}

		for (int d = 0; d < 9; d++) {
			int nexti = i + di[d];
			int nextj = j + dj[d];
			System.out.println(nexti + "," + nextj);

			// 이동하려는 칸의 인덱스가 유효하고 빈칸일때
			if (nexti >= 0 && nexti < 8 && nextj >= 0 && nextj < 8 && chess[nexti][nextj] == '.') {
				if (nexti - 1 >= 0 && chess[nexti - 1][nextj] == '.') { // 다음으로 이동하는 칸의 위가 빈칸이라면 이동 가능
//					System.out.println(nexti + "," + nextj);
					System.out.println(chess[nexti - 1][nextj]);
					move(); // 모든 벽 아래에 있는 행으로 한 칸씩 내리기
					dfs(nexti, nextj);
				}
			}
		}
	}

	public static void move() {
		boolean[][] visited = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chess[i][j] == '#' && !visited[i][j]) { // 해당 칸이 벽이라면
					if (i + 1 < 8) {
						chess[i][j] = '.'; // 해당 칸을 빈칸으로 바꾸고
						chess[i + 1][j] = '#'; // 해당 칸 아래에 있는 칸을 벽으로 바꾼다
						visited[i + 1][j] = true; // 다음 반복때 또 바꾸면 안되니까 true로 변경
					} else { // 가장 아래에 있는 칸이라면 그냥 빈칸으로
						chess[i][j] = '.';
					}
				}
			}
		}

		chess[7][0] = '.'; // 가장 왼쪽 아랫칸은 항상 벽이 아님

		for (char[] n : chess) {
			for (char nn : n) {
				System.out.print(nn + " ");
			}
			System.out.println();
		}
	}

}
