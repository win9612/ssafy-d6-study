import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//문제는 도착지점에 손님이 있을 경우 였습니다. 저는 도착지점에는 손님이 없다고 생각하고 작성했었습니다.
public class BOJ_19238_스타트택시 {
	static int N, M, fuel, goal;
	static int[][] map;
	static int[][] goalMap; // 목적지만 담은 맵
	static boolean flag = true;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, -1, 0, 1 };

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static Queue<Point> q = new LinkedList<>();
	static Queue<Point> goalQ = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 맵의 크기
		M = Integer.parseInt(st.nextToken()); // 승객의 수
		fuel = Integer.parseInt(st.nextToken()); // 연료의 양

		map = new int[N][N];
		goalMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 입력 받기

		st = new StringTokenizer(br.readLine()); // 택시위치 입력받기
		int ty = Integer.parseInt(st.nextToken()) - 1;
		int tx = Integer.parseInt(st.nextToken()) - 1;
		q.offer(new Point(ty, tx));

		for (int i = 2; i < M + 2; i++) {
			st = new StringTokenizer(br.readLine());
			int py = Integer.parseInt(st.nextToken()) - 1;
			int px = Integer.parseInt(st.nextToken()) - 1; // 사람
			int gy = Integer.parseInt(st.nextToken()) - 1;
			int gx = Integer.parseInt(st.nextToken()) - 1; // 목적지

			map[py][px] = i;
			goalMap[gy][gx] = i;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		} // 맵 출력
		System.out.println("=================================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(goalMap[i][j] + " ");
			}
			System.out.println();
		} // 골맵 출력

		while (flag) {
			if(M==0) {
				System.out.println(fuel);
				break;
			}
			
			
			
			personBfs(); // 태울 사람 찾기
			q = new LinkedList<>(); //택시 담는 큐 초기화
			
			
			
			
			System.out.println("택시 태운 후#########################################");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			} // 맵 출력
			System.out.println("태운 후 연료의 양은 ????" + fuel);
			
			
			
			
			goalBfs(); // 태운 사람 목적지 데려다 주기
			goalQ = new LinkedList<>(); // 사람담는 큐 초기화
			
			
			
			
			System.out.println("목적지 도달 후@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			} // 맵 출력
			System.out.println("도달 후 연료의 양은 ????" + fuel);
			System.out.println("목적지 도달 목적지 맵 $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(goalMap[i][j] + " ");
				}
				System.out.println();
			} // 맵 출력
			
			
		}
		if(!flag)
			System.out.println(-1);
	}

	static void personBfs() {// 태울 사람 찾기
		boolean[][] visited = new boolean[N][N];
		int distCnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point point = q.poll();
				visited[point.y][point.x] = true;
				
				if(map[point.y][point.x] >= 2) { // 사람찾으면
					System.out.println("***" + distCnt);
					goalQ.offer(new Point(point.y, point.x)); 
					goal = map[point.y][point.x]; // 목적지 저장
					map[point.y][point.x] = 0;//빈칸만들기
					fuel -= distCnt;
					if(fuel < 0) {
						flag = false;
					}
					
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int y = point.y + dy[d];
					int x = point.x + dx[d];
					if (y >= 0 && y < N && x >= 0 && x < N) {
						if(!visited[y][x] && map[y][x] != 1) {
							q.offer(new Point(y, x));
						}
					}
				}// for d
			}// for size
			distCnt++;
		}//while
		flag = false;
	}

	static void goalBfs() {// 태운 사람 목적지 데려다 주기
		boolean[][] visited = new boolean[N][N];
		int distCnt = 0;
		
		while(!goalQ.isEmpty()) {
			int size = goalQ.size();
			for(int s=0; s<size; s++) {
				Point point = goalQ.poll();
				visited[point.y][point.x] = true;
				
				if(goal == goalMap[point.y][point.x]) { // 사람찾으면
					System.out.println("***" + distCnt);
					q.offer(new Point(point.y, point.x)); 
					goalMap[point.y][point.x] = 0;
					goal = 0; // 목적지 초기화
					fuel -= distCnt;
					
					if(fuel < 0) { // 연료가 떨어진경우
						flag = false;
					}
					
					fuel += (distCnt*2); // 연료두배충전
					M-=1; // 사람 한명씩 목적지에 데려다주면 -1
					return;
				}
				
				for(int d=0; d<4; d++) {
					int y = point.y + dy[d];
					int x = point.x + dx[d];
					if(y >=0 && y<N && x>=0 && x<N) {
						if(!visited[y][x] && map[y][x] != 1) {
							goalQ.offer(new Point(y, x));
						}
					}
				} //for d
			} // for size
			distCnt++;
		}//while
		flag = false;
	}
}

//6 3 1
//0 0 0 0 1 0
//0 0 0 0 1 1
//0 0 0 0 0 0
//0 0 0 0 0 0
//0 0 0 0 0 0
//0 0 0 0 0 0
//1 1
//1 1 1 2
//1 2 1 3
//1 3 1 2
