package day0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629_곱셈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());

		System.out.println(pow(A, B, C));
	}

	public static long pow(long A, long B, long C) {
		if (B == 0) {
			return 1;
		}
		if (B == 1) {
			return A % C;
		}

		long num = pow(A, B / 2, C);

		if (B % 2 == 1) { // B가 홀수이면 A를 한번 더 곱해야 함
			return (num * num % C) * A % C;
		} else { // B가 짝수이면 num만 두번 곱하기
			return num * num % C;
		}
	}

}
