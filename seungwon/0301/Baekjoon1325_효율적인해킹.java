package Day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1325_효율적인해킹 {

	static Node[] input;
	static int N, M;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		// TODO 효율적인 해킹
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		
		// 인접리스트 생성
		input = new Node[N+1];
		
		for(int i=1; i<=M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st2.nextToken());
			int from = Integer.parseInt(st2.nextToken());
			input[from] = new Node(to, input[from]);
		}
		
		// 각 정점의 count를 저장할 변수 생성
		answer = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			//boolean[] visited = new boolean[N+1];
			//dfs(input, visited, i, i);
			bfs(input, i, i);
		}
		
		int max=0;
		for(int i=1; i<=N; i++) {
			if(answer[i]>=max) {
				max = Math.max(max, answer[i]);
				System.out.print(i+" ");
			}	
		}
	}
	
	// Node 클래스 생성
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link = " + link + "]";
		}
	}
	
	/*
	// dfs 함수
	static void dfs(Node[] input, boolean[] visited, int current, int i) {
		visited[current] = true;
		answer[i]++;
		
		for(Node temp=input[current]; temp!=null; temp=temp.link) {
			if(!visited[temp.vertex]) {
				dfs(input, visited, temp.vertex, i);
			}
		}
	}
	*/
	public static void bfs(Node[] adjList, int start, int i) {
		Queue<Integer> queue = new LinkedList<Integer>(); 
		boolean[] visited = new boolean[N+1]; 
		
		queue.offer(start); 
		visited[start] = true; 
		
		while(!queue.isEmpty()) {  
			int current = queue.poll(); 

			for(Node temp=adjList[current]; temp != null; temp = temp.link) { 
		
				if(!visited[temp.vertex]) { 
					queue.offer(temp.vertex); 
					visited[temp.vertex] = true; 
					answer[i]++;
				}
			}
		}
	}
}
