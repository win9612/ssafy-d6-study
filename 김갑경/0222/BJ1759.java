import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1759
public class Main {
	private static char[] alphabets;
	private static int L, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = parse(st.nextToken());
		C = parse(st.nextToken());
		alphabets = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			alphabets[i] = st.nextToken().charAt(0);

		Arrays.sort(alphabets);
		go(0, 0, 0, "");
	}

	private static void go(int cnt, int idx, int mo, String s) {
		if (cnt >= L) {
			if (mo >= 1 && L - mo >= 2)
				System.out.println(s);
			return;
		}
		if (idx >= C)
			return;

		char c = alphabets[idx];
		go(cnt + 1, idx + 1, mo + ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0), s + c);
		go(cnt, idx + 1, mo, s);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
