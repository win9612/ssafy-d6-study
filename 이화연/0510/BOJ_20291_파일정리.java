import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_20291_파일정리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 파일 개수

		Map<String, Integer> map = new TreeMap<String, Integer>(); // TreeMap은 자동정렬
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			st.nextToken();
			String s = st.nextToken();

			if (!map.isEmpty() && map.containsKey(s)) { // 확장자를 이미 포함하고 있으면
				map.put(s, map.get(s) + 1); // 1 증가
			} else { // 없는 확장자면 map에 put
				map.put(s, 1);
			}
		}

		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
	}

}
