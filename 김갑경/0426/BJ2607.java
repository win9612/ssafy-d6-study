import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2607

public class BJ2607 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] cnt = getStrCnt(br.readLine());

		int ans = 0;
		for (int i = 1; i < n; i++) {
			int[] tmp = getStrCnt(br.readLine());
			if (compare(cnt, tmp))
				ans++;
		}
		System.out.println(ans);
	}

	private static boolean compare(int[] cnt, int[] tmp) {

		boolean up = false;
		boolean down = false;

		for (int j = 0; j < 26; j++) {
			if (Math.abs(cnt[j] - tmp[j]) >= 2)
				return false;
			else if (Math.abs(cnt[j] - tmp[j]) == 1) {
				if (cnt[j] > tmp[j]) {
					if (up)
						return false;
					up = true;
				} else {
					if (down)
						return false;
					down = true;
				}
			}
		}

		return true;
	}

	private static int[] getStrCnt(String s) {
		int[] cnt = new int[26];
		for (int i = 0; i < s.length(); i++) {
			cnt[s.charAt(i) - 'A']++;
		}
		return cnt;
	}
}
