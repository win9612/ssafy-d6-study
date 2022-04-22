package Day0422;

import java.util.Scanner;

public class Baekjoon16198 {

	static int N;
	static int[] input;
	static int answer;
	
	
	public static void main(String[] args) {
		// TODO 에너지 모으기
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		answer = Integer.MIN_VALUE;
		int temp = 0;
		
		// 입력받기
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		
		energy_goosuel(input, 0);
		
		System.out.println(answer);
		
	}
	
	// 재귀함수
	public static void energy_goosuel(int[] arr, int energy){
		// 기저조건
		if(arr.length==2) { // 두개만 남으면 끝
			
			if(energy > answer) {
				answer = energy; // 최대 값 갱신
			}
			return;
		}
		
		
		for(int i=1; i<arr.length-1; i++) { // 두번째부터 뒤에서 두번째까지
			int temp = energy + (arr[i-1] * arr[i+1]); // 현재에서 전과 후를 곱함

			
			int[] temp_arr = new int[arr.length-1]; // 크기 1 작은 배열 하나 생성
			int idx=0;
			for(int j=0; j<arr.length; j++) { // 현재 원소를 제외한 배열을 다시 생성한다.
				if(j!=i) {
					temp_arr[idx] = arr[j];
					idx++;
				}
			}
			
			energy_goosuel(temp_arr, temp); // 재귀함수 실행.
		}
		
		return;
		
	}

}
