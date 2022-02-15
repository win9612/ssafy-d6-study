package day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074_Z {
	static int N, r, c, cnt;
	static int[] dy = {0, 0, 1, 1};
	static int[] dx = {0, 1, 0, 1};
	
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
			for(int i=0; i<4; i++) {
				int yy = y + dy[i];
				int xx = x + dx[i];
				if(yy == r && xx == c) {
					System.out.println(cnt);
					System.exit(0);
				}
				cnt++;
			}
			return;
		}
		
		dfs(n/2, y, x);
		dfs(n/2, y, x + n/2);
		dfs(n/2, y + n/2, x);
		dfs(n/2, y + n/2, x + n/2);
	}

}
