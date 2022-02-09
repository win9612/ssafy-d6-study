import java.io.*;
import java.util.*;

public class Main {

	private static int s, n, cnt;
	private static int[] arr;

  // https://www.acmicpc.net/problem/1182
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 부분수열의 개수: 2^20 = 백만
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		go(0, 0);

		// 0인 경우 공집합을 제외해줘야함
		if (s == 0)
			cnt--;

		System.out.println(cnt);
	} // end main()

	private static void go(int idx, int sum) {
		if (idx >= n) {
			if (sum == s)
				cnt++;
			return;
		}

		go(idx + 1, sum + arr[idx]);
		go(idx + 1, sum);
	}
}
