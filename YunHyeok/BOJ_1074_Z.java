package day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074_Z {
	static int N, r, c, cnt;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		dfs((int) Math.pow(2, N), 0, 0);
		
	}
	static void dfs(int n, int y, int x) {
		if(n==2) {
			if(y==r && x== c) {
				System.out.println(cnt);
				return;
			}
			cnt+=1;
			
			if(y==r && x+1==c) {
				System.out.println(cnt);
				return;
			}
			cnt+=1;
			
			if(y+1==r && x==c) {
				System.out.println(cnt);
				return;
			}
			cnt+=1;
			
			if(y+1==r && x+1==c) {
				System.out.println(cnt);
				return;
			}
			cnt+=1;
			return;
		}
		
		dfs(n/2, y, x);
		dfs(n/2, y, x + n/2);
		dfs(n/2, y + n/2, x);
		dfs(n/2, y + n/2, x + n/2);
	}

}
