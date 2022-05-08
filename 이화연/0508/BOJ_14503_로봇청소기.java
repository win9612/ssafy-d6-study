import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	static int N, M, map[][], area;
	static int[] di = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken()); // 행
		M = stoi(st.nextToken()); // 열

		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int robot_i = stoi(st.nextToken()); // 로봇
		int robot_j = stoi(st.nextToken());
		int robot_d = stoi(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}

		area = 1; // 청소하는 칸의 개수, 처음에 로봇청소기가 위치한 칸은 무조건 청소함
		clean(robot_i, robot_j, robot_d);
		System.out.println(area);
	}

	public static void clean(int i, int j, int d) {
		map[i][j] = 2; // 현재 위치 청소

		for (int k = 0; k < 4; k++) { // 왼쪽 방향으로 회전하는 것 4번 반복
			d = d - 1 < 0 ? 3 : d - 1;
			int nexti = i + di[d];
			int nextj = j + dj[d];
			if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && map[nexti][nextj] == 0) { // 청소하지 않은 빈공간일때
				area++;
				clean(nexti, nextj, d); // 왼쪽으로 회전하고 한 칸 전진
				return;
			}
		}

		int newD = d < 2 ? d + 2 : d - 2; // 뒤쪽
		int backi = i + di[newD];
		int backj = j + dj[newD];
		if (backi >= 0 && backi < N && backj >= 0 && backj < M && map[backi][backj] != 1) { // 뒤쪽이 벽이 아닐때 후진
			clean(backi, backj, d);
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
