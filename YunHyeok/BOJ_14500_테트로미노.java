import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_14500_테트로미노 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int max = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // map 입력받기
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dfs(0, i, j, 0); // ㅗ 모양 빼고 탐색
				carShape(i, j); // ㅗ 모양 탐색
			}
		} 
		
		System.out.println(max);
		
		
	}
	static void dfs(int idx, int i, int j, int sum) {
		if(idx == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int d=0; d<4; d++) {
			int y = i + dy[d];
			int x = j + dx[d];
			if(y>=0 && x>=0 && y<N && x<M) {
				if(!visited[y][x]) { // 모든 경우를 다 탐색해야하는 순열이니까?
					visited[y][x] = true;
					dfs(idx+1, y, x, sum + map[y][x]);
					visited[y][x] = false;
				}
			}
		}
		
	}
	
	static void carShape(int i, int j) {
		int min = Integer.MAX_VALUE; // 추가한 칸 중에 제일 작은 값 저장 
		int cnt = 0; // 칸이 몇개 붙었는지
		int sum = map[i][j]; // 선택한 칸 포함하기
		
		for(int d=0; d<4; d++) {
			int y = i + dy[d];
			int x = j + dx[d];
			if(y>=0 && x>=0 && y<N && x<M) {
				cnt++;
				min = Math.min(min, map[y][x]);
				sum += map[y][x];
			}
		}
		if(cnt == 4) { // 만약 + 모양이라면 제일 작은 칸 하나를 빼준다.
			sum -= min;
			max = Math.max(max, sum);
		}else if(cnt == 3) { // 자동차 모양이라면 바로 max값과 비교한다.
			max = Math.max(max, sum);
		}
	}

}
