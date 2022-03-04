import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1120
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1 = st.nextToken();
		String s2 = st.nextToken();

		int min = 50;
		for (int i = 0; i < s2.length() - s1.length() + 1; i++) {
			// i: s1 포인터의 시작 위치
			int cnt = 0;
			for (int j = 0; j < s1.length(); j++) {
				if (s1.charAt(j) != s2.charAt(i + j))
					cnt++;
			}
			min = Math.min(min, cnt);
		}
		System.out.println(min);
	}
}
