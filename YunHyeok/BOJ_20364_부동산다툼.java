import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20364_부동산다툼 {
	static int N, Q; // 땅개수, 오리수
	static List<List<Integer>> list = new ArrayList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=1; i<=N; i++) {
			if(2*i <= N)
				list.get(i).add(2*i);
			if(2*i+1 <= N)
				list.get(i).add(2*i+1);
		}
		
		
		for(int i=0; i<Q; i++) {
			int end = Integer.parseInt(br.readLine());
			
			System.out.println(bfs(1, end));
		}
		
	}
	static int bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);

		while(!q.isEmpty()) {
			int v = q.poll();
			
			if(v == end && v > 1) {
				for(int i=0; i<list.size(); i++) {
					for(int j=0; j<list.get(i).size(); j++) {
						if(list.get(i).get(j) == end) list.get(i).remove(j);
					}
				}
				
				return 0;
			}
			
			for(int d=0; d<2; d++) {
				int k=0;
				if(d==0) {
					if(v*2<=N) k = v*2;
				}else {
					if(v*2+1<=N) k = v*2+1;
				}
				
				for(int i=0; i<list.get(v).size(); i++) {
					if(k == list.get(v).get(i)) q.offer(k);
				}
			}
		}
		
		return end/2;
	}
	
}

