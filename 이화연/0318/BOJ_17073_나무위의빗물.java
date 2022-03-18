import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17073_나무위의빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		List<Integer>[] list = new ArrayList[N + 1]; // 루트가 1부터

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}

		int cnt = 0; // 자식 없는 노드 개수 
		for (int i = 2; i <= N; i++) { // 루트의 사이즈가 1인경우가 있기 때문에 2부터 시작해야 함
			if (list[i].size() == 1) { // 사이즈가 1인 것은 자식이 없는 것
				cnt++;
			}
		}
		System.out.printf("%.10f", (double) W / cnt);
		System.out.println();

	}

}
