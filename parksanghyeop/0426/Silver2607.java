package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Silver2607 {

	static Map<Character, Integer> first = new HashMap<>();
	static Map<Character, Integer> compare = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int count = 0;

		int n = Integer.parseInt(br.readLine()) - 1;
		String word = br.readLine();
		int len = word.length();

		init(first);
		for (int i = 0; i < len; i++) {
			char ch = word.charAt(i);
			first.replace(ch, first.get(ch) + 1);
		}

		for (int i = 0; i < n; i++) {
			init(compare);

			word = br.readLine();
			len = word.length();

			for (int j = 0; j < len; j++) {
				char ch = word.charAt(j);
				compare.replace(ch, compare.get(ch) + 1);
			}
			if (similar(compare))
				count++;
		}
		System.out.println(count);
	}

	static void init(Map<Character, Integer> temp) {
		for (char ch = 'A'; ch <= 'Z'; ch++)
			temp.put(ch, 0);
	}

	static boolean similar(Map<Character, Integer> temp) {
		int a, b;
		int diff = 0;
		int firstLen = 0, compareLen = 0;
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			firstLen += (a = first.get(ch));
			compareLen += (b = temp.get(ch));
			diff += Math.abs(a - b);
		}
		return diff <= 2 && Math.abs(firstLen - compareLen) <= 1;
	}
}
