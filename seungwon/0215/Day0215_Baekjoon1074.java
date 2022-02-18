package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Day0215_Baekjoon1074 {


	
	static int[][] arr;
	static int count = 0;
	static int N, r, c;
	static void func(int row_end, int column_end, int array_size ) {
		// row 가 시작하는 인덱스, columns  이 시작하는 인덱스, 
		// 기저부분
	
		int row_start = row_end - array_size;
		int column_start = column_end - array_size;
		
		if(array_size == 2) {
			// 기능
			// 1사분면 -> 2사분면 -> 3사분면 -> 4사분면 순으로 count 값 입력
			
			
			arr[row_start][column_start] = count++;

			arr[row_start][column_start+1] = count++;

			arr[row_start+1][column_start] = count++;

			arr[row_start+1][column_start+1] = count++;

			return;
		}
		
		// 유도부분. 1사분면 -> 2사분면 -> 3사분면 -> 4사분면 순으로 재귀함수 실행
		func(row_end-array_size/2, column_end-array_size/2 , array_size/2 );
		func(row_end-array_size/2, column_end , array_size/2 );
		func(row_end, column_end-array_size/2 , array_size/2 );
		func(row_end, column_end , array_size/2 );
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Z
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		

		int range = 1;
		
		// 반복문으로 거듭 제곱 구한다.
		for(int i=0; i<N; i++) {
			range *= 2;
		}
		
		arr = new int[range][range]; 

		int count = 0; // 탐색 순서대로 0부터 1씩 올라가며 배열에 값을 넣을 것임.
		
		
		func(range,range,range);
		
		/* 배열 전체 출력
		for(int i=0; i<range; i++) {
			for(int j=0; j<range; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		} */
		
		System.out.println(arr[r][c]);

	}

}
