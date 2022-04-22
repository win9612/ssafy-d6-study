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
			map[y][x][5] = dir;
			
			if(dir%2==0) map[y][x][3]++; // 짝수
			else map[y][x][4]++; // 홀수
			
			
			q.offer(new Point(y, x, m, s, dir));
		}
		
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j][0] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("=============");
	
		while(K-- > 0) {
			
			move();
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j][0] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("move after=============");
			divide();
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j][0] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("divide after=============");
		}
		
		resultPrint();
		
	}

	private static void resultPrint() {
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j][1]==0) continue;
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
		int mSum = map[i][j][1]; // 질량의 합
		int speadSum = map[i][j][2]; //속도의 합
		int even = map[i][j][3]; // 짝수
		int odd = map[i][j][4]; // 홀수
//		System.out.println(size + " : " + mSum + " : " + speadSum + " : " + even + " : " + odd );
		mSum /= 5;
		speadSum /= size;
		
		if(mSum != 0) {		
			map[i][j][0] = 4;	
			map[i][j][1] = mSum * 4;
			map[i][j][2] = speadSum;
			
			if(even == size || odd == size) {
				for(int f=0; f<4; f++) {
					q.offer(new Point(i, j, mSum, speadSum, (f*2)));
					map[i][j][5] = (f*2);
				}
				map[i][j][3] = 4;
				map[i][j][4] = 0;
			}else {
				for(int f=0; f<4; f++) {
					q.offer(new Point(i, j, mSum, speadSum, (f*2)+1));
					map[i][j][5] = (f*2)+1;
				}
				map[i][j][3] = 0;
				map[i][j][4] = 4;
			}
			
//			System.out.println(map[i][j][0] + " : " + map[i][j][1] + " : " + map[i][j][2] + " : " + map[i][j][3] + " : " + map[i][j][4] );
		}else {
			map[i][j][0] = 0;
			map[i][j][1] = 0; // 만약에 질량의 합/5가 0이 된다면
			map[i][j][2] = 0;
			map[i][j][3] = 0;
			map[i][j][4] = 0;
			map[i][j][5] = 0;
		}
	}

	private static void move() {
		int size = q.size();
		for(int s=0; s<size; s++) {
			Point point = q.poll();
//			System.out.println(point.y + " : " + point.x + " : " + point.dir);
			map[point.y][point.x][0]--;
			map[point.y][point.x][1] -= point.m;
			map[point.y][point.x][2] -= point.s;
			
			
			if(point.dir%2==0) map[point.y][point.x][3]--; // 짝수
			else map[point.y][point.x][4]--; // 홀수
			
			
			int y = (point.y + dy[point.dir]*point.s + N*1000)%N;
			int x = (point.x + dx[point.dir]*point.s + N*1000)%N;
			
			
			map[y][x][0]++;
			map[y][x][1] += point.m;
			map[y][x][2] += point.s;
			map[y][x][5] = point.dir;
			if(point.dir%2==0) map[y][x][3]++; // 짝수
			else map[y][x][4]++; // 홀수
			
//			if(map[y][x][0] == 1) {
//				q.offer(new Point(y, x, point.m, point.s, point.dir));
//			}
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
