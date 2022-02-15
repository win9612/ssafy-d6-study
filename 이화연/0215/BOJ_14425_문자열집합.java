import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_14425_문자열집합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Map<Integer, String> str = new HashMap<Integer, String>();
		StringTokenizer line = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(line.nextToken()); // 집합 S 문자열 개수 = 같은 문자열 여러번 X
		int M = Integer.parseInt(line.nextToken()); // 검사해야하는 문자열

		for (int n = 0; n < N; n++) { // 입력
			str.put(n, br.readLine());
		}

		int count = 0; // 포함하고 있는 문자열 개수
		for (int m = 0; m < M; m++) {
			if (str.containsValue(br.readLine())) {
				count++;
			}
		}

		System.out.println(count);

	}

}
