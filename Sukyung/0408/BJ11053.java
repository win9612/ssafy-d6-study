package day0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11053 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		int[] DP = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0;
		for (int i = 0; i < N; ++i) {
			DP[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (num[j] < num[i] && DP[i] < DP[j] + 1) {
					DP[i] = DP[j] + 1;
				}
			}
			if (max < DP[i])
				max = DP[i];
		}
		System.out.println(max);
	}
}
