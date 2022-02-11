import java.util.Arrays;
import java.util.Scanner;

public class BOJ_10971_외판원순회2 {
	static int N, min;
	static int[][] city;
	static int[] numbers, result;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 도시 수
		city = new int[N][N]; // 비용 행렬
		result = new int[N]; // 순열 저장할 배열
		numbers = new int[N]; // 도시 1,2,3,4
		visited = new boolean[N]; // 방문 확인
		min = Integer.MAX_VALUE;

		int num = 0;
		for (int i = 0; i < N; i++) {
			numbers[i] = num++;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				city[i][j] = sc.nextInt();
			}
		}

		permu(0);

		System.out.println(min);
	}

	public static void permu(int cnt) {
		if (cnt == N) {
			int sum = 0;
//			System.out.println(Arrays.toString(result));
			for (int i = 0; i < result.length - 1; i++) {
				for (int j = i + 1; j <= i + 1; j++) {
					if (city[result[i]][result[j]] == 0) { // 갈 수 없을 때 빠져나오기..?
						break;
					}
					// 뽑힌 result 순서에 따라 도시 이동
					// 예를 들어 result = {0,1,2,3} 일때
					// city[0][1] -> city[1][2] -> city[2][3] 이렇게..?
					sum += city[result[i]][result[j]];
				}
			}
			min = Math.min(min, sum);
//			System.out.println(min);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;

			result[cnt] = numbers[i];
			visited[i] = true;

			permu(cnt + 1);
			visited[i] = false;

		}
	}

}
