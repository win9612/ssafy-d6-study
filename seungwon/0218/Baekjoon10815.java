package day0215_0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon10815 {
	
	//재귀로 이진탐색 구현해보기
	static int binarySearch(int[] arr, int start, int end, int key) { // key는 찾고자 하는 값.
		// 기저부분
		if(start > end) return -1; // 처음과 끝이 교차하면
		
		// 유도부분
		int mid = (start+end)/2; // 중간 인덱스를 찾는다
		if( arr[mid]==key ) { // 중간 값이 찾는 값이면 return
			return mid;
		}else if( arr[mid] < key) { // key 가 중간 값 보다 오른족에 있는 경우
			return binarySearch(arr, mid+1, end, key); // 범위 수정
		}else { // key 가 중간 값 보다 왼족에 있는 경우
			return binarySearch(arr, start, mid-1, key);
		}

		
	}
	
	/* 반복문으로 이진 탐색 구현
	static int binarySearch(int[] arr, int start, int end, int key) {
		int result = 0;
		while(start<=end) {
			int mid = (start+end) / 2;
			if (arr[mid] == key) {
				result = 1;
				break;
			}else if( key < arr[mid] ) {
				end = mid-1;
			}else if( arr[mid] < key) {
				start = mid+1;
			}
		}
		return result;
	}*/

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO 숫자 카드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 정수 카드
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int[] jungsu = new int[N];
		for(int i=0; i<N; i++) {
			jungsu[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정수 카드 오름차순 정렬하기
		Arrays.sort(jungsu);
		
		// 입력 데이터
		int M = Integer.parseInt(br.readLine());
		String str2 = br.readLine();
		StringTokenizer st2 = new StringTokenizer(str2, " ");
		int[] input = new int[M];
		for(int i=0; i<M; i++) {
			input[i] = Integer.parseInt(st2.nextToken());
		}
		
		// input 원소들을 하나씩 입력하면서 결과 출력
		for(int i=0; i<M; i++) {
			int a = binarySearch(jungsu, 0, N-1, input[i]);
			System.out.print( (a==-1?0:1)+" "); // 못찾아서 -1 이 return 되었으면 0
			// 찾아서 해당 위치 인덱스가 return 되었으면 1 이 출력되게 설정.
			
		}

	}

}
