package day0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2607 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] alphabet = new int[26];
		String standard = br.readLine();
		int answer = 0;
		
		for (int i = 0; i < standard.length(); i++) {
			alphabet[standard.charAt(i) - 'A']++;
		}
		for (int i = 1; i < n; i++) {
			String word = br.readLine();
			
			if (Math.abs(standard.length() - word.length()) > 1)
				continue;

			int[] alphabet2 = new int[26];
			for (int j = 0; j < word.length(); j++) {
				alphabet2[word.charAt(j) - 'A']++;
			}
			
			int count = 0;
			boolean same = true;
			for (int j = 0; j < 26; j++) {
				if (alphabet[j] != alphabet2[j]) {
					if (Math.abs(alphabet[j] - alphabet2[j]) == 1) {
						if (count > 1) {
							same = false;
							break;
						}
						count++;
					} else {
						same = false;
						break;
					}
				}
			}
			if (standard.length() == word.length()) {
				if (same && count <= 2)
					answer++;
			} else {
				if (same && count < 2)
					answer++;
			}
		}
		System.out.println(answer);
	}
}
