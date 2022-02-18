import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815_숫자카드__재귀 {
	static int N, numbers[], M;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine()); // 상근이가 가지고 있는 숫자 카드 개수
		numbers = new int[N];
		StringTokenizer line = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			numbers[n] = Integer.parseInt(line.nextToken());
		}
		Arrays.sort(numbers); // 먼저 상근이가 가지고 있는 숫자 카드 정렬

		M = Integer.parseInt(br.readLine()); // 숫자카드인지 아닌지 구해야하는 M개 정수
		line = new StringTokenizer(br.readLine());

		for (int m = 0; m < M; m++) {
			compare(Integer.parseInt(line.nextToken()), 0, N - 1);
		}
		System.out.println(sb.toString());

	}

	public static void compare(int num, int start, int end) {
		if (start > end) {
			sb.append("0 ");
			return;
		}
		int mid = (start + end) / 2; // 중간값
		if (num == numbers[mid]) { // input값이 numbers의 중간값과 같다면
			sb.append("1 ");
		} else if (num < numbers[mid]) {
			compare(num, start, mid - 1);
		} else if (num > numbers[mid]) {
			compare(num, mid + 1, end);
		}
	}

}
