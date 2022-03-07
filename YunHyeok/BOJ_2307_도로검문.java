import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2307_도로검문 {
	static int N, M, min, max;
	static boolean[] visited;
	static int[] dist;
	static int[] road;
	static ArrayList<ArrayList<Vertex>> list;
	static PriorityQueue<Vertex> q;
	static class Vertex implements Comparable<Vertex>{
		int no, minDist;
		Vertex(int no, int minDist){
			this.no = no;
			this.minDist = minDist;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.minDist - o.minDist;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정점의 수
		M = Integer.parseInt(st.nextToken()); // 간선의 수
		
		dist = new int[N+1];
		road = new int[N+1];
		visited = new boolean[N+1];
		list = new ArrayList<>();
		for(int i=0; i<N+1; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(from).add(new Vertex(to, weight));
			list.get(to).add(new Vertex(from,weight)); // 양방향
		} // 정점, 간선, 가중치 입력받기
		
		// 검문이 없을 경우 용의자의 최소거리 구하기 시작
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		q = new PriorityQueue<>();
		q.offer(new Vertex(1, 0));
		
		while(!q.isEmpty()) {
			Vertex curr = q.poll();
			if(visited[curr.no]) continue;
			
			visited[curr.no] = true;
			
			for(Vertex next : list.get(curr.no)) {
				if(!visited[next.no] && dist[next.no] > next.minDist + curr.minDist) {
					dist[next.no] = next.minDist + curr.minDist;
					q.offer(new Vertex(next.no, dist[next.no]));
					road[next.no] = curr.no; //최소거리로 연결된 길만 검문하기 위함
				}
			}
		}
		// 검문이 없을 경우 용의자의 최소거리 구하기 종료
		
//		System.out.println(Arrays.toString(road));
		min = dist[N]; //검문이 없을 경우 용의자의 최소거리
		max = -1; //지연 최대 시간
	
		for(int i=N; i>1; i=road[i]) { // 지나온 길만 검사하기 위해 i=road[i]
			visited = new boolean[N+1]; //방문때마다 초기화 필수
			//road : 시작 -- i : 끝
			int time = findroad(road[i], i);
			max = Math.max(max, time);
		}
		
		if(max == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(max-min);
	}
	
	static int findroad(int from, int to) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		q = new PriorityQueue<>();
		q.offer(new Vertex(1, 0));
		
		while(!q.isEmpty()) {
			Vertex curr = q.poll();
			if(visited[curr.no]) continue;
			
			visited[curr.no] = true;
			
			for(Vertex next : list.get(curr.no)) {
				if(next.no == to && curr.no == from)
					continue;
				if(!visited[next.no] && dist[next.no] > next.minDist + curr.minDist) {
					dist[next.no] = next.minDist + curr.minDist;
					q.offer(new Vertex(next.no, dist[next.no]));
				}
			}
		}
		return dist[N];
	}
	
}