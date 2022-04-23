import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_20056_마법사상어와파이어볼2 {
	static int N, M, K;
	static int[][][] map;
	static Queue<Point> q = new LinkedList<>();
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parse(st.nextToken());
		M = parse(st.nextToken());
		K = parse(st.nextToken());
		
		map = new int[N][N][6];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = parse(st.nextToken())-1;
			int x = parse(st.nextToken())-1;
			int m = parse(st.nextToken()); // 질량
			int s = parse(st.nextToken()); // 속도
			int dir = parse(st.nextToken()); // 방향

			map[y][x][0]++;
			map[y][x][1] += m;
			map[y][x][2] += s;
			map[y][x][5] += dir;
			
			if(dir%2==0) map[y][x][3]++; // 짝수
			else map[y][x][4]++; // 홀수
			
			q.offer(new Point(y, x, m, s, dir));
		}
		
		while(K-- > 0) {
			move();
			divide();
		}
		resultPrint();
	}

	private static void resultPrint() {
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ans += map[i][j][1];
			}
		}
		System.out.println(ans);
	}

	private static void divide() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j][0] >= 2) {
					fireDivide(i, j);
				}else if(map[i][j][0] == 1) {
					q.offer(new Point(i, j, map[i][j][1], map[i][j][2], map[i][j][5]));
				}
			}
		}
	}

	private static void fireDivide(int i, int j) {
		int size = map[i][j][0]; // 개수
		int mSum = map[i][j][1]/5; // 질량의 합
		int speadSum = map[i][j][2]/size; //속도의 합
		int even = map[i][j][3]; // 짝수
		int odd = map[i][j][4]; // 홀수
		
		if(mSum != 0) {		
			map[i][j][0] = 4;	
			map[i][j][1] = mSum * 4;
			map[i][j][2] = speadSum*4;
			map[i][j][5] = 0;
			
			for (int f = 0; f < 4; f++) {
                q.offer(new Point(i, j, mSum, speadSum, (f * 2 + ((even == size || odd == size) ? 0 : 1) )));
            }
            map[i][j][3] = (even == size || odd == size) ? 4 : 0;
            map[i][j][4] = 4 - map[i][j][3];
			
		}else {
			for(int k=0; k<=5; k++) { // 초기화
				map[i][j][k] = 0;
			}
		}
	}

	private static void move() {
		int size = q.size();
		while(!q.isEmpty()) {
			Point point = q.poll();
			map[point.y][point.x][0]--;
			map[point.y][point.x][1] -= point.m;
			map[point.y][point.x][2] -= point.s;
			map[point.y][point.x][5] -= point.dir; 
			
			if(point.dir%2==0) map[point.y][point.x][3]--; // 짝수
			else map[point.y][point.x][4]--; // 홀수
			
			int y = (point.y + dy[point.dir]*point.s + N*1000)%N;
			int x = (point.x + dx[point.dir]*point.s + N*1000)%N;
			
			map[y][x][0]++;
			map[y][x][1] += point.m;
			map[y][x][2] += point.s;
			map[y][x][5] += point.dir;
			
			if(point.dir%2==0) map[y][x][3]++; // 짝수
			else map[y][x][4]++; // 홀수
			
		}
	}

	static class Point{
		int y, x, m, s, dir;
		
		Point(int y, int x, int m, int s, int dir){
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.dir = dir;
		}
	}
	
	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
