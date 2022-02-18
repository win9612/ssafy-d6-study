### v1 ###
import java.util.*;
import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11663
public class Main {

	private static int n;
	private static int[] point;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parse(st.nextToken());
		int m = parse(st.nextToken());
		point = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			point[i] = parse(st.nextToken());
		Arrays.sort(point);
		// end Input

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = parse(st.nextToken());
			int end = parse(st.nextToken());
			int l = search(start);
			int r = search(end);
			if (point[l] >= start)
				l--;
			if (point[r] <= end)
				r++;
			sb.append((r - l - 1) + "\n");
		}
		System.out.println(sb);
	}

	private static int search(int key) {
		int l = 0;
		int r = n - 1;
		while (l < r) {
			int mid = (l + r) / 2;
			if (point[mid] == key)
				return mid;
			else if (point[mid] < key)
				l = mid + 1;
			else
				r = mid - 1;
		}
		return l;
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}



### v2 ###
import java.io.*;
import java.util.*;

public class Main {

	private static int n;
	private static int[] point;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parse(st.nextToken());
		int m = parse(st.nextToken());
		point = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			point[i] = parse(st.nextToken());
		Arrays.sort(point);
		// end Input

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = parse(st.nextToken());
			int end = parse(st.nextToken());
			// lower bound
			int l = 0;
			int r = n - 1;
			while (l <= r) {
				int mid = (l + r) / 2;
				if (point[mid] == start) {
					r = mid - 1;
				} else if (point[mid] > start) {
					r = mid - 1;
				} else
					l = mid + 1;
			}
			int ans = r + 1;

			// upper bound
			l = 0;
			r = n - 1;
			while (l <= r) {
				int mid = (l + r) / 2;
				if (point[mid] == end) {
					l = mid + 1;
				} else if (point[mid] > end) {
					r = mid - 1;
				} else
					l = mid + 1;
			}
			sb.append((r - ans + 1) + "\n");
		}
		System.out.println(sb);
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
