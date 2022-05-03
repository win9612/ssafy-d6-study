package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gold16719 {

	static String str;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		visited = new boolean[str.length()];

		find(0, str.length() - 1);

	}

	static void find(int start, int end) {

		if (start > end)
			return;

		int index = end;

		for (int i = start; i <= end; i++) {
			if (str.charAt(index) > str.charAt(i))
				index = i;
		}

		visited[index] = true;

		for (int i = 0; i < str.length(); i++) {
			if (visited[i])
				System.out.print(str.charAt(i));
		}
		System.out.println();

		find(index + 1, end);
		find(start, index - 1);

	}
}
