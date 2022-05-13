import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1655
public class BJ1655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> large = new PriorityQueue<>(); // 작은 순으로
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // 큰 순으로

		int n = parse(br.readLine());
		for (int i = 0; i < n; i++) {
			int now = parse(br.readLine());
			large.offer(now);
			while (!small.isEmpty() && small.size() == large.size() && small.peek() > large.peek()) {
				int tmp = large.poll();
				large.offer(small.poll());
				small.offer(tmp);
			}

			if (small.size() < large.size())
				small.offer(large.poll());

			sb.append(small.peek()).append("\n");
		}

		System.out.println(sb);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
