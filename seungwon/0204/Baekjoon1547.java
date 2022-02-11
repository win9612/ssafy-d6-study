<<<<<<< HEAD
package seungwon;

import java.util.Scanner;

public class Baekjoon1547 {

	public static void main(String[] args) {
		// TODO 공
		
		
		
		Scanner sc = new Scanner(System.in);
		
		// 인덱스는 0부터 시작하기 때문에 크기가 4인 배열을 생성한다.
		int[] arr = {0, 1, 0, 0};
		int temp = 0;
		// m 입력 받기
		int m = sc.nextInt();
		
		for(int i=0;i<m;i++) {
			// x 입력 받기
			int x = sc.nextInt();
			// y 입력 받기
			int y = sc.nextInt();
			
			// 일시적으로 변수에 값 저장 -> 배열의 값 교환
			temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
			
		}
		// Scanner 닫아줘야함
		sc.close();
		
		// 반복문으로 1의 값을 가지고 있는 인덱스를 출력
		for(int i=1;i<arr.length;i++) {
			if(arr[i]==1) {
				System.out.println(i);
				break;
			}
		}

	}

}
=======
package seungwon;

import java.util.Scanner;

public class Baekjoon1547 {

	public static void main(String[] args) {
		// TODO 공
		
		
		
		Scanner sc = new Scanner(System.in);
		
		// 인덱스는 0부터 시작하기 때문에 크기가 4인 배열을 생성한다.
		int[] arr = {0, 1, 0, 0};
		int temp = 0;
		// m 입력 받기
		int m = sc.nextInt();
		
		for(int i=0;i<m;i++) {
			// x 입력 받기
			int x = sc.nextInt();
			// y 입력 받기
			int y = sc.nextInt();
			
			// 일시적으로 변수에 값 저장 -> 배열의 값 교환
			temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
			
		}
		// Scanner 닫아줘야함
		sc.close();
		
		// 반복문으로 1의 값을 가지고 있는 인덱스를 출력
		for(int i=1;i<arr.length;i++) {
			if(arr[i]==1) {
				System.out.println(i);
				break;
			}
		}

	}

}
>>>>>>> 4019eb485fe88814e8a99cb3cd6e0f1646046d59
