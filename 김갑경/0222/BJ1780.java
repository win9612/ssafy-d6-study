import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1780
public class Main {

	private static int[][] arr;
	private static int[] cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parse(br.readLine());

		arr = new int[n][n];
		cnt = new int[3];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = parse(st.nextToken()) + 1;
			}
		}

		go(n, 0, 0);
		System.out.println(cnt[0] + "\n" + cnt[1] + "\n" + cnt[2]);
	}

	// 3등분
	private static void go(int n, int i, int j) {
		// 애초에 쪼개서 검사하는게 아니고 쪼개질때만 검사
		if (check(n, i, j)) {
			// true면 해당 칸만 더함
			cnt[arr[i][j]]++;
			return;
		} else {
			// 쪼개질때만 검사
			n /= 3;
			for (int k = 0; k < 3; k++) {
				for (int l = 0; l < 3; l++) {
					go(n, i + n * k, j + n * l);
				}
			}
		}
	}

	private static boolean check(int n, int i, int j) {
		for (int a = i; a < i + n; a++) {
			for (int b = j; b < j + n; b++) {
				if (arr[i][j] != arr[a][b])
					return false;
			}
		}
		return true;
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
