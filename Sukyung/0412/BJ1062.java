package day0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1062 {
	static int N, K, answer;
	static Character[][] words;
	static boolean[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new Character[N][15];
		answer = Integer.MIN_VALUE;
		alphabet = new boolean[26];

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			for (int j = 4; j < word.length() - 4; j++) {
				words[i][j - 4] = word.charAt(j);
			}
		}
		alphabet['a' - 'a'] = true;
		alphabet['c' - 'a'] = true;
		alphabet['i' - 'a'] = true;
		alphabet['t' - 'a'] = true;
		alphabet['n' - 'a'] = true;
		dfs(0, 0);
		System.out.println(answer == Integer.MIN_VALUE ? 0 : answer);
	}

	public static void dfs(int start, int count) {
		if (count == K - 5) {
			int readWords = 0;
			int wordLength = 0;

			for (int i = 0; i < N; i++) {
				int alphabetCount = 0;

				for (int j = 0; j < 15; j++) {
					if (words[i][j] == null) {
						wordLength = j;
						break;
					}
					if (alphabet[words[i][j] - 'a'])
						alphabetCount++;
				}
				if (alphabetCount == wordLength) {
					readWords++;
				}
			}
			answer = Math.max(answer, readWords);
			return;
		}

		for (int i = start; i < 26; i++) {
			if (!alphabet[i]) {
				alphabet[i] = true;
				dfs(i + 1, count + 1);
				alphabet[i] = false;
			}
		}
	}
}
