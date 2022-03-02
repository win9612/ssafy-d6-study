import java.util.Scanner;

public class BOJ_1120_문자열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();

		int aLen = A.length();
		int bLen = B.length();
		int cnt = 0;

		int min = bLen; // 많이 틀려도 B의 갯수를 넘지 않으니까
		for (int i = 0; i < bLen - aLen + 1; i++) { // 길이차이 + 1만큼 for문을 돌기
			cnt = 0;
			for (int j = 0; j < aLen; j++)  // A의 길이만큼 for문 돌기
				if (A.charAt(j) != B.charAt(i + j))cnt++;
			min = Math.min(min, cnt);
		}
		System.out.println(min);
	}

}
