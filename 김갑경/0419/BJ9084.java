import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = parse(br.readLine());

		while (TC-- > 0) {
			int n = parse(br.readLine()); // 동전의 가짓수
			int[] coins = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				coins[i] = parse(st.nextToken());
			}
			int m = parse(br.readLine());
			int[] dp = new int[m + 1];
			dp[0] = 1;

			for (int i = 0; i < n; i++) {
				for (int j = coins[i]; j <= m; j++) {
					dp[j] += dp[j - coins[i]];
				}
			}
			System.out.println(dp[m]);
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
