package Day0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon2307_도로검문 {
	
	static class Vertex implements Comparable<Vertex>{
		int no; // 정점 번호
		int minDistance; // 출발지에서 자신으로의 최소비용
		
		public Vertex(int no, int minDistance) {
			super();
			this.no = no;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDistance-o.minDistance;
		}
		
	}
	
	static int N, M;
	static int[][] adjMatrix;
	static int start, end;
	static int[] distance;
	static boolean[] selected;
	static PriorityQueue<Vertex> pqueue;
	
	static int Dijkstra(int[][] matrix) {
		
		pqueue = new PriorityQueue<Vertex>();
		Arrays.fill(distance, Integer.MAX_VALUE); // 큰 값으로 초기화
		Arrays.fill(selected, false); // false로 초기화
		
		// 시작 정점 처리
		distance[start] = 0;
		pqueue.offer(new Vertex(start, distance[start]));

		while (!pqueue.isEmpty()) {
			Vertex current_vertex = pqueue.poll();
			if (selected[current_vertex.no])
				continue; // 확정된 정점이면 넘어가

			selected[current_vertex.no] = true; // 현재 정점 확정 처리
			if (current_vertex.no == end)
				break; // 현재 정점이 도착점이면 끝내라

			for (int j = 1; j <= N; j++) {
				if (!selected[j] && adjMatrix[current_vertex.no][j] != 0 && // 확정되지 않았고 해당 정점으로의 가중치가 0이 아니라면
						distance[j] > distance[current_vertex.no] + adjMatrix[current_vertex.no][j]) {
					distance[j] = distance[current_vertex.no] + adjMatrix[current_vertex.no][j];
					pqueue.offer(new Vertex(j, distance[j]));
				}
			}
		}
		return distance[end]; // 종료 지점 까지의 최소 거리 출력
	}
	
	public static void main(String[] args) throws IOException {
		// TODO 도로 검문
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		adjMatrix = new int[N+1][N+1];
		start = 1; // 시작 정점 인덱스 
		end = N; // 도착 정점 인덱스
		
		// 간선 정보들 하나씩 저장해놓을 2차원 배열 행 -> 간선, 열-> from, to
		int[][] hoobow = new int[M+1][2];
		// 간선 입력 받기
		for(int i=1; i<=M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st2.nextToken());
			int to = Integer.parseInt(st2.nextToken());
			int weight = Integer.parseInt(st2.nextToken());
			adjMatrix[from][to] = weight;
			adjMatrix[to][from] = weight;
			// 간선들 하나식 저장해놓기
			hoobow[i][0] = from; 
			hoobow[i][1] = to; 
		}
		
		
		selected = new boolean[N+1];
		distance = new int[N+1];
		
		
		int answer = 0;
		int before = Dijkstra(adjMatrix);
		int temp_weight = 0;
		int after = 0;
		int temp_from;
		int temp_to;
		
		for(int i=1; i<=M; i++) { // 저장해놓았던 간선들을 하나씩 탐색하며 지워보며 다익스트라 진행
			temp_from = hoobow[i][0];
			temp_to = hoobow[i][1];
				
			temp_weight = adjMatrix[temp_from][temp_to];
			
			adjMatrix[temp_from][temp_to] = 0; // 현재 선택된 간선을 0으로 바꾼다
			after = Dijkstra(adjMatrix); // 해당 간선이 없는 상태로 다시 돌린다.
			if(after==Integer.MAX_VALUE) { // 종점까지 가지 못한다면 
				answer = -1; // answer에 -1값을 주고 반복문을 끝낸다.
				break;
			}
			adjMatrix[temp_from][temp_to] = temp_weight; // 다시 되돌린다
			answer = Math.max(answer, after-before); // 최대 지연 값 갱신
					
		}
		
		System.out.println(answer);
		

	}

}
