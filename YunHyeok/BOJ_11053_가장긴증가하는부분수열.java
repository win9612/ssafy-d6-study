import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11053_가장긴증가하는부분수열 {
	static int N;
	static int[] A, D;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		A = new int[N];
		D = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		
		for(int i=0; i<N; i++) {
			D[i] = 1;
			for(int j=0; j<i; j++) {
				if(A[j] < A[i] && D[i] < D[j] + 1) {
					D[i] = D[j] + 1; 
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			max = Math.max(D[i], max);
		}
		
		System.out.println(max);
	}

}
