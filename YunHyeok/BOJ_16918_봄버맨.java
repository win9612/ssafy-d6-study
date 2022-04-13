import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16918_봄버맨 {
	static int R, C, N;
	static char[][] map;
	static Queue<Point> q = new LinkedList<>();
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; // 초기 폭탄 맵
		
		for(int r=0; r<R; r++) {
			String str = br.readLine();
			for(int c=0; c<C; c++) {
				char element = str.charAt(c);
				map[r][c] = element;
			}
		}
		

		if(N==1) { // 1초면 바로 출력
			printResult();
		}else { 
			search(); // 폭탄찾기
			for(int i=2; i<=N; i++) { // N초까지 폭탄찾기와 터뜨리기 반복
				if(i%2 == 0) {// 짝수인 경우
					zeroFull(); 
				}
				else {
					explode(); 
					search();
				}
			}
			printResult();
		}
		

	}
	
	// 폭탄 터뜨리기
	static void explode() {
		while(!q.isEmpty()) {
			Point point = q.poll();
			map[point.y][point.x] = '.';
			
			for(int d=0; d<4; d++) {
				int y = point.y + dy[d];
				int x = point.x + dx[d];
				if(y>=0 && y<R && x>=0 && x<C) {
					if(map[y][x] == 'O') map[y][x] = '.';
				}
			}
		}
	}
	
	// 폭탄 탐색 후 큐에 넣기
	static void search() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'O') q.offer(new Point(i, j));
			}
		}
	}
	
	// 폭탄으로 가득 채우기
	static void zeroFull() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = 'O';
			}
		}
	}
	
	//결과 출력 함수
	static void printResult() {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}
}
