package day0208_0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1182 {
	
	static int n, s;
	static int[] input;
	static int[] subset;
	static boolean[] selected;

	static int count=0;
	
	public static void main(String[] args) throws IOException {
		// TODO 부분수열의 합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		input = new int[n];
		selected = new boolean[n];
		
		String str2 = br.readLine();
		StringTokenizer st2 = new StringTokenizer(str2, " ");
		for(int i=0; i<n; i++) {
			input[i] = Integer.parseInt(st2.nextToken());
		}
		
		
		subsetsum(0);
		System.out.println(count);

	}
	
	static void subsetsum(int idx) {
		// 기저부분
		if(idx == n) {
			int sum = 0;
			for(int i=0; i<n; i++) {
				if(selected[i]) {
					sum += input[i]; // 선택된거 더해준다.
				}
			}
			if(sum == s) count++;
			return;
		}
		
		// 유도부분
		selected[idx] = true;
		subsetsum(idx+1);
		
		selected[idx] = false;
		subsetsum(idx+1);
		
	}

}
