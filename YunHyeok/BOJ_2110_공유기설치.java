package day0217;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2110_공유기설치 {
	static int N, C, result;
	static int[] house;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 집
		C = sc.nextInt(); // 공유기 수
		
		house = new int[N];
		for(int i=0; i<N; i++) {
			house[i] = sc.nextInt(); 
		}
		Arrays.sort(house); // 이분 탐색하려면 무조건~
		
		// '거리' 가 기준!!!
		int start = 1; //최소로 이동할 수 있는 거리는 1이겠지
		int end = house[N-1] - house[0]; //최대로 이동할 수 있는 거리는 8!

		while(start <= end) { 
			int houseCnt =0;
			int mid = (start + end) /2; // 그럼 중간 값부터 탐색을 시작 
			int installed = house[0]; // 무조건 처음 집부터 해야함!
			houseCnt++; 
			
			for(int i=1; i<house.length; i++) {
				if(mid + installed <= house[i]) {//중간값 + 공유기설치한집의 좌표 보다 다음 집의 좌표가 크다면
					installed = house[i]; 
					houseCnt++;
				}
			}
			
			if(houseCnt < C) { // 공유기를 다 설치 못했다. end 갱신해서 다시
				end = mid-1; 
			}else { // 공유기 설치는 다했다. 그래도 중간값 올려서 start가 end보다 커질때까지 탐색해보기
				start = mid+1; 
				result = mid; // 일단, 답을 담아두자
			}
			
		}
		
		System.out.println(result);
		
	}

}
