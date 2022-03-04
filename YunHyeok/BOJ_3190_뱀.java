import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {
	static int N, K, dir;
	static int[][] map;
	static int dy[] = {0, 1, 0, -1};
	static int dx[] = {1, 0, -1, 0};
	static Queue<DPoint> dp = new LinkedList<>();
	static Deque<Point> q = new ArrayDeque<>();
	int i, j;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; // 입력부터 귀찮아 갈 수 있는 곳은 0으로!
		
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y-1][x-1] = 1; // 사과는 1로 두기
		}
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char move = st.nextToken().charAt(0);
			dp.add(new DPoint(sec, move));
		}
		
		
		
		q.offer(new Point(0, 0)); //시작지점 넣어주기
		int i = 0, j = 0, sec = 0;
		
		while(true) {
			sec++;
			int y = i + dy[dir];
			int x = j + dx[dir];
			
			if(check(y, x)) // 벽도 안부딪히고 몸통이랑도 안부딪히면
				break;
			
			if(map[y][x] == 1) { // 앞에 사과라면
				map[y][x] = 0; // 와 설마 이거 안해줘서..? 맞네;
				q.offerLast(new Point(y, x));
			}else { // 사과가 아니라면
				q.offerLast(new Point(y, x)); // 머리 붙이기
				q.pollFirst(); // 꼬리자르기
			}
			
			i = y;
			j = x; 
			
			if(!dp.isEmpty() && sec == dp.peek().sec) {
//				System.out.println("####" + dp.peek().sec);
				DPoint dpoint = dp.poll();
				if(dpoint.move == 'D') { // 오른쪽으로 가야한다면
					dir++;
					if(dir == 4) dir=0;
				}else { // 왼쪽으로 가야한다면
					dir--;
					if(dir == -1) dir = 3;
				}
			}
			
		}
		
		System.out.println(sec);
		
		
	}
	
	static boolean check(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N) {// 벽도 안부딪히고
			return true;
		}
		for(Point point : q) {
			if(y == point.y && x == point.x)
				return true;
		}
		return false;
	}
	
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static class DPoint{
		int sec;
		char move;
		DPoint(int sec, char move){
			this.sec = sec;
			this.move = move;
		}
	}
	
	

	
}
