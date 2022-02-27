import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16954_움직이는미로탈출 {
	static ArrayList<char[]> list;
	static Queue<Point> q = new LinkedList<>();
	static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1}; // 1. 상하좌우 + 대각선이동 + 제자리
	static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	static int N = 8;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			list.add(br.readLine().toCharArray()); //맵을 계속 바꿔줘야해서 ArrayList로 선언
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		q.offer(new Point(N-1, 0));
		
		while(!q.isEmpty()) {
			visited = new boolean[N][N]; // 맵이 계속 바뀌니까 그때마다 초기화를 해줘야 함..
			
			int size = q.size(); // 무조건! 해줘야 함 for문안에서 poll을 하면 size가 변경이 되니까
			for(int s=0; s<size; s++) { // 맵이 바뀔때마다 
				Point point = q.poll();
				
				if(list.get(point.y)[point.x] == '#') continue; // 벽을 만나면 큐에서 다음 좌표를 부르기
				if(point.y == 0 && point.x == N-1) return 1; // 목적지에 도달했다면
				
				for(int d=0; d<9; d++) {
					int y = point.y + dy[d];
					int x = point.x + dx[d];
					if(y>=0 && x>=0 && y<N && x<N) {
						if(list.get(y)[x] == '.' && !visited[y][x]) {
							visited[y][x] = true;
							q.offer(new Point(y, x));
						}
					}
				}
			}
			
			list.remove(N-1); // 맨 아래에 행 삭제
			list.add(0, new char[] {'.', '.', '.', '.', '.', '.', '.', '.'}); //맨 위에 행 추가
		}
		return 0;
	}
	
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	

}
