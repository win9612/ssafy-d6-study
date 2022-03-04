package Day0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1174_줄어드는수 {
	static int size;
	static int n;
	static int[] number = {0,1,2,3,4,5,6,7,8,9};
	static boolean[] selected; 
	static int[] result;
	static int count;
	static int[] answer;
	static int end_count;
	static boolean is_fail; // 실패하면 -1 출력하도록 할 수 있게 boolean 변수 생성
	
	public static void main(String[] args) throws IOException {
		// TODO 줄어드는 수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		size = input.toCharArray().length; // 자릿수
		n = Integer.parseInt(input); // n 
		selected = new boolean[10]; // 0~9 뭐가 사용됐는지 체크
		result = new int[size];
		answer = new int[size];
		end_count = factorial(10) / (factorial(size)*factorial(10-size));
		//System.out.println("end_count : "+ end_count);
		
		
		if(n>end_count) System.out.println(-1);
		else {
			Permutation(0);
			for(int i=0; i<answer.length; i++) {
				System.out.print(answer[i]);
			}
		}
		
	}
	
	static void Permutation(int idx) {
		
		if(count > end_count) {
			is_fail = true;
			return; // 10Csize 경우의 수만큼을 넘으면 종료한다.
		}
			
		if(idx == size) {
			count++;
			//System.out.println(Arrays.toString(result));
			//System.out.println("count : " + count);
			
			if(count == n) {		
				for(int i=0; i<result.length;i++) {
					answer[i] = result[i];
				}
				return;
			}else return;
		}
		
		for(int i=0; i<10; i++) {
			
			if(selected[i]==false) {
				result[idx] = number[i];
				if(idx-1>=0 && result[idx-1]<=result[idx]) return; // 가지치기
				selected[i] = true;
				Permutation(idx+1);
				selected[i] = false;
			}
			
		}
	}
	
	static int factorial(int n) {
		if(n==1) return 1;
		return n*factorial(n-1);
	}

}
