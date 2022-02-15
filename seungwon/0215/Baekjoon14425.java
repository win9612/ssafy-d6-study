package day0215_0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14425 {

	public static void main(String[] args) throws IOException {
		// TODO 문자열 집합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
				
		String[] input = new String[N];
		String[] input2 = new String[M];
		for(int i=0; i<N; i++) {
			input[i] = br.readLine();
		}
		for(int i=0; i<M; i++) {
			input2[i] = br.readLine();
		}
		
		int count = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(input[i].equals(input2[j])) {
					count++;
				}
			}
		}
		System.out.println(count);

	}

}
