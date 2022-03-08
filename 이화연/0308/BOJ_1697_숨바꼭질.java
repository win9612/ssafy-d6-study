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

		int abs = Math.abs(N - K);
		boolean[] visited = new boolean[100001]; // 0 < N <= 100,000
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N); // 수빈이 현재 점 넣기
		visited[N] = true; // 방문 처리

		int time = 0;
		while (!queue.isEmpty()) {

			int current = queue.poll();
			System.out.println("current : " + current);

			if (current == K) { // current가 K면 탈출
				break;
			}

			if (Math.abs(current - K) <= abs) {
				queue.offer(current * 2); // 순간 이동
				queue.offer(current - 1); // 걷기
				queue.offer(current + 1);
			}
//			time++;
//			System.out.println(time);
		}

		System.out.println(time);
	}

}
