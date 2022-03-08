import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class BOJ_1197_최소스패닝트리 {
	static int V, E, result, cnt;
	static int[] parent;
	static ArrayList<Edge> edgeList;
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a < b) 
			parent[b] = a;
		else
			parent[a] = b;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V+1];
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		
		edgeList = new ArrayList<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		
		Collections.sort(edgeList); //오름차순 정렬
		
		
		for(Edge edge : edgeList) { // 간선정보 하나씩 빼면서
			if(find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				result += edge.weight;
			}
		}
		
		System.out.println(result);
		
	}

}
