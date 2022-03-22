package Day0322;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16948_데스나이트 {
	static int N, sy,sx,ey,ex, map[][], dy[] = {-2,-2,0,0,2,2},dx[] = {-1,1,-2,2,-1,1};
	static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 체스판의 크기 N x N
		map = new int[N][N];
		// 방문처리 배열
		visit = new boolean[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken()); // r1
 		sx = Integer.parseInt(st.nextToken()); // c1
		ey = Integer.parseInt(st.nextToken()); // r2
		ex = Integer.parseInt(st.nextToken()); // c2
		bfs();
	}
	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(sy,sx,0)); // 시작 좌표 입력
		visit[sy][sx] = true; // 방문 처리
		while(!q.isEmpty()) {
			Node n = q.poll();
			if(n.y == ey && n.x == ex) { // 도착 좌표에 도착했으면 끝낸다/
				System.out.println(n.cnt); // 이 때의 cnt 가 최소 : BFS 이기 떄문에
				return;
			}
			for (int i = 0; i < 6; i++) { // 6 가지 방법으로 좌표 변화
				int ny = n.y + dy[i];
				int nx = n.x + dx[i];
				if(ny < 0 || nx < 0 || ny >=N || nx >= N||visit[ny][nx]) // 지도 범위에서 벗어나거나  방문처리 되었다면 continue 
					continue;
				q.offer(new Node(ny,nx,n.cnt+1)); // 현재 cnt 에 1을 더한 값을 queue 에 저장
				visit[ny][nx] = true; // 방문처리
			}
		}
		System.out.println(-1); // queue를 다 돌고도 return 문을 만나지 못한 경우 -> 이동 불가인 경우 -1 출력
	}
	
	static class Node{
		int y, x, cnt; // x 좌표, y 좌표, 이동 횟수
		Node(int y, int x, int cnt){
			this.y = y;
			this.x = x;
			this.cnt = cnt;
	  }
  }
}
