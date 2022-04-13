package day0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
	static int L;
	static int C;
	static char[] array;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		array = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			array[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(array);
		dfs(0, 0, "");
	}
	static void dfs(int idx, int cnt, String str) {
		if(cnt == L) {
			int mo = 0;
			int ja = 0;
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i)== 'a' || str.charAt(i)== 'e' ||str.charAt(i)== 'i' ||str.charAt(i)== 'o' || str.charAt(i)== 'u')
					mo++;
				else
					ja++;
			}
			if(mo >= 1 && ja>=2) System.out.println(str);
			return;
		}
		
		if(idx >= C) 
			return;
		
		dfs(idx+1, cnt+1, str + array[idx]);
		dfs(idx+1, cnt, str);
	}

}
