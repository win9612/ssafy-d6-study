import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_효율적인해킹 {
	static int N, M, max;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		result = new int[N+1];
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}

		for(int i=1; i<=N; i++) {
			bfs(i);
		}
		
		for(int i=1; i<=N; i++) {
			max = Math.max(max, result[i]);
		}
		
		for(int i=1; i<=N; i++) {
			if(max == result[i]) sb.append(i+" ");
		}
		System.out.println(sb);
		
	}
	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			ArrayList<Integer> list = graph.get(x);
			for(int i=0; i<list.size(); i++) {
				int val = list.get(i);
				if(!visited[val]) {
					visited[val] = true;
					result[val]++;
					q.offer(val);
				}
			}
		}
	}

}
