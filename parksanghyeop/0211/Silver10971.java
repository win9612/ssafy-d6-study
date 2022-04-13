package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver10971 {

	static int n;
	static int[][] w;
	static boolean[] visited;
	static long result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		w = new int[n][n];
		// 배열 입력
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++)
				w[i][j] = Integer.parseInt(st.nextToken());
		}

		// 여행
		for (int start = 0; start < n; start++) {
			visited = new boolean[n];
			visited[start] = true;
			dfs(start, start , 0);
		}
		System.out.println(result);

	}

	public static void dfs(int start, int current, long weight) {
		if(allVisited()) {
			if(w[current][start]!=0) {
				// 지금까지 더한 비용 weight + 나한테 돌아오는 비용 w[current][0]
				// 이전에 계산했던 값 result와 비교해서 더작은걸 result로 갱신
				result = Math.min(weight+w[current][0], result);
			}
		}
		
		for(int i=start;i<n;i++) {
			if(!visited[i] && w[current][i] !=0) {
				visited[i] = true;
				dfs(start, i, weight+w[current][i]);
				visited[i] = false;
			}			
		}
	}
	
	public static boolean allVisited() {
		for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
	}

}
