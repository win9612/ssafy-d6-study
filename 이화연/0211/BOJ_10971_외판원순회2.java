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
		visited = new boolean[N]; // 방문 확인
		min = Integer.MAX_VALUE;

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
			for (int i = 0; i < result.length; i++) {
				if (i == (result.length - 1)) {
					if (city[result[result.length - 1]][result[0]] != 0) {
						// i가 마지막 인덱스일때는 처음으로 다시 가야하기 때문에 city[3][0]을 더해줘야 함
						sum += city[result[result.length - 1]][result[0]];
					} else { // 갈 수 없을때
						return;
					}
				} else {
					if (city[result[i]][result[i + 1]] != 0) {
						// 뽑힌 result 순서에 따라 도시 이동
						// 예를 들어 result = {0,1,2,3} 일때
						// city[0][1] -> city[1][2] -> city[2][3] 이렇게
						sum += city[result[i]][result[i + 1]];
					} else {
						return;
					}
				}
			}
//			System.out.println(sum);
			min = Math.min(min, sum);
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;

			result[cnt] = i;
			visited[i] = true;

			permu(cnt + 1);
			visited[i] = false;

		}
	}

}
