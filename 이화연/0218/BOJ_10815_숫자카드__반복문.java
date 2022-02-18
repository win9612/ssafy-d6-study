import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815_숫자카드__반복문 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 상근이가 가지고 있는 숫자 카드 개수
		int[] numbers = new int[N];
		StringTokenizer line = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			numbers[n] = Integer.parseInt(line.nextToken());
		}
		Arrays.sort(numbers); // 먼저 상근이가 가지고 있는 숫자 카드 정렬

		int M = Integer.parseInt(br.readLine()); // 숫자카드인지 아닌지 구해야하는 M개 정수
		int[] result = new int[M]; // 결과 저장할 배열
		line = new StringTokenizer(br.readLine());

		for (int m = 0; m < M; m++) {
			int num = Integer.parseInt(line.nextToken());
			int start = 0; // 시작
			int end = N - 1; // 끝
			while (start <= end) {
				int mid = (start + end) / 2; // 중간값
				if (num == numbers[mid]) { // input값이 numbers의 중간값과 같다면
					result[m] = 1; // 가지고 있으니까 1
				} else if (num < numbers[mid]) {
					end = mid - 1; // 중간값보다 작으면 중간값 아래에서 비교
				} else if (num > numbers[mid]) {
					start = mid + 1; // 중간값보다 크면 중간값 위에서 비교
				}
			}
			System.out.print(result[m] + " ");
		}
	}
}
