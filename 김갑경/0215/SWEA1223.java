import java.io.*;
import java.util.*;

public class SWEA1223 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = 10;
		for (int tc = 1; tc <= TC; tc++) {
			int l = Integer.parseInt(br.readLine());
			String s = br.readLine();
			StringBuilder sb = new StringBuilder();
			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < l; i++) {
				char c = s.charAt(i);
				if (c == '+' || c == '*') {
					while (!stack.isEmpty() && c >= stack.peek())
						sb.append(stack.pop());

					stack.push(c);
				} else
					sb.append(c);
			}
			while (!stack.isEmpty())
				sb.append(stack.pop());
			// 후위 표기식으로 변환(sb에 저장되어있음)

			// 후위표기식 계산용 스택
			Stack<Integer> stack2 = new Stack<>();
			for (int i = 0; i < l; i++) {
				char c = sb.toString().charAt(i);
				if (c == '+')
					stack2.push(stack2.pop() + stack2.pop());
				else if (c == '*')
					stack2.push(stack2.pop() * stack2.pop());
				else
					stack2.push(c - '0');

			} // 계산완료
			System.out.println("#" + tc + " " + stack2.pop());
		}
	}
} // end Class
