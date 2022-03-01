package Day0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Baekjoon16954_움직이는미로탈출 {

	static char[][] input;
	static boolean[][] visited;
	static List<Wall> wall_list = new ArrayList<Wall>(); // 벽 위치 저장할 리스트 생성
	static int[] dx = {0,0,-1,1,1,1,-1,-1,0};
	static int[] dy = {1,-1,0,0,-1,1,-1,1,0};
	static Queue<Character> queue = new LinkedList<Character>();
	static boolean isSuccess;
	
	public static void main(String[] args) throws IOException {
		// TODO 움직이는 미로 탈출
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = new char[8][8];
		visited = new boolean[8][8];
		for(int i=0; i<8; i++) {
			input[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(input[i][j]=='#') {
					wall_list.add(new Wall(i, j));
				}
			}
		}
		
		bfs();
		if(isSuccess) System.out.println(1);
		else System.out.println(0);
		

	}

	static class Wall{
		int x;
		int y;
		public Wall(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static class Character{
		int x;
		int y;
		public Character(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static void wall_move() {
		for(int i=0; i<wall_list.size(); i++) {
			Wall temp_wall = wall_list.get(i);
			int current_x = temp_wall.x;
			int current_y = temp_wall.y;
			int next_x = temp_wall.x + 1;
			if(0<=current_x&&0<=current_y&&current_x<8&&current_y<8) {
				wall_list.get(i).x = next_x;
				input[current_x][current_y] = '.';
				if(next_x<8) input[next_x][current_y] = '#';
			}
		}
	}
	
	static void bfs() {
		queue.offer(new Character(7, 0));
		visited[7][0] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();

			for(int s=0; s<size; s++) {
				Character temp = queue.poll();
				if(input[temp.x][temp.y]=='#') break; // 이거 고쳐야됩니다.. 
				for(int i=0; i<9; i++) {
					int next_x = temp.x + dx[i];
					int next_y = temp.y + dy[i];
					if(i==9) {
						continue; // 제자리에 서있으면 continue
					}
					if(0<=next_x&&0<=next_y&&next_x<8&&next_y<8&&!visited[next_x][next_y]&&input[next_x][next_y]=='.') {
						visited[next_x][next_y] = true;
						queue.offer(new Character(next_x, next_y));
						if(next_x==0&&next_y==7) {
							isSuccess=true;
							break;
						}
					}
				}
			}
			wall_move();
		}
	}
}
