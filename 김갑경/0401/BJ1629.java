import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1629
// 곱셈
public class BJ1629 {

	private static long c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long a = parse(st.nextToken());
		long b = parse(st.nextToken());
		c = parse(st.nextToken());

		System.out.println(find(a, b) % c);
	}

	private static long find(long a, long b) {
		if (b <= 1)
			return a % c;

		long tmp = find(a, b / 2) % c;
		long ans = (tmp * tmp) % c;

		if (b % 2 == 0) {
			return ans;
		} else {
			return (ans * a) % c;
		}
	}

	private static long parse(String s) {
		return Long.parseLong(s);
	}
}
