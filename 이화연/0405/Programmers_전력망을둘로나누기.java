import java.util.LinkedList;
import java.util.Queue;

public class Programmers_전력망을둘로나누기 {

	public static void main(String[] args) {
		System.out.println(solution(9,
				new int[][] { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } }));
		System.out.println(solution(4, new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 } }));
		System.out.println(solution(7, new int[][] { { 1, 2 }, { 2, 7 }, { 3, 7 }, { 3, 4 }, { 4, 5 }, { 6, 7 } }));
	}

	public static int solution(int n, int[][] wires) {
		int answer = 987654321;
		int[][] map = new int[n + 1][n + 1];

		for (int i = 0; i < n - 1; i++) {
			map[wires[i][0]][wires[i][1]] = 1;
			map[wires[i][1]][wires[i][0]] = 1;
		}

		int abs = 0;
		int result = 0;
		for (int i = 0; i < n - 1; i++) {
			map[wires[i][0]][wires[i][1]] = 0; // 간선 끊기
			map[wires[i][1]][wires[i][0]] = 0;

			result = bfs(map, n, wires[i][0]);
			answer = Math.min(answer, Math.abs((n - result) - result));
			map[wires[i][0]][wires[i][1]] = 1; // 다음 간선 끊을 때 끊어져 있으면 안되니까 다시 연결
			map[wires[i][1]][wires[i][0]] = 1;
		}
		return answer;
	}

	public static int bfs(int[][] map, int N, int num) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(num);
		visited[num] = true;

		int cnt = 1; // 정점이 몇개인지
		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && map[now][i] != 0) { // 간선이 있을때만
					queue.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}

}
