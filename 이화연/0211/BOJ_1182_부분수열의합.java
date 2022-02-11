import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의합 {
	static int N, S, count, sum;
	static int[] array;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		count = 0; // 부분수열 개수

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		if (S == 0) { // 부분집합은 공집합도 0으로 인식하기 때문에 미리 빼준다..
			count = -1;
		}
		
		array = new int[N]; //저장할 배열
		visited = new boolean[N]; //방문확인할 배열

		StringTokenizer line = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(line.nextToken());
		}

		subset(0);
		
		System.out.println(count);

	}

	public static void subset(int cnt) {
		if (cnt == N) {
			sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					sum += array[i];
				}
			}
			// 부분집합의 원소를 더한 값이 S랑 같을때만 count 증가
			if (sum == S) {
				count++;
			}
			return;
		}

		// 현재 원소 선택했을 때
		visited[cnt] = true;
		subset(cnt + 1);

		// 현재 원소 선택 안했을 때
		visited[cnt] = false;
		subset(cnt + 1);

	}

}
