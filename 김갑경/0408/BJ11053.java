import java.io.*;
import java.util.*;

// 가장 긴 증가하는 부분수열
public class BJ11053 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = parse(br.readLine());
		int[] arr = new int[n];
		int[] dp = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = parse(st.nextToken());
			dp[i] = 1;
		}

		int max = 1;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (arr[i] < arr[j]) {
					dp[j] = Math.max(dp[i] + 1, dp[j]);
					max = Math.max(dp[j], max);
				}
			}
		}
		System.out.println(max);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
