// 첫번째 풀이

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1697_숨바꼭질_클래스풀이 {
	static int[] dx = { -1, +1, 2 };
	static int N, K, cnt;
	static boolean[] visited = new boolean[100001];
	static Queue<Point> q = new LinkedList<>();

	static class Point{
		int v, cnt;
		Point(int v, int cnt){
			this.v = v;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 수빈이
		K = sc.nextInt(); // 동생

		q.offer(new Point(N, 0));
		visited[N] = true;
		
		while (!q.isEmpty()) {
			Point curr = q.poll();
			visited[curr.v] = true;

			if (curr.v == K) {
				System.out.println(curr.cnt);
				break;
			}

			for (int i = 0; i < dx.length; i++) {
				int nextV = 0;
				if (i == 2)
					nextV = curr.v * dx[i];
				else
					nextV = curr.v + dx[i];

				if (nextV >= 0 && nextV < 100001 && !visited[nextV]) {
					visited[nextV] = true;
					q.offer(new Point(nextV, curr.cnt+1));
				}
			}
		}

	}

}








// 두번째 풀이

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1697_숨바꼭질_세번째풀이 {
	static int[] dx = { -1, +1, 2 };
	static int N, K, cnt;
	static boolean[] visited = new boolean[100001];
	static int[] map = new int[100001];
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt(); // 수빈이
		K = sc.nextInt(); // 동생

		q.offer(N);
		visited[N] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();
			visited[curr] = true;

			if (curr == K) {
				System.out.println(map[curr]);
				break;
			}

			for (int i = 0; i < dx.length; i++) {
				int nextV = 0;
				if (i == 2)
					nextV = curr * dx[i];
				else
					nextV = curr + dx[i];

				if (nextV >= 0 && nextV < 100001) {
					if (!visited[nextV]) {
						visited[nextV] = true;
						q.offer(nextV);
						map[nextV] = map[curr] + 1; // 어차피 제일 가까운 곳에만 +1을 할테니
					}
				}
			}
		}

	}

}
