import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9081_단어맞추기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			char[] word = br.readLine().toCharArray();

			int idx = -1;
			for (int i = word.length - 1; i > 0; i--) {
				if (word[i - 1] < word[i]) { // i가 i-1보다 크면 위치변경해줘야 함
					idx = i - 1;
					break;
				}
			}
			if (idx == -1) { // idx가 바뀌지 않았으면 마지막단어라는 것
				for (int i = 0; i < word.length; i++) {
					sb.append(word[i]);
				}
				sb.append("\n");
			} else {
				int idx2 = 0;
				for (int i = word.length - 1; i >= 0; i--) { // 뒤에서부터
					if (word[idx] < word[i]) { // 위치 변경해줘야 하는 알파벳보다 클때
						idx2 = i;
						break;
					}
				}
				char temp = word[idx]; // 위치 변경
				word[idx] = word[idx2];
				word[idx2] = temp;

				Arrays.sort(word, idx + 1, word.length); // idx+1부터 끝까지 정렬
				for (int i = 0; i < word.length; i++) {
					sb.append(word[i]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
