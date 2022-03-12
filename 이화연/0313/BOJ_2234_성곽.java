import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_성곽 {
	static int N, M, result[][], cnt, width[];
	static String map[][];
	static boolean[][] visited;
	static int[] di = { 1, 0, -1, 0 }; // 하, 우, 상, 좌
	static int[] dj = { 0, 1, 0, -1 };

	public static class Node {
		int i, j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 열
		M = Integer.parseInt(st.nextToken()); // 행

		map = new String[M][N];
		result = new int[M][N]; // 같은 영역인지 숫자를 넣어줄 배열
		visited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// 이진 문자열로 변환하고 4자리로 맞추기 위해 빈 곳은 0으로 채우기
				map[i][j] = String.format("%4s", Integer.toBinaryString(Integer.parseInt(st.nextToken()))).replace(" ",
						"0");
			}
		}

		// 이 성에 있는 방의 개수 찾기
		cnt = 0; // 같은 방이 몇 개 인지 담을 변수
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) { // 방문하지 않았을 경우에만 탐색 시작
					result[i][j] = ++cnt; // 현재 시작하는 방에 cnt 넣어줌
					bfs(i, j);
				}
			}
		}

		System.out.println(cnt);
		System.out.println(roomMaxWidth());
		System.out.println(roomMaxWidthRemoveWall());
	}

	public static void bfs(int i, int j) { // 방의 개수 구하기 위해 방 번호 넣어주기
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(i, j));
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int s = 0; s < 4; s++) {
				if (map[now.i][now.j].charAt(s) == '0') { // 0인 곳에만 통로가 있음
					int nexti = now.i + di[s];
					int nextj = now.j + dj[s];
					if (nexti >= 0 && nexti < M && nextj >= 0 && nextj < N && !visited[nexti][nextj]) {
						result[nexti][nextj] = result[now.i][now.j]; // 같은 영역이라 같은 숫자 넣어줌
						queue.offer(new Node(nexti, nextj));
						visited[nexti][nextj] = true;
					}
				}
			}
		}

	}

	public static int roomMaxWidth() { // 가장 넓은 방의 넓이 찾기
		width = new int[cnt + 1]; // 방 번호가 1부터 시작해서
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				width[result[i][j]]++;
			}
		}
		int max = -1;
		for (int n : width) {
			max = Math.max(max, n);
		}
		return max;
	}

	public static int roomMaxWidthRemoveWall() { // 하나의 벽을 제거해서 얻을 수 있는 가장 넓은 방 크기 찾기
		int max = -1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int d = 0; d < 4; d++) {
					int nexti = i + di[d];
					int nextj = j + dj[d];
					// 상하좌우 보면서 현재 방 번호와 다른 방 번호를 발견하면 두개 방 넓이 더해주기
					if (nexti >= 0 && nexti < M && nextj >= 0 && nextj < N && result[i][j] != result[nexti][nextj]) {
						sum = width[result[i][j]] + width[result[nexti][nextj]];
					}
				}
				max = Math.max(sum, max);
			}
		}
		return max;
	}

	// 출력확인용
	public static void print(int[][] map) {
		for (int[] nn : map) {
			for (int n : nn) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
	}

}
