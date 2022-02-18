import java.io.*;
import java.util.*;

public class BJ2110 {

	private static int n;
	private static int[] houses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parse(st.nextToken()); // 집의 개수
		int c = parse(st.nextToken()); // 공유기 개수
		houses = new int[n];
		for (int i = 0; i < n; i++)
			houses[i] = parse(br.readLine());
		Arrays.sort(houses);

		int l = 1;
		int r = houses[n - 1] - houses[0];

		int ans = 1;
		while (l <= r) {
			// mid로 놓을 수 있는 최대 공유기의 개수
			int mid = (l + r) >> 1;
//			System.out.println(mid + " " + getDist(mid));
			// mid로놓았을 때 공유기를 c개보다 많이 놓을 수 있다면 : 범위를 줄인다
			if (getDist(mid) >= c) {
				ans = Math.max(ans, mid);
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.println(ans);
	}

	private static int getDist(int dist) {
		int ans = 1;
		int last = houses[0];
		for (int i = 0; i < n; i++) {
			// 거리가 dist보다 작다면 더해서 비교해본다.
			if (houses[i] - last >= dist) {
				ans++;
				last = houses[i];
			}
		}
		return ans;
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
