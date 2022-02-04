import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int M; // 가로
	static int N; // 세로
	static int[][] graph;
	static Queue<int[]> q = new LinkedList<>();;
	
	static void bfs() {
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			for(int l=0; l<4; l++) {
				int yy = y + dy[l];
				int xx = x + dx[l];
				if(yy >= 0 && yy < N && xx >= 0 && xx <M) {
					if(graph[yy][xx] == 0) {
						graph[yy][xx] = graph[y][x] + 1;
						q.offer(new int[] {yy, xx});
					}
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}//for문 종료
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}//for문 종료
		
		bfs(); //탐색
		
		int max = 0;
		boolean flag = false; // 0 찾기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j] == 0) {
					flag = true; // 0이 하나라도 있다면
				}else {
					max = Math.max(max, graph[i][j]-1);
				}
			}
		}
		if(flag) {
			System.out.println(-1);
		}else {
			if(max > 0) {
				System.out.println(max);
			}else {
				System.out.println(0);
			}
		}
		
	}

}
                                




