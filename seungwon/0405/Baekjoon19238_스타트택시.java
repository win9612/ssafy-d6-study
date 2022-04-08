package Day0405;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon19238_스타트택시 {
	static int N, M, fuel;
	static int[][] map;
	static int start_r, start_c;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) {
		// TODO 스타트 택시
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		fuel = sc.nextInt();
		
		map = new int[N+1][N+1];
		
		// 입력 받기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 택시 좌표 입력 받기
		start_r = sc.nextInt();
		start_c = sc.nextInt();
		
		// 승객 출발지 도착지 좌표 입력 받기
		int id = 2; // 승객은 2부터 id 까지 도착지는 음수
		for(int i=0; i<M; i++) {
			int start_r = sc.nextInt()-1;
			int start_c = sc.nextInt()-1;
			int end_r = sc.nextInt()-1;
			int end_c = sc.nextInt()-1;
			map[start_r][start_c] = id;
			map[end_r][end_c] = -1*id;
			id++;
		}
		
		
		// 두 점 사이 거리 구하는 함수로 최소 거리 승객부터 태워가면서 푼다. 미완성.
		

	}
	
	// bfs로 두 점 사이의 거리를 구한다.
	static int bfs(int r, int c, int r_goal, int c_goal) {
		boolean[][] visited = new boolean[N][N];
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(r, c, 0)); // 시작점 queue에 넣기
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			int current_r = current.r;
			int current_c = current.c;
			
			if(current_r == r_goal && current_c == c_goal) {
				int depth = current.depth;
				return depth;
			}
			
			for(int i=0; i<4; i++) {
				int next_r = current_r+dr[i];
				int next_c = current_c+dc[i];
				
				if(next_r>=0 && next_r<N && next_c>=0 && next_c<N) {
					if(!visited[next_r][next_c] && map[next_r][next_c]!=1) {
						queue.add(new Point(next_r, next_c, current.depth+1));
						visited[next_r][next_c] = true;
					}
				}				
			}
			
		}
		return 0;
	}
	
	static class Point{
		int r;
		int c;
		int depth;
		public Point(int r, int c, int depth) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
		
	}

}
