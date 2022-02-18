### v1 ###
import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/10815
public class BJ10815 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			set.add(Integer.parseInt(st.nextToken()));

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++)
			sb.append((set.contains(Integer.parseInt(st.nextToken())) ? "1 " : "0 "));

		System.out.println(sb);
	}
}




### v2 ###
import java.io.*;
import java.util.*;

public class BJ10815 {

	private static int n;
	private static int[] card;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = parse(br.readLine());
		card = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			card[i] = parse(st.nextToken());
		Arrays.sort(card);
		// end Input

		int m = parse(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m; i++)
			sb.append(search(parse(st.nextToken())) + " ");
		System.out.println(sb);
	}

	private static int search(int key) {
		int l = 0;
		int r = n - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (card[mid] == key)
				return 1;
			else if (card[mid] < key)
				l = mid + 1;
			else
				r = mid - 1;
		}
		return 0;
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
