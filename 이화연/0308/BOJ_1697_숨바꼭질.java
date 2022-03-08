import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697_숨바꼭질 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이 현재 점
		int K = Integer.parseInt(st.nextToken()); // 수빈이가 이동해야 할 점

		int[] visited = new int[100001]; // 0 < N <= 100,000

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N); // 수빈이 현재 점 넣기

		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == K) { // current가 K면 탈출
				System.out.println(visited[current]);
				return;
			}

			if (current - 1 >= 0 && visited[current - 1] == 0) { // N-1
				visited[current - 1] = visited[current] + 1;
				queue.offer(current - 1);
			}
			if (current + 1 <= 100000 && visited[current + 1] == 0) { // N+1
				visited[current + 1] = visited[current] + 1;
				queue.offer(current + 1);
			}
			if (current * 2 <= 100000 && visited[current * 2] == 0) { // N*2
				visited[current * 2] = visited[current] + 1;
				queue.offer(current * 2);
			}

		}

	}

}
