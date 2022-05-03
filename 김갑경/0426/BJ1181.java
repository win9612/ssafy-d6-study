import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1181

public class BJ1181 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}

		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					return s1.compareTo(s2);
				}
				return s1.length() - s2.length();
			}
		});

		for (int i = 0; i < n; i++) {
			if (i == 0 || i >= 1 && !arr[i].equals(arr[i - 1]))
				System.out.println(arr[i]);
		}

	}
}
