import java.util.*;

class Solution {
	public static int answer;
	public static boolean[][] visit;
	public static boolean[] num;

	public int solution(int n, int[][] wires) {
		visit = new boolean[n + 1][n + 1];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < wires.length; i++) {
			visit[wires[i][0]][wires[i][1]] = true;
			visit[wires[i][1]][wires[i][0]] = true;
		}

		for (int i = 0; i < wires.length; i++) {
			num = new boolean[n + 1];
			visit[wires[i][0]][wires[i][1]] = false;
			visit[wires[i][1]][wires[i][0]] = false;
			bfs(n);
			visit[wires[i][0]][wires[i][1]] = true;
			visit[wires[i][1]][wires[i][0]] = true;
		}
		return answer;
	}

	public void bfs(int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		num[1] = true;

		int count = 1;
		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int i = 1; i <= n; i++) {
				if (!num[i] && visit[now][i]) {
					count += 1;
					queue.offer(i);
					num[i] = true;
				}
			}
		}
		answer = Math.min(answer, Math.abs((n - count) - count));
	}
}
