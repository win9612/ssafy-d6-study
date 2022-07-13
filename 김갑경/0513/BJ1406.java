import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1406
public class BJ1406 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine(); // 초기 문자열
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			left.add(s.charAt(i));
		}
		int m = parse(br.readLine()); // 명령어의 개수

		while (m-- > 0) {
			String tmp = br.readLine();
			char cmd = tmp.charAt(0);
			switch (cmd) {
			case 'L':
				if (!left.isEmpty()) {
					right.add(left.pop());
				}
				break;
			case 'D':
				if (!right.isEmpty()) {
					left.add(right.pop());
				}
				break;
			case 'B':
				if (!left.isEmpty()) {
					left.pop();
				}
				break;
			case 'P':
				left.add(tmp.charAt(2));
				break;

			}
		}

		StringBuilder sb = new StringBuilder();
		for (char c : left) {
			sb.append(c);
		}
		while (!right.isEmpty()) {
			sb.append(right.pop());
		}

		System.out.println(sb.toString());

	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
