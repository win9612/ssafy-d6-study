import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2573_빙산 {
	static int[][] graph;
	static boolean[][] visited;
	static int N;
	static int M;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	
	static void bfs(int i, int j) {
		visited[i][j] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			for(int l=0; l<4; l++) {
				int yy = y + dy[l];
				int xx = x + dx[l];
				if(yy>=0 && yy<N && xx >=0 && xx<M) {
					if(graph[yy][xx] > 0 && !visited[yy][xx]){
						q.offer(new int[] {yy, xx});
						visited[yy][xx] = true;
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M]; // 빙산
		
		int yearCnt = 0; // 년
		
		// 빙산 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		boolean flag = true; //빙산이 다 녹을 때까지 분리되지 않은 경우를 위한 플래그
		while(flag) {
			int cnt = 0;
			visited = new boolean[N][M];
			flag = false; // 이중for문을 돌고 빙산이 다 녹았다면 false가 유지, 반복문을 돌다가 하나라도 빙산이 있다면 true
			for (int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(graph[i][j] > 0 && !visited[i][j]) {
						flag=true;
						bfs(i, j);
						cnt++;
					}
				}
			}
			
			// 빙산이 두덩어리 이상으로 나눠진 경우
			if(cnt >= 2) {
				System.out.println(yearCnt);
				break;
			}
			
			// 빙산 녹이기
			int[][] updateGraph = new int[N][M]; // 업데이트 될 빙산
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(graph[i][j] > 0) {
						int zeroCnt = 0; // 주변 빈공간
						for(int l=0; l<4; l++) {
							int yy = dy[l] + i;
							int xx = dx[l] + j;
							if(graph[yy][xx] == 0) zeroCnt+=1;
						}
						int update = graph[i][j] - zeroCnt; // 음수일 경우에는 '0'으로 맞춰주기 
						if(update <= 0) update = 0;
						updateGraph[i][j] = update;
					}
				}
			}
			yearCnt+=1; //녹이고 난 뒤에 1년 추가
			graph = updateGraph.clone(); 
		}//while 종료

		if(!flag) System.out.println(0);
		
		
	}
}
