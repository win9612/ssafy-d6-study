import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/22859
// HTML 파싱

public class BJ22859 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "<"); // 여는 태그로 토큰
		st.nextToken(); // main태그 빼버리기

		StringBuilder sb = new StringBuilder();
		StringBuilder now = new StringBuilder();
		String prevClose = "";

		while (st.hasMoreTokens()) {
			String s = st.nextToken();
//			System.out.println(s);
			if (s.charAt(0) == '/') {
				// 닫는태그
				StringTokenizer tmp = new StringTokenizer(s, ">");
				StringTokenizer abc = new StringTokenizer(tmp.nextToken().substring(1), " ");
				prevClose = abc.nextToken();
				now.append(tmp.hasMoreTokens() ? tmp.nextToken() : "");
				if (prevClose.equals("p")) {
					sb.append(now.toString().replaceAll("\\s+", " ").trim() + "\n");
					now = new StringBuilder();
				}
			} else if (s.length() >= 3 && s.replaceAll("\\s+", " ").substring(0, 3).equals("div") && s.charAt(3) == ' '
					&& s.contains("title=\"")) {
				// div태그
				StringTokenizer tmp = new StringTokenizer(s, "\"");
				tmp.nextToken();
				sb.append("title : " + (tmp.hasMoreTokens() ? tmp.nextToken().replaceAll("\\s+", " ").trim() : "")
						+ "\n");
			} else {
				// 여는태그
				StringTokenizer tmp = new StringTokenizer(s, ">");
				tmp.nextToken();
				now.append(tmp.hasMoreTokens() ? tmp.nextToken() : "");
			}
		}
		System.out.println(sb.toString().trim());
	}
}
