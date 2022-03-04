import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/13300
public class BJ13300 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] students = new int[7][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken());
		int k = parse(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = parse(st.nextToken());
			int grade = parse(st.nextToken());
			students[grade][gender]++;
		}

		int rooms = 0;
		for (int i = 1; i <= 6; i++) {
			rooms += students[i][0] / k + (students[i][0] % k > 0 ? 1 : 0);
			rooms += students[i][1] / k + (students[i][1] % k > 0 ? 1 : 0);
		}
		System.out.println(rooms);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
