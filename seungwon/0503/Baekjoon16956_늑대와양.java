package Day0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon16956_늑대와양 {

	static int R, C;
	static char[][] input;
	static int[] dr= {1,0,-1,0};
	static int[] dc= {0,1,0,-1};
	static int answer = 1;
	
	public static void main(String[] args) throws IOException {
		// TODO 늑대와 양
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new char[R][C];
		
		// 입력
		for(int r=0; r<R; r++) {
			String str = br.readLine();
			for(int c=0; c<C; c++) {
				input[r] = str.toCharArray();
			}
		}
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(input[r][c]=='S') {
					search(r, c);
				}

			}
		}
		
		if(answer==1) {
			System.out.println(answer);
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					System.out.print(input[r][c]);
				}
				System.out.println();
			}
		}else {
			System.out.println(answer);
		}
		
	}
	
	static void search(int r, int c) {
		for(int i=0; i<4; i++) {
			int next_r = r + dr[i];
			int next_c = c + dc[i];
			if(0<=next_r && next_r<R && 0<=next_c && next_c<C) {
				if(input[next_r][next_c]=='W') {
					answer = 0;
				}else if(input[next_r][next_c]=='.'){
					input[next_r][next_c] = 'D';
				}
			}
		}
	}

}







