import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780_종이의갯수 {
	static int N, aCnt, bCnt, cCnt;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,N);
		System.out.println(aCnt);
		System.out.println(bCnt);
		System.out.println(cCnt);
	}
	
	static void dfs(int si, int sj, int size) {
		boolean flag = true;
		int tmp = graph[si][sj];
		for(int i=si; i<si+size; i++) {
			for(int j=sj; j<sj+size; j++) {
				if(tmp != graph[i][j]) flag = false; 
			}
		}
		
		if(flag) {
			if(tmp == -1) aCnt++;
			else if(tmp == 0) bCnt++;
			else cCnt++;
			return;
		}
		
		int s = size/3;
		
		// 00, 03, 06
		dfs(si, sj, s);
		dfs(si, sj+s, s);
		dfs(si, sj+s*2, s);
		
		// 30, 33, 36
		dfs(si+s, sj, s);
		dfs(si+s, sj+s, s);
		dfs(si+s, sj+s*2, s);
		
		// 60, 63, 66
		dfs(si+s*2, sj, s);
		dfs(si+s*2, sj+s, s);
		dfs(si+s*2, sj+s*2, s);
		
		
	}
	

}
