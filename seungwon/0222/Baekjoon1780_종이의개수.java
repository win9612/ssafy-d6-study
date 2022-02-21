package Day0222;

import java.util.Scanner;

public class Baekjoon1780_종이의개수 {
	static int N;
	static int[][] input;
	static int count_a;
	static int count_b;
	static int count_c;
	
	public static void main(String[] args) {
		// TODO 종이의 개수
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				input[i][j] = sc.nextInt();
			}
		}
		count_a=0;
		count_b=0;
		count_c=0;
		func(0,0,N);
		System.out.println(count_a);
		System.out.println(count_b);
		System.out.println(count_c);

	}
	
	static void func(int row_start, int col_start, int size) { // 시작좌표로 풀어라
		// 기저부분
		int row_end = row_start+size;
		int col_end = col_start+size;
		
		int a_count = 0;
		int b_count = 0;
		int c_count = 0;
		
		// 유망성 검사
		for(int i=row_start; i<row_end; i++) {
			for(int j=col_start; j<col_end; j++) {
				if(input[i][j]==0) b_count++;
				else if(input[i][j]==-1) a_count++;
				else if(input[i][j]==1) c_count++;
			}
		}

		if(c_count==size*size) {
			count_c++;
			return;
		}else if(b_count==size*size) {
			count_b++;
			return;
		}else if(a_count==size*size) {
			count_a++;
			return;
		}
		
		// 유도부분
		func(row_start, col_start, size/3);
		func(row_start, col_start+size/3, size/3);
		func(row_start, col_start+2*size/3, size/3);
		func(row_start+size/3, col_start, size/3);
		func(row_start+size/3, col_start+size/3, size/3);
		func(row_start+size/3, col_start+2*size/3, size/3);
		func(row_start+2*size/3, col_start, size/3);
		func(row_start+2*size/3, col_start+size/3, size/3);
		func(row_start+2*size/3, col_start+2*size/3, size/3);
		
		
	}

}
