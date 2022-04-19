import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9084_동전 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] coin = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}

			int M = Integer.parseInt(br.readLine());
			int[] D = new int[M + 1];
			D[0] = 1; 
      
			for (int i = 0; i < N; i++) {
				for (int j = coin[i]; j <= M; j++) { // 동전 금액부터 시작, 현재 동전 금액보다 작은 건 어차피 안됨
					D[j] += D[j - coin[i]];
				}
			}
			System.out.println(D[M]);

		}
	}

}
