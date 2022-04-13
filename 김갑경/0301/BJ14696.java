import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14696
public class Main {

	private static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parse(br.readLine());

		TC: for (int tc = 0; tc < n; tc++) {
			arr = new int[2][4];
			input(br.readLine(), 0); // 어린이A
			input(br.readLine(), 1); // 어린이B

			// 점수비교
			for (int j = 3; j >= 0; j--) {
				if (arr[0][j] > arr[1][j]) {
					System.out.println("A");
					continue TC;
				} else if (arr[0][j] < arr[1][j]) {
					System.out.println("B");
					continue TC;
				}
			}
			System.out.println("D");
		}
	}

	private static void input(String s, int idx) {
		StringTokenizer st = new StringTokenizer(s);
		int tmp = parse(st.nextToken());
		for (int i = 0; i < tmp; i++)
			arr[idx][parse(st.nextToken()) - 1]++;
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}

}
