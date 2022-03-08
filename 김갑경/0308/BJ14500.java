import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14500
// BJ14500: 테트로미노
public class BJ14500 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken());
		int m = parse(st.nextToken());

		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = parse(st.nextToken());
			}
		}

		int max = 0;
		// 블록 종류 바꾸기
		for (int i = 0; i < block.length; i++) {
			// 시작점 바꾸기
			for (int j = 0; j < n - block[i][0][0] + 1; j++) {
				for (int k = 0; k < m - block[i][0][1] + 1; k++) {
					int score = 0;
					for (int l = 1; l <= 4; l++) {
						score += map[j + block[i][l][0] - 1][k + block[i][l][1] - 1];
					}
					max = Math.max(max, score);
				}
			}
		}
		System.out.println(max);

	}

	private static int[][][] block = {
			// 긴 가로 블록
			{ { 1, 4 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 } },
			// 긴 세로 블록
			{ { 4, 1 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 } },
			// 네모 블록
			{ { 2, 2 }, { 1, 1 }, { 1, 2 }, { 2, 1 }, { 2, 2 } },
			// ㄴ자 블록 (1)
			{ { 3, 2 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 3, 2 } },
			// ㄴ자 블록(2)
			{ { 3, 2 }, { 1, 2 }, { 2, 2 }, { 3, 2 }, { 3, 1 } },
			// ㄴ자 블록(3)
			{ { 2, 3 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 1, 3 } },
			// ㄴ자 블록(4)
			{ { 2, 3 }, { 1, 1 }, { 2, 1 }, { 2, 2 }, { 2, 3 } },
			// ㄴ자 블록(5)
			{ { 3, 2 }, { 1, 1 }, { 1, 2 }, { 2, 2 }, { 3, 2 } },
			// ㄴ자 블록(6)
			{ { 3, 2 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 1, 2 } },
			// ㄴ자 블록(7)
			{ { 2, 3 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 2, 1 } },
			// ㄴ자 블록(8)
			{ { 2, 3 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 2, 3 } },
			// ㄹ자 블록(1)
			{ { 3, 2 }, { 1, 1 }, { 2, 1 }, { 2, 2 }, { 3, 2 } },
			// ㄹ자 블록(2)
			{ { 3, 2 }, { 1, 2 }, { 2, 2 }, { 2, 1 }, { 3, 1 } },
			// ㄹ자 블록(3)
			{ { 2, 3 }, { 1, 1 }, { 1, 2 }, { 2, 2 }, { 2, 3 } },
			// ㄹ자 블록(4)
			{ { 2, 3 }, { 1, 2 }, { 1, 3 }, { 2, 1 }, { 2, 2 } },
			// ㅗ자 블록
			{ { 2, 3 }, { 1, 2 }, { 2, 1 }, { 2, 2 }, { 2, 3 } },
			// ㅓ자 블록
			{ { 3, 2 }, { 1, 2 }, { 2, 1 }, { 2, 2 }, { 3, 2 } },
			// ㅏ자 블록
			{ { 3, 2 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 2, 2 } },
			// ㅜ자 블록
			{ { 2, 3 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 2, 2 } } };

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
