import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_1181_단어정렬 {
  
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 단어 개수
		String[] words = new String[N];

		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		Arrays.sort(words, new Comparator<String>() { // 정렬
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) { // 길이 같으면
					return o1.compareTo(o2); // 사전 순 정렬
				} else {
					return o1.length() - o2.length();
				}
			}
		});

		for (int i = 0; i < N - 1; i++) {
			if (!words[i].equals(words[i + 1])) {
				System.out.println(words[i]);
			}
		}
		System.out.println(words[N-1]);

	}
  
  /*
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 단어 개수
		ArrayList[] words = new ArrayList[51]; // 길이 50넘지 않음

		for (int i = 0; i < 51; i++) {
			words[i] = new ArrayList<String>();
		}

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			boolean flag = true;
			for (int j = 0; j < words[s.length()].size(); j++) { // 해당 단어의 길이의 리스트 확인
				if (words[s.length()].get(j).equals(s)) { // 만약 같은단어가 있으면
					flag = false;
					break;
				}
			}
			if (flag) { // 같은 단어 없을때만
				words[s.length()].add(s);
			}
		}

		for (int i = 0; i < 51; i++) {
			if (words[i].size() != 0 && words[i].size() > 1) { // 길이 같은게 여러개면
				Collections.sort(words[i]); // 정렬
				for (int j = 0; j < words[i].size(); j++) {
					System.out.println(words[i].get(j));
				}
			} else if (words[i].size() != 0 && words[i].size() == 1) { // 길이 같은 것 없이 하나만 있으면
				System.out.println(words[i].get(0)); // 그냥 출력
			}
		}
	}
  */

}
