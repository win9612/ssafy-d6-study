import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/20291
public class BJ20291 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Map<String, Integer> hm = new HashMap<>();

		int n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");

			st.nextToken();
			String ext = st.nextToken();

			if (hm.get(ext) == null) {
				hm.put(ext, 1);
			} else {
				hm.put(ext, hm.get(ext) + 1);
			}
		}

		Object[] mapkey = hm.keySet().toArray();

		Arrays.sort(mapkey);

		for (Object key : mapkey) {
			System.out.println(key + " " + hm.get(key));
		}
	}
}
