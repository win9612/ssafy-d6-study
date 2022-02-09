import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/10971
public class Main {

	private static int n;
	private static int[][] cost;
	private static boolean[] visited;
	private static int[] arr;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 순열 수: 10! = 360만
		n = Integer.parseInt(br.readLine());
		cost = new int[n][n];
		visited = new boolean[n];
		arr = new int[n + 1];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				cost[i][j] = Integer.parseInt(st.nextToken());
		} // end Input

		go(0);
		System.out.println(min);
	}

	private static void go(int cnt) {
		if (cnt >= n) {
			int ans = 0;
			arr[n] = arr[0];
			for (int i = 0; i < n; i++) {
				int tmp = cost[arr[i]][arr[i + 1]];
				if (tmp == 0)
					return;
				else
					ans += tmp;
			}
			min = Math.min(min, ans);
		}

		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			arr[cnt] = i;
			go(cnt + 1);
			visited[i] = false;
		}
	}
}
