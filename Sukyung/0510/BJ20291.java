package day0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ20291 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> files = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			st.nextToken();
			String file = st.nextToken();

			if (files.containsKey(file)) {
				files.put(file, files.get(file) + 1);
			} else {
				files.put(file, 1);
			}
		}
		for (String key : files.keySet()) {
			System.out.println(key + " " + files.get(key));
		}
	}
}