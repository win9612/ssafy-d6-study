import java.util.Scanner;

public class Main {
	static int[] coin, D;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		
		for(int tc=0; tc<TC; tc++) {
			int N = sc.nextInt();
			coin = new int[N]; // 동전 담을 배열
			for(int i=0; i<N; i++) { // 동전 담기
				coin[i] = sc.nextInt();
			}
			
			int M = sc.nextInt();
			D = new int[M+1];
			D[0] = 1; // 누적을 위함
			
			for(int i=0; i<N; i++) { // 동전 하나씩 꺼내서
				for(int j=coin[i]; j<=M; j++) { //만들어야 할 금액 M까지
					D[j] += D[j-coin[i]];
				}
			}
			
			System.out.println(D[M]);
			
		}
	}

}
