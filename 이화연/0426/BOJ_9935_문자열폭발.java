import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_문자열폭발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		String explosion = br.readLine();

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			stack.add(s.charAt(i));
			if (stack.size() >= explosion.length()) { // 폭발문자열 길이보다 많거나 같을때 비교
				boolean flag = true;
				for (int j = 0; j < explosion.length(); j++) {
					char c1 = stack.get(stack.size() - explosion.length() + j);
					char c2 = explosion.charAt(j);
					if (c1 != c2) { // 폭발 문자열 못찾으면
						flag = false;
						break;
					}
				}
				if (flag) { // 폭발 문자열 찾으면
					for (int j = 0; j < explosion.length(); j++) {
						stack.pop(); // 스택에서 꺼내기
					}
				}
			}
		}
		
		if(stack.size() == 0) {
			System.out.println("FRULA");
		}else {
			for(int i=0; i<stack.size(); i++) {
				sb.append(stack.get(i));
			}
		}
		System.out.println(sb.toString());
	}

}
