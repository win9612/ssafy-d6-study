import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_20056_마법사상어와파이어볼 {
	static int N, M, K, mTotalCnt;
	static int[][] map;
	static Queue<Point> q = new LinkedList<>();
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parse(st.nextToken());
		M = parse(st.nextToken());
		K = parse(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = parse(st.nextToken())-1;
			int x = parse(st.nextToken())-1;
			int m = parse(st.nextToken()); // 질량
			int s = parse(st.nextToken()); // 속도
			int dir = parse(st.nextToken()); // 방향

			map[y][x]++;
			q.offer(new Point(y, x, m, s, dir));
		}
		
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("=============");
	
		while(K-- > 0) {
			move();
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("=============");
			
			divide();
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("=============");
		}
		
		System.out.println(mTotalCnt);
		
	}

	private static void divide() {
		mTotalCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] ==1) {
					for(Point point : q) {
						if(point.y == i && point.x == j) {
							mTotalCnt += point.m;
							break;
						}
					}
				}
				else if(map[i][j] >= 2) {
					mTotalCnt += fireDivide(i, j);
				}
			}
		}
	}

	private static int fireDivide(int i, int j) {
		int aSize = map[i][j];
		int size = map[i][j];
		map[i][j] = 0;
		
		int mSum = 0;
		int sSum = 0;
		int even = 0;
		int odd = 0;
		
		int qs = q.size();
		while(qs-- > 0) {
			Point point = q.poll();
			if(point.y == i && point.x == j) {
				mSum += point.m;
				sSum += point.s;
				if(point.dir % 2 == 0) { // 짝수면
					even++;
				}else { // 홀수면
					odd++;
				}
			}else { // 넘어가기
				mTotalCnt += point.m;
				q.offer(point);
			}
		} 
		
		mSum /= 5;
		sSum /= aSize;
		if(mSum != 0) {		
			map[i][j] = 4;	
			if(even == aSize || odd == aSize) {
				for(int f=0; f<4; f++) {
					q.offer(new Point(i, j, mSum, sSum, (f*2)));
				}
			}else {
				for(int f=0; f<4; f++) {
					q.offer(new Point(i, j, mSum, sSum, (f*2)+1));
				}
			}
		}
		
		return mSum * 4;
		
	}

	private static void move() {
		int size = q.size();
		for(int s=0; s<size; s++) {
			Point point = q.poll();
			map[point.y][point.x]--;
			
			int y = (point.y + dy[point.dir]*point.s + N*1000)%N;
			int x = (point.x + dx[point.dir]*point.s + N*1000)%N;

			q.offer(new Point(y, x, point.m, point.s, point.dir));
			map[y][x]++;
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
