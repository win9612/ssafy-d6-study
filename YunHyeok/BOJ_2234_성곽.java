import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_성곽 {
	static int N, M, oneCnt, max, rMax;
	static int[][] graph;
	static int[][] visited;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dir = { 1, 2, 4, 8 };
	static int color = 1;
	static Map<Integer, Integer> map = new HashMap<>();

	static class Point {
		int y, x, wall;

		Point(int y, int x, int wall) {
			this.y = y;
			this.x = x;
			this.wall = wall;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[M][N];
		visited = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 그래프 입력받기


		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0) {
					int twoCnt = bfs(i, j, graph[i][j]);
					map.put(color, twoCnt); //#3
					max = Math.max(max, twoCnt); //#2. 가장 넓은 방의 넓이
					oneCnt++; // #1. 이 성에 있는 방의 개수
					color++; // visited배열 1~5로 구간 나누기
				}
			}
		}

		removeSearch(); // #3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

		System.out.println(oneCnt);
		System.out.println(max);
		System.out.println(rMax);

	}

	static int bfs(int i, int j, int wallNum) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(i, j, wallNum));
		visited[i][j] = color;
		int twoCnt = 1;
		while (!q.isEmpty()) {
			Point point = q.poll();

			boolean[] move = new boolean[4];

			// 벽에대한 정보를 통해 이동할 수 있는 경우를 move배열에 넣어주기
			int wallN = 15 - point.wall;
			for (int num = 3; num >= 0; num--) {
				if (wallN >= dir[num]) {
					wallN -= dir[num];
					move[num] = true;
				}
			}

			for (int m = 0; m < move.length; m++) {
				if (move[m]) {
					int y = point.y + dy[m];
					int x = point.x + dx[m];
					if (visited[y][x] == 0) {
						q.offer(new Point(y, x, graph[y][x]));
						visited[y][x] = color;
						twoCnt++;
					}
				}

			}
		}
		return twoCnt;
	}

	static void removeSearch() {
		// 오른쪽, 아래만 확인
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(j+1 < N) {
					if (visited[i][j] != visited[i][j + 1]) {
						int removeCnt = map.get(visited[i][j]) + map.get(visited[i][j + 1]);
						rMax = Math.max(rMax, removeCnt);
					}
				}
				if(i+1< M) {
					if (visited[i][j] != visited[i + 1][j]) {
						int removeCnt = map.get(visited[i][j]) + map.get(visited[i + 1][j]);
						rMax = Math.max(rMax, removeCnt);
					}
				}
				
			}
		}
	}
}
