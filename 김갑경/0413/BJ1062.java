import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1062
// 가르침

public class BJ1062 {

	private static int n, k;
	private static boolean[] alphabet;
	private static String[] strs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parse(st.nextToken()); // 단어의 개수
		k = parse(st.nextToken()); // 글자

		alphabet = new boolean[26];

		if (k < 5) {
			System.out.println(0);
			return;
		}

		char[] defaultAlphabet = { 'a', 'n', 't', 'i', 'c' };

		for (char c : defaultAlphabet) {
			alphabet[c - 'a'] = true;
		}

		strs = new String[n];
		for (int i = 0; i < n; i++) {
			strs[i] = br.readLine();
		}

		comb(0, 0);
		System.out.println(max);
	}

	private static int max;

	private static int canRead() {
		int ans = 0;

		for (int i = 0; i < n; i++) {
			boolean flag = true;
			for (int j = 0; j < strs[i].length(); j++) {
				if (!alphabet[strs[i].charAt(j) - 'a']) {
					flag = false;
					;
					break;
				}
			}
			if (flag) {
				ans++;
			}
		}
		return ans;
	}

	private static void comb(int cnt, int before) {
		if (cnt >= k - 5) {
			max = Math.max(max, canRead());
			return;
		}

		for (int i = before; i < 26; i++) {
			if (alphabet[i]) {
				continue;
			}

			alphabet[i] = true;
			comb(cnt + 1, i + 1);
			alphabet[i] = false;
		}

	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
