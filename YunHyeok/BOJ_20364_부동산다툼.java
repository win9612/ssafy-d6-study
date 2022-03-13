import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_20364_부동산다툼 {
	static int Q, N;
	static List<List<Integer>> list;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Q = Integer.parseInt(st.nextToken()); // 땅 개수
		N = Integer.parseInt(st.nextToken()); // 오리 수
		
		list = new ArrayList<>();
		visited = new boolean[Q+1];
		for(int i=0; i<=Q; i++) {
			list.add(new ArrayList<>());
		}
		
		// 주어진 이진트리를 간선리스트로 만들기
		for(int i=1; i<=Q; i++) {
			if(i>1) {
				if(i % 2 == 0)
					list.get(i).add(i/2);
				else
					list.get(i).add(i/2);
			}
			
			if(2*i <= Q)
				list.get(i).add(2*i);
			if(2*i+1 <= Q)
				list.get(i).add(2*i+1);
		}
		
//		for(List li : list) {
//			System.out.println(li);
//		}
		
		for(int i=0; i<N; i++) {
			bfs(Integer.parseInt(br.readLine()));
		}
		System.out.println(sb);
		
	}
	static void bfs(int target) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(target);
		
		while(!q.isEmpty()) {
			int curr = q.poll();

			if(visited[curr]) {
				sb.append(curr+"\n");
				break;
			}
			
			//1 방문시 그 이후 방문하는 땅은 다 1로 나와야 되는거 아닌지..
			/*
			 6 4
			 1
			 2
			 3
			 4
			 */
//			if(curr == 1) {
//				sb.append(0 + "\n");
//				visited[target] = true;
//			}
			
			if(curr == 1 && target == 1) {
				sb.append(0 + "\n");
			}else if(curr == 1 && target != 1) {
				sb.append(0 + "\n");
				visited[target] = true;
			}
			

			for(Integer next : list.get(curr)) {
				if(next < curr) {
					q.offer(next);
				}
			}
		
		}
	}
}