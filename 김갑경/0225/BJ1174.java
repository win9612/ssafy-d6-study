import java.io.*;
import java.util.*;

// 줄어드는 수: https://www.acmicpc.net/problem/1174
// 쓸데없이 dp로 어렵게풀음;;
public class Main {

	private static int[][] dp;
	private static int n;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = parse(br.readLine());

		// dp[i번 자리수에서][j로 시작하는 줄어드는 수의 개수]
		// dp[i][10]은 해당 열의 총합
		dp = new int[10][11];
		Arrays.fill(dp[0], 1);
		dp[0][10] = 10;

		for (int i = 1; i < 10; i++) {
			for (int j = i; j < 10; j++) {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
				dp[i][10] += dp[i][j];
			}
		}

		while (n >= 1)
			n = findNum();
		System.out.println(sb);
	}

	private static int findNum() {
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			int cntRow = 0;
			for (int j = i; j < 10; j++) {
				cnt += dp[i][j];
				cntRow += dp[i][j];
				if (cnt >= n) {
					// 감소하는 수의 자리수를 찾았으면 sb에 붙여준다.
					sb.append(j);
					// 첫 열이면 멈춰야함
					if (i == 0)
						return 0;
					// n을 한 열 이전의 해당 수로 보내서 다시 하면 됨
					return n - dp[i - 1][10] - cntRow + dp[i][j];
				}
			}
		}
		sb.append("-1");
		return -1;
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
