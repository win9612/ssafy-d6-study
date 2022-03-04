import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_계산기2 {
	static int N;
	static Stack<Character> op = new Stack<>(); // 연산자 담는 스택
	static Stack<Integer>  opNum = new Stack<>(); // 피연산자 담는 스택
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1; tc<=10; tc++) {
			N = Integer.parseInt(br.readLine());
			String preOreder = br.readLine();
			String postOrder = "";
			
			for(int i=0; i<preOreder.length(); i++) {
				char ch = preOreder.charAt(i);
				if(ch - '0' > 0 && ch - '0' < 10) { // 숫자 넣기
					postOrder += ch;
				}else { // 연산자 넣기
					if(ch=='+') { // 더하기 인 경우, op스택에 *이 있는지 확인해야 한다.
						while(!op.isEmpty()) postOrder += op.pop();
						op.push(ch);
					}else op.push(ch);// 곱하기인 경우는 그냥 op스택에 넣는다.
				}
			}// 후위표기식 만들기 종료
			while(!op.isEmpty()) postOrder += op.pop(); //남아있는 연산자 다 넣기
			
			for(int i=0; i<postOrder.length(); i++) {
				char ch = postOrder.charAt(i);
				if(ch - '0' > 0 && ch - '0' < 10) { // 숫자인 경우 opNum스택에 넣는다.
					opNum.push(ch-'0');
				}else { // 연산자의 경우
					if(ch == '+') opNum.push(opNum.pop() + opNum.pop());
					else opNum.push(opNum.pop() * opNum.pop());
				}
			}
			
			System.out.println("#"+tc+" "+opNum.pop());
			
		}//tc종료
		
	}

}
