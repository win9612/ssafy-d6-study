package day0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815_숫자카드 {
	static int N, M;
	static int[] array;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(array);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int target = Integer.parseInt(st.nextToken());
			
			boolean result = binarysearch(array, target, 0, N-1);
			if(result) System.out.print("1 ");
			else System.out.print("0 ");
		}
		
	}
	static boolean binarysearch(int[] array, int target, int start, int end) {
		if(start>end)
			return false;
		
		int mid = (start+end)/2;
		
		if(array[mid] == target) return true;
		
		if(target>array[mid]) 
			return binarysearch(array, target, mid+1, end);
		else 
			return binarysearch(array, target, start, mid-1);
	
	}
}
