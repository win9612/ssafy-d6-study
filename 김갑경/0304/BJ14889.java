import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14889
public class Main {

	private static int n, min = Integer.MAX_VALUE;
	private static int[][] arr;
	private static boolean[] team;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = parse(br.readLine());
		arr = new int[n][n];
		team = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = parse(st.nextToken());
			}
		}

		setTeam(0, 0);

		System.out.println(min);

	}

	private static void setTeam(int idx, int cnt) {
		if (cnt >= n / 2) {
			min = Math.min(min, getScoreDiff());
			return;
		}

		if (idx >= n)
			return;

		team[idx] = true;
		setTeam(idx + 1, cnt + 1);
		team[idx] = false;
		setTeam(idx + 1, cnt);
	}

	private static int getScoreDiff() {
		int team1 = 0;
		int team2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (team[i] && team[j]) {
					team1 += arr[i][j] + arr[j][i];
				} else if (!team[i] && !team[j]) {
					team2 += arr[i][j] + arr[j][i];
				}
			}
		}
		return Math.abs(team1 - team2);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
