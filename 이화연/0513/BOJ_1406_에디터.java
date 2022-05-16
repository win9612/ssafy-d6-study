import java.io.*;
import java.util.*;

public class BOJ_1406_에디터 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		Stack<String> left = new Stack<String>();
		Stack<String> right = new Stack<String>();

		for (int i = 0; i < s.length(); i++) {
			left.push(s.charAt(i) + "");
		}

		int M = Integer.parseInt(br.readLine()); // 명령어 개수
		for (int i = 0; i < M; i++) {
			String s2 = br.readLine(); // L,D,B,P 저장할 변수
			String s3 = ""; // $ 저장할 변수
			if (s2.length() > 1) { // P $ 인경우
				StringTokenizer st = new StringTokenizer(s2);
				s2 = st.nextToken();
				s3 = st.nextToken();
			}

			switch (s2) {
			case "L":
				if (!left.isEmpty()) {
					right.push(left.pop());
				}
				break;
			case "D":
				if (!right.isEmpty()) {
					left.push(right.pop());
				}
				break;
			case "B":
				if (!left.isEmpty()) {
					left.pop();
				}
				break;
			case "P":
				left.push(s3);
				break;
			}
		}
		StringBuilder sb = new StringBuilder();

		while (!left.isEmpty()) {
			right.push(left.pop());
		}
		while(!right.isEmpty()) {
			sb.append(right.pop());
		}
		System.out.println(sb.toString());
	}

}
