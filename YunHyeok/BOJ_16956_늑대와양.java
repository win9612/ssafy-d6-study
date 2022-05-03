import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_16956_늑대와양 {
	static int R, C;
	static boolean flag;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Queue<Point> q = new LinkedList<>();
	static char[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = parse(st.nextToken());
		C = parse(st.nextToken());
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'W') q.offer(new Point(i,j)); // 늑대 담기
			}
		}
		
		bfs();
		if(!flag) {
			System.out.println(1);
			print();
		}
	}
	
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = p.i + dy[d];
				int nj = p.j + dx[d];
				if(check(ni, nj)) {
					if(map[ni][nj] == '.') { // 늑대 주위에 울타리 짓기
						map[ni][nj] = 'D';
					}else if(map[ni][nj] == 'S') { // 양과 만나면 0 출력
						System.out.println(0);
						flag = true;
						return;
					}
				}
			}
		}
	}

	private static boolean check(int ni, int nj) { // 범위검사
		if(ni>=0 && nj>=0 && ni<R && nj<C) return true;
		return false;
	}

	private static void print() { // 결과 출력
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static int parse(String str) {
		return Integer.parseInt(str);
	}
	
	private static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
