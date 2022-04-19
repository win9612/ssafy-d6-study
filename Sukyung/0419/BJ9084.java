package day0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9084 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] coin = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 1; i <= N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int DP[] = new int[10001];

			for (int i = 1; i <= N; i++) {
				for (int j = coin[i]; j <= M; j++) {
					if (j == coin[i])
						DP[j] += 1;
					DP[j] += DP[j - coin[i]];
				}
			}
			System.out.println(DP[M]);
		}
	}
}
