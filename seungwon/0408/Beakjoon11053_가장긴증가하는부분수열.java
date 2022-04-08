package Day0408;

import java.util.Scanner;

public class Beakjoon11053_가장긴증가하는부분수열 {

	public static void main(String[] args) {
		// TODO 가장긴증가하는부분수열
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		
		int max = 0; // 해당 수열의 LIS 최장 길이를 저장할 변수
		int[] LIS = new int[N];
		
		
		for(int i=0; i<N; i++) { // 모든 원소에 대해 자신을 끝으로 하는 LIS 길이 계산
			LIS[i] = 1; // 자신 혼자 LIS 구성할 때의 길이 1로 초기화. 최장길이 처음 시작이면 1이 되도록.
			for(int j=0; j<i; j++) {
				if(input[j] < input[i] && LIS[i] < LIS[j]+1) { // arr[j] < arr[i]: 증가수열의 모습
					LIS[i] = LIS[j]+1;
				}
			}
			if(max<LIS[i]) max=LIS[i]; // 최장 길이 개신
		}
		
		System.out.println(max);

	}

}
