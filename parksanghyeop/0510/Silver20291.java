package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Silver20291 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			String file = br.readLine();
			String extention = file.substring(file.indexOf(".") + 1);
			if (map.containsKey(extention)) {
				int tmp = map.get(extention);
				map.replace(extention, ++tmp);
			} else {
				map.put(extention, 1);
			}
		}

		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
	}
}
