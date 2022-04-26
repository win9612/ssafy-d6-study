import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/16198
public class BJ16198 {

	private static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		go(n, 0, arr);
		System.out.println(max);
	}

	private static void go(int n, int sum, int[] arr) {
		if (n <= 2)
			max = Math.max(max, sum);

		int[] tmp = arr.clone();
		for (int i = 1; i < n - 1; i++) {
			// 해당 구슬을 선택
			go(n - 1, sum + arr[i - 1] * arr[i + 1], erase(arr, n, i));
			arr = tmp.clone();
		}
	}

	private static int[] erase(int[] arr, int n, int select) {
		for (int i = select; i < n - 1; i++) {
			arr[i] = arr[i + 1];
		}
		return arr;
	}
}
