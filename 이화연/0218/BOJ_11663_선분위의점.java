import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11663_선분위의점 {
	static int N, M, point[], start, end, xIdx, yIdx, mid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer line = new StringTokenizer(br.readLine());

		N = Integer.parseInt(line.nextToken());
		M = Integer.parseInt(line.nextToken());

		line = new StringTokenizer(br.readLine());

		point = new int[N];
		for (int n = 0; n < N; n++) {
			point[n] = Integer.parseInt(line.nextToken());
		}
		Arrays.sort(point); // 정렬

		for (int m = 0; m < M; m++) {

			line = new StringTokenizer(br.readLine());

			count(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())); // 시작점, 끝점
			System.out.println(yIdx - xIdx + 1); // 개수세려면 +1
		}

	}

	public static void count(int x, int y) {
		start = 0;
		end = N - 1;
		while (start <= end) {
			mid = (start + end) / 2; // 중간값

			if (x == point[mid]) {
				xIdx = mid; // 중간값이랑 일치하면 xIdx에 중간값인덱스 넣기
				break;
			} else if (x < point[mid]) {
				end = mid - 1;
			} else if (x > point[mid]) {
				start = mid + 1;
			}
		}
		if (start > end) { // 같은 값을 아예 찾지 못했을 때
			xIdx = start; // 시작점이니까 start
		}

		start = 0;
		end = N - 1;
		while (start <= end) {
			mid = (start + end) / 2;

			if (y == point[mid]) {
				yIdx = mid; // 중간값이랑 일치하면 yIdx에 중간값인덱스 넣기
				break;
			} else if (y < point[mid]) {
				end = mid - 1;
			} else if (y > point[mid]) {
				start = mid + 1;
			}
		}
		if (start > end) { // 같은 값을 아예 찾지 못했을 때
			yIdx = end; // 끝점이니까 end
		}
	}

}
