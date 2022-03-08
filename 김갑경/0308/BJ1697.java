import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1697
// BJ1697: 숨바꼭질
public class BJ1697 {

	private static boolean[] visit = new boolean[100002];
	private static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken());
		int k = parse(st.nextToken());

		q.offer(n);
		visit[n] = true;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int tmp = q.poll();
				if (tmp == k) {
					System.out.println(time);
					return;
				}

				move(tmp - 1);
				move(tmp + 1);
				move(2 * tmp);
			}
			time++;
		}
	}

	private static void move(int x) {
		if (x < 0 || x >= visit.length || visit[x])
			return;
		visit[x] = true;
		q.offer(x);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
