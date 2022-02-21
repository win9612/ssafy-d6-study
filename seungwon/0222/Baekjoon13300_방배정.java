package Day0222;

import java.util.Scanner;

public class Baekjoon13300_방배정 {

	public static void main(String[] args) {
		// TODO 방 배정
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 학생 수
		int K = sc.nextInt(); // 방 당 최대 인원 수
		
		int[][] students = new int[7][2]; // 0 인덱스는 비우고 1~6 학년을 인덱스로 사용하기 위해 크기 7 선언
		
		for(int i=0; i<N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			students[b][a] += 1;			
		}
		
		int count = 0;
		
		// row는 1~6학년 
		for(int i=1; i<7; i++) {
			for(int j=0; j<2; j++) {

				if(students[i][j]%K==0) { // K로 나누어 떨어지면 몫을 더해준다
					count+=students[i][j]/K;
				}else count+=((students[i][j]/K)+1); // 나누어 떨어지지 않으면 몫 +1을 더해준다
			}

		}
		
		System.out.println(count);
	}

}
