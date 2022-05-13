package Day0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon1406_에디터 {

	public static void main(String[] args) throws IOException {
		// TODO 에디터
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		Stack<Character> stack_jinzza = new Stack<Character>();
		Stack<Character> stack_gazza = new Stack<Character>();
		
		String str = br.readLine();
		int size = str.length();
		// stack에 넣는다. 커서는 자동으로 끝에 위치
		for(int i=0; i<size; i++) {
			stack_jinzza.add(str.charAt(i));
		}
		
		// 명령어 개수 입력
		int N = Integer.parseInt(br.readLine());
		
		// 명령어 실행
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			String order = st.nextToken();
			char somoonja;
			
			if(order.equals("P")) {
				somoonja = st.nextToken().charAt(0); 
				stack_jinzza.add(somoonja); // 메인 스택에 추가
			}
			if(order.equals("L")) {
				if(!stack_jinzza.isEmpty()) {
					stack_gazza.add(stack_jinzza.pop()); // 메인 스택에서 빼서 임시 스택으로 옮긴다. -> 커서 이동 효과
				}
			}
			if(order.equals("D")) {
				if(!stack_gazza.isEmpty()) { // 임시 스택에서 메인 스택으로 옮긴다. -> 커서 이동 효과
					stack_jinzza.add(stack_gazza.pop());
				}
			}
			if(order.equals("B")) {
				if(!stack_jinzza.isEmpty()) {
					stack_jinzza.pop(); // 메인 스택에서 지워준다.
				}
				
			}
		}
		
		// 임시 스택에서 메인 스택으로 다 옮긴다.
		int tempsize = stack_gazza.size();
		for(int i=0; i<tempsize; i++) {
			stack_jinzza.add(stack_gazza.pop());
		}
		
		// 출력
		tempsize = stack_jinzza.size();
		for(int i=0; i<tempsize; i++) {
			sb.append(stack_jinzza.get(i));
		}
		System.out.println(sb);
		
		
		
	}
}
