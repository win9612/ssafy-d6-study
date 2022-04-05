package Day0405;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_전력망을둘로나누기 {

	static int n;
	static int[][] wires;
	static int result;
	static int[][] adjMatrix;
	public static void main(String[] args) {
		// TODO 전력망을 둘로 나누기
		// n = 9;
		//wires = new int[][] {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
		n = 7;
		wires = new int[][] {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
		result = Integer.MAX_VALUE;
		
		adjMatrix = new int[n+1][n+1];
		
		// input
		for(int i=0; i<wires.length; i++) {
			int start = wires[i][0];
			int end = wires[i][1];
			
			adjMatrix[start][end] = 1;
			adjMatrix[end][start] = 1;
		}
		
		// 간선을 하나씩 끊어본다.
		for(int i=0; i<wires.length; i++) {
			int temp_start = wires[i][0];
			int temp_end = wires[i][1];
			
			// 간선 끊기
			adjMatrix[temp_start][temp_end] = 0;
			adjMatrix[temp_end][temp_start] = 0;
			
			// bfs 수행
			result = Math.min(result, bfs(n, 1));
			
			// 간선 다시 복구
			adjMatrix[temp_start][temp_end] = 1;
			adjMatrix[temp_end][temp_start] = 1;
			
			
		}
		System.out.println(result);
	}
	
	static int bfs(int n, int start){ // 정점의 개수와 시작 정점 입력 받기
        boolean[] visited= new boolean[n+1]; // 방문 처리 배열
        int count=1; // 연결된 정점 개수 셀 변수
        
        Queue<Integer> queue= new LinkedList<>();
        queue.offer(start); // 첫 정점 queue 에 넣기
        
        while(!queue.isEmpty()){
            int current= queue.poll();
            visited[current]= true; // 방문처리
            
            for(int i=1; i<=n; i++){ 
                if(!visited[i] && adjMatrix[current][i]==1) { // 방문처리 되지 않았고 인접한 정점 
                    queue.offer(i);
                    count++; // 전체 개수 N에서 이 count를 빼준 것이 다른 부분의 송전탑의 개수
                }
            }
        }
        return Math.abs( count-(n-count) ); // 두 그룹의 차이
    }
}
