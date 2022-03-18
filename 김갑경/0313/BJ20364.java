import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/20364
// 부동산 다툼
public class BJ20364 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken()); // 땅 개수
		int q = parse(st.nextToken()); // 꽊꽊나라 오리 수

		boolean[] visit = new boolean[n + 1];
		StringBuilder sb = new StringBuilder();
		while (q-- > 0) {
			int duck = parse(br.readLine()); // 오리가 갖고싶은 땅 번호
			int tmp = duck;

			int ground = 0;
			while (tmp > 1) {
				if (visit[tmp]) {
					ground = tmp;
				}
				tmp /= 2;
			}
			if (ground == 0) {
				visit[duck] = true;
			}
			sb.append(ground + "\n");
		}
		System.out.println(sb);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
