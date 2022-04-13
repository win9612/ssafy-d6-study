package day0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18808 {
	static int N, M, K, R, C;
	static int[][] notebook;
	static int[][] sticker;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		notebook = new int[N][M];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			sticker = new int[R][C];

			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if (!attach()) { // 0
				for (int i = 0; i < 3; i++) { // 90, 180, 270
					swap();
					rotate();
					if (attach())
						break;
				}
			}
		}
		System.out.println(countSticker());
	}

	public static void swap() {
		int temp = R;
		R = C;
		C = temp;
	}

	public static int countSticker() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (notebook[i][j] == 1)
					count++;
			}
		}
		return count;
	}

	public static void rotate() {
		int[][] rotateSticker = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				rotateSticker[i][C - 1 - j] = sticker[j][i];
			}
		}
		sticker = rotateSticker;
	}

	public static boolean attach() {
		for (int i = 0; i <= N - R; i++) {
			for (int j = 0; j <= M - C; j++) {
				int[][] copy = deepcopy(notebook);
				boolean keepGoing = true;

				for (int r = 0; r < R; r++) {
					if (!keepGoing)
						break;
					for (int c = 0; c < C; c++) {
						if (copy[i + r][j + c] == 1 && sticker[r][c] == 1) {
							keepGoing = false;
							break;
						}
						if (sticker[r][c] == 1)
							copy[i + r][j + c] = sticker[r][c];
					}
				}
				if (keepGoing) {
					notebook = copy;
					return true;
				}
			}
		}
		return false;
	}

	public static int[][] deepcopy(int[][] notebook) {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = notebook[i][j];
			}
		}
		return copy;
	}
}
