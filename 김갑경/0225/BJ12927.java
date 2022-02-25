import java.io.*;

// 배수 스위치 - https://www.acmicpc.net/problem/12927
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		boolean[] on = new boolean[s.length()];
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'Y')
				on[i] = true;
		}

		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if (!on[i])
				continue;

			cnt++;
			for (int j = i; j < s.length(); j += (i + 1)) {
				on[j] = !on[j];
			}
		}
		System.out.println(cnt);
	}
}
