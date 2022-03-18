import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20364_부동산다툼 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 땅 개수
		int Q = Integer.parseInt(st.nextToken()); // 오리 수
		boolean[] visited = new boolean[N + 1];

		for (int i = 0; i < Q; i++) {
			int n = Integer.parseInt(br.readLine()); // 오리가 원하는 땅 번호
			visited[n] = true; // 오리가 원하는 땅이기 때문에 먼저 방문 처리
			int ans = 0;
			while (n > 0) { // 1번 땅부터 시작하니까 0보다 클때까지
				n = n / 2;
				if (visited[n]) { //2로 나눈 몫의 땅 번호가 이미 방문처리가 되어 있으면 그 번호 출력
					ans = n;
					break;
				}
			}
			System.out.println(ans);
		}
	}

}
