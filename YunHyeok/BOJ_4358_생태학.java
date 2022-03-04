import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Boj4358_생태학 {
	static HashMap<String, Integer> array = new HashMap<>();
	static int totalCnt = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		while (true) {

			if (array.containsKey(str)) {// 만약, str이 존재한다면
				array.put(str, array.get(str) + 1);
				totalCnt++;
			} else {// str이 존재하지 않는다면
				array.put(str, 1);
				totalCnt++;
			}

			str = br.readLine();
			if (str == null || str.length() == 0) {
				break;
			}
		}

		// 오름차순 정렬
		List<String> keySet = new ArrayList<>(array.keySet());
		Collections.sort(keySet);
		for (String key : keySet) {
			System.out.printf("%s %.4f \n", key, (double) array.get(key) / totalCnt * 100);
		}

	}

}
