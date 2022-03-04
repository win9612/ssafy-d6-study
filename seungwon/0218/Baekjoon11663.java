package day0215_0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon11663 {  

	static int N, M;
	static int[] jum;
	static int[][] sun;
	
	static int counting_jum_bamhanuelPearl(int[] arr, int start, int end) { // 이분 탐색
		int first =0; // 점 배열의 첫 인덱스
		int last = jum.length-1;  // 점 배열의 마지막 인덱스
		
		// 선분의 끝좌표를 대상으로 이분 탐색하여 해당 값의 인덱스를 구하기
		while(first <= last) { // 교차될 때까지.
			int mid = (first+last)/2; // 점 중에 가운데 인덱스
			
			if(end <jum[mid]) { // 점 배열 중에 가운데 값이 선분의 끝(key)보다 크다면 -> 가운데 값이 선분위에 없음
				last = mid-1; // 가운데 값 아래 점들 중에 찾아라
			}else first=mid+1; // jum[mid] <= end, 가운데 값 위 점들 중에 찾아라
		}
		
		// 이분 탐색은 원하는 키값을 가진 배열의 값의 인덱스를 구하는 것.
		//int endIndex = last +1; // 선분의 끝좌표보다 작거나 같은 점들의 개수
		int endIndex = first; // first 는 end 값 바로 뒤에 있는 인덱스
		
		
		
		// 이번엔 선분의 시작좌표를 대상으로 한번 더 수행.
		first = 0;
		last = jum.length-1;
		
		// 선분의 시작좌표를 대상으로 이분 탐색하여 해당 값의 인덱스를 구하기
		while(first<=last) {
			int mid = (first+last)/2;
			if(jum[mid] < start) {
				first = mid+1;
			}else last = mid-1;
		}
		int startIndex = first; // first는 start 값 바로 위에 있는 값 인덱스, 선분의 시작 좌표 바로 뒤 값 인덱스
		
		return endIndex-startIndex; // 끝점 뒤 인덱스 - 시작 점 뒤 인덱스 = 그 사이 존재하는 점의 갯수
	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO 선분 위의 점  
		// 반복문으로 풀면 시간초과 -> 이분 탐색으로 풀어라
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		jum = new int[N];
		
		// 배열 jum 에 점 좌표 입력 받기
		String str2 = br.readLine();
		StringTokenizer st2 = new StringTokenizer(str2," ");
		for(int i=0; i<N; i++) {
			jum[i] = Integer.parseInt(st2.nextToken());
		}
		
		// 점 오름차순 정렬
		Arrays.sort(jum);
		
		sun = new int[M][2];
		
		// 2차원 배열 sun에 시작 끝 좌표 입력 받기
		for(int i=0; i<M; i++) {
			String str3 = br.readLine();
			StringTokenizer st3 = new StringTokenizer(str3," ");
			sun[i][0] = Integer.parseInt(st3.nextToken());
			sun[i][1] = Integer.parseInt(st3.nextToken());
		}
		
		// 선들을 반복문으로 하나씩 입력한다.
		for(int i=0; i<M; i++) { 
			System.out.println(counting_jum_bamhanuelPearl(jum, sun[i][0], sun[i][1]));
		}
		
	
		
	}

}
