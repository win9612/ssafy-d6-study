import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	static int N, M; 
	static int ans = 0;
	static int[][] map;
	static int[] dy = {-1, 0, 1, 0}; // 상 좌 하 우
	static int[] dx = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parse(st.nextToken());
		M = parse(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = parse(st.nextToken());
		int c = parse(st.nextToken());
		int dir = parse(st.nextToken());
		if(dir == 1) dir=3; // 우 -> 좌
		else if(dir==3) dir=1; // 좌 -> 우
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parse(st.nextToken());
			}
		}
		
		dfs(r, c, dir);
		System.out.println(ans);
	}

	private static void dfs(int i, int j, int d) {
		if(map[i][j] ==0) { // 현재자리 청소
			map[i][j] = -1;
			ans++; 
		}
			
		boolean flag = false; 
		for(int t=0; t<4; t++) {
			int nd = (d+1)%4;
			int ni = i +dy[nd];
			int nj = j +dx[nd];
			
			if(check(ni, nj) && map[ni][nj] == 0) {
				dfs(ni, nj, nd);
				flag = true; // 빈공간이 하나라도 있다면
				break;
			}
			d = (d+1)%4;
		}
		
		if(!flag) { // 네 방향 모두 빈공간이 없을때, 벽 or 청소한 구간
			int backD = (d+2)%4;
			int bi = i + dy[backD];
			int bj = j + dx[backD];

			if(map[bi][bj] == 1) {// 만약, 뒤에 벽이라면 종료
				return; 
			}else if(map[bi][bj] <= 0) { // 벽이 아니라면 후진
				dfs(bi, bj, d);
			}

		}
	}

	private static boolean check(int ni, int nj) {
		if(ni>0 && nj>0 && ni<N && nj<M) return true;
		return false;
	}

	private static int parse(String str) {
		return Integer.parseInt(str);
	}

	
}
