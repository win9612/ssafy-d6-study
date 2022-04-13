package Day0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1120_문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		// A 입력
		String A = st.nextToken();
		// B 입력
		String B = st.nextToken();
		
		int answer = 0;
		int max_count = 0;
		int main_index = 0; // 가장 최적의 인덱스 저장
		
		// B의 인덱스를 처음 부터 탐색. 이 때 B를 A크기만큼 잘라낼것이기 때문에 B길이 - A길이 인덱스 까지 탐색
		for(int i=0; i<B.length()-A.length()+1; i++) {
			int temp_count = 0; // 일치하는 값의 갯수
			
			// B의 현재 인덱스부터 A인덱스 만큼 잘라내서 Sub_B에 저장
			String Sub_B = B.substring(i, i+A.length());
			
			// 현재 B에서 잘라낸 부분과 A를 비교하여 일치하는 문자가 가장 많은 경우의 인덱스를 저장한다.
			for(int j=0; j<A.length(); j++) {
				if(Sub_B.charAt(j)==A.charAt(j)) temp_count++;
			}
			// 가장 일치하는 값이 큰 인덱스를 저장한다.
			if(temp_count>max_count) {
				max_count = temp_count;
				main_index=i;
			}	
		}
		
		
		// 위에서 확정된 인덱스를 통해 최종적으로 일치하지 않은 문자의 갯수를 구한다.
		for(int j=0; j<A.length(); j++) {
			char a = A.charAt(j);
			char b = B.charAt(main_index+j);
			if(a != b) {
				answer++;
			}
		}
		
		System.out.println(answer);
		
	}
}
