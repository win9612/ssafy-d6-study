package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gold1062 {

	static int n, k, result=0;
	static boolean[] visited;
	static String[] word;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		word = new String[n];
		visited = new boolean[26];

		if (k < 5 || k == 26) {
			System.out.println(k == 26 ? n : 0);
			return;
		}

		k -= 5;

		for (int i = 0; i < n; i++) {
			word[i] = br.readLine();

			String filter = word[i].substring(4, word[i].length() - 4);

		}

		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['c' - 'a'] = true;
		visited['i' - 'a'] = true;

		find(0, 0);

		System.out.println(result);

	}

	public static void find(int a, int count) {
		if (count == k) {
			int temp = 0;
			for (int i = 0; i < n; i++) {
				boolean flag = true; // 이게 true면 배울수 있는 단어
				
				// 익히지 않은 알파벳이 있는경우 체크
				for (int j = 0; j < word[i].length(); j++) {
					if (!visited[word[i].charAt(j) - 'a']) {
						flag = false; // 배울수 없는 단어 처리
						break;
					}
				}

				if (flag)
					temp++;
			}
			
			result = Math.max(result, temp);

			return;
		}

		for (int i = a; i < 26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				find(i, count + 1);
				visited[i] = false;
			}
		}
	}
}
