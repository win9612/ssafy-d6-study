import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
	static int L, C;
	static String[] array, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken()); // 선택해야하는 문자 수
		C = Integer.parseInt(st.nextToken()); // 입력으로 들어오는 문자 수

		array = new String[C];
		result = new String[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			array[i] = st.nextToken();
		}

		Arrays.sort(array); // 알파벳 순서대로 정렬

		combi(0, 0);
	}

	public static void combi(int cnt, int start) {
		if (cnt == L) { // 선택해야 하는 문자 수 다 골랐을때
			int cntM = 0; // 모음 셀 변수
			int cntJ = 0; // 자음 셀 변수
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i]);
				if (result[i].equals("a") || result[i].equals("e") || result[i].equals("i") || result[i].equals("o")
						|| result[i].equals("u")) { // 모음일때
					cntM++;
				} else { // 자음일때
					cntJ++;
				}
			}
			if (cntM < 1 || cntJ < 2) { // 모음이 1개도 없고 자음이 2개미만일때는 출력 X
				return;
			} else {
				System.out.println(sb.toString());
			}
			return;
		}

		for (int i = start; i < C; i++) {
			result[cnt] = array[i];
			combi(cnt + 1, i + 1);

		}
	}
}
