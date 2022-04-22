package Day0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon9084_동전 {
	static int TC, N, money;
	static int[] input;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO 동전
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			input = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			money = Integer.parseInt(br.readLine());
			dp = new int[money+1]; // 입력받은 돈의 크기+1로 배열을 생성
			// 초기 값들 설정
			
			
			dp[0] = 1;
			
			// dp [j] = dp [j] + dp [j - coin]   // 이전까지의 최적해 + 현재 코인을 뺀 값의 최적해 
			for(int each : input) {
				for(int j=each; j<=money; j++) {
					dp[j] += dp[j-each];
				}
			}
			
			System.out.println(dp[money]);
			
		}

	}

}
