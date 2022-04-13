import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18429_근손실 {
	static int N, K, cnt;
	static int[] array;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			array[i] = Integer.parseInt(st.nextToken())-K; // 하루에 중량 4씩 감소
		}
		
		dfs(0, 0);
		System.out.println(cnt);
	}

	static void dfs(int day, int weight) {
		if(day == N) { // 주어진 일(N)을 다채웠을 경우
			cnt++;
			return;
		}
			
		//순열
		for(int i=0; i<N; i++) {
			if(!visited[i] && weight + array[i] >= 0) { // 중량이 500이상인 경우만 
				visited[i] = true;
				dfs(day+1, weight+array[i]); 
				visited[i] = false;
			}
		}
	}
}