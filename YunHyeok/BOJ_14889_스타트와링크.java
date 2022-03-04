import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// map 입력받기
		
		dfs(0, 0);
		System.out.println(min);
	}
	
	static void dfs(int idx, int cnt) {
		if(cnt == N/2) {
			int ans = diff();
			min = Math.min(min, ans);
			return;
		}
		if(idx>=N)
			return;
		
		visited[idx] = true;
		dfs(idx+1, cnt+1);
		visited[idx] = false;
		dfs(idx+1, cnt);
	}
	
	static int diff() {
		ArrayList<Integer> aList = new ArrayList<>();
		ArrayList<Integer> bList = new ArrayList<>();
		int aSum = 0, bSum = 0;
		
		for(int i=0; i<N; i++) {
			if(visited[i])
				aList.add(i);
			else
				bList.add(i);
		}
		
		for(int i=0; i<aList.size(); i++) {
			for(int j=0; j<aList.size(); j++) {
				if(i==j) continue;
				aSum += map[aList.get(i)][aList.get(j)];
			}
		}
		
		for(int i=0; i<bList.size(); i++) {
			for(int j=0; j<bList.size(); j++) {
				if(i==j) continue;
				bSum += map[bList.get(i)][bList.get(j)];
			}
		}
		
		return Math.abs(aSum-bSum);
	}
}


//두번째 풀이
public class BOJ_14889_스타트와링크2 {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int[][] array;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		array = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		System.out.println(min);
	}
	
	static void dfs(int idx, int cnt) {
		if(cnt == N/2) {
			int ans = diff();
			min = Math.min(min, ans);
			return;
		}
		if(idx>=N)
			return;
		
		visited[idx] = true;
		dfs(idx+1, cnt+1);
		visited[idx] = false;
		dfs(idx+1, cnt);
	}
	
	static int diff() {
		int teamA = 0;
		int teamB = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				if(visited[i] && visited[j])
					teamA += array[i][j] + array[j][i];
				else if(!visited[i] && !visited[j])
					teamB += array[i][j] + array[j][i];
			}
		}
		
		return Math.abs(teamA - teamB);
	}

}
