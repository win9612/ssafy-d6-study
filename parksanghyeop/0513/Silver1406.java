package silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Silver1406 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();
		int N = str.length();
		int M = Integer.parseInt(br.readLine());
		
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		for (int i = 0; i < N; i++)
			left.push(str.charAt(i));

		for (int i = 0; i < M; i++) {
			String[] commandArr = br.readLine().split(" ");
			String command = commandArr[0];

			switch (command) {
			case "L":
				if (!left.isEmpty())
					right.push(left.pop());
				break;
			case "D":
				if (!right.isEmpty())
					left.push(right.pop());
				break;
			case "B":
				if (!left.isEmpty())
					left.pop();
				break;
			case "P":
				char c = commandArr[1].charAt(0);
				left.push(c);
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!left.isEmpty())
			sb.append(left.pop());

		String result1 = sb.reverse().toString();

		sb= new StringBuilder();
		while (!right.isEmpty())
			sb.append(right.pop());

		String result2 = sb.toString();

		bw.write(result1 + result2);
		bw.flush();

	}
}
