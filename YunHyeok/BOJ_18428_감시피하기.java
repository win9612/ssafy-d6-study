import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static char[][] map;
	static boolean result = false;
	static List<Point> list = new ArrayList<>();
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static class Point{
		int y,x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'T') list.add(new Point(i, j));
			}
		}
			
		comb(0);
		System.out.println(result? "YES" : "NO");
	}
	
	static void comb(int cnt) {
		if(cnt >= 3) {
			if(check()) {
				result = true;
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'X') {
					map[i][j] = 'O';
					comb(cnt+1);
					map[i][j] = 'X';
				}
			}
		}
		
	}
	
	static boolean check() {
		for(int i=0; i<list.size(); i++) {
			int dir = 0;
			Point p = list.get(i);
			int ni = p.y;
			int nj = p.x;

			while(true) {
				if(dir >= 4) {
					break;
				}
				
				int y = ni + dy[dir];
				int x = nj + dx[dir];
				
				if(y<0 || y>=N || x<0 || x>=N || map[y][x] == 'T' || map[y][x] == 'O') {
					ni = p.y;
					nj = p.x;
					dir++;
					continue;
				}
				
				if(map[y][x] == 'S') {
					return false;
				}
	
				if(map[y][x] == 'X') {
					ni+=dy[dir];
					nj+=dx[dir];
				}
			}
			
		}
		return true;
	}
	
}
