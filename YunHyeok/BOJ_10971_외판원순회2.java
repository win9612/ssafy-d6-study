import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회2 {
	static int N;
	static int[][] array;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE; // 각 행렬의 성분은 1,000,000이하의 양의 정수
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		array = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, i, 0);
		}
		
		System.out.println(min);
		
	}
	static void dfs(int start, int end, int sum) {
		if(visitCheck()) { 
			if(array[start][end] != 0) {
				sum += array[start][end];
				min = Math.min(min, sum);
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(array[start][i] != 0 && !visited[i]) {
				visited[i] = true;
				dfs(i, end, sum + array[start][i]);
				visited[i] = false;
			}
		}
	}
	
	static boolean visitCheck() { // visited 탐색
		for(int i=0; i<N; i++) {
			if(!visited[i])
				return false;
		}
		return true;
	}

}
