import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918_봄버맨 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken()); // 행
		int C = Integer.parseInt(st.nextToken()); // 열
		int N = Integer.parseInt(st.nextToken()); // 초

		int[] di = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
		int[] dj = { 0, 0, -1, 1 };

		int visited[][] = new int[R][C]; // 몇 초에 폭탄이 들어가는지 확인할 배열
		char map[][] = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int time = 0;
		for (int i = 0; i < R; i++) { // 초기상태를 세팅하기 위해 폭탄 찾는다
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'O') {
					visited[i][j] = time + 3; // 초기 폭탄의 초는 3초로 설정
				}
			}
		}

		while (time++ != N) { // 시간이 N초가 될때까지
			if (time % 2 == 0) { // 시간이 짝수일때는 빈칸에 폭탄을 채워넣어야함
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (map[i][j] == '.') { // 빈칸이면
							map[i][j] = 'O'; // 폭탄으로 바꾸고
							visited[i][j] = time + 3; // 폭탄 넣는 시간을 기록
						}
					}
				}
			} else {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (visited[i][j] == time) {
							map[i][j] = '.'; // 빈칸으로 바꾸고
							for (int d = 0; d < 4; d++) { // 인접한 곳 폭탄 파괴
								int nexti = i + di[d];
								int nextj = j + dj[d];
								
								if (nexti >= 0 && nexti < R && nextj >= 0 && nextj < C && map[nexti][nextj] == 'O'
										&& visited[nexti][nextj] != time) {
									map[nexti][nextj] = '.';
									visited[nexti][nextj] = 0;
								}
							}
						}
					}
				}
			}
		}
		print(map);
	}

	public static void print(char[][] map) {
		for (char[] cc : map) {
			for (char c : cc) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
