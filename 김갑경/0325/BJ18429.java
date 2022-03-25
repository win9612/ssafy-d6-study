import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/18429
// 근손실
public class BJ18429 {

	private static int n, k, ans;
	private static boolean[] visit;
	private static int[] kit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parse(st.nextToken());
		k = parse(st.nextToken());
		kit = new int[n];
		visit = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			kit[i] = parse(st.nextToken());
		}

		// 순열
		permutation(0, 500);
		System.out.println(ans);
	}

	private static void permutation(int cnt, int w) {
		if (w < 500) {
			return;
		}

		if (cnt >= n) {
			ans++;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visit[i])
				continue;

			visit[i] = true;
			permutation(cnt + 1, w - k + kit[i]);
			visit[i] = false;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
