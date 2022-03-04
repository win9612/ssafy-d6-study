import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14696_딱지놀이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 총 라운드 수

		for (int n = 0; n < N; n++) {
			int[] a = new int[5];
			int[] b = new int[5];

			int A = sc.nextInt();
			for (int i = 0; i < A; i++) {
				a[sc.nextInt()]++;
			}

			int B = sc.nextInt();
			for (int i = 0; i < B; i++) {
				b[sc.nextInt()]++;
			}

			String result = "";
			if (a[4] == b[4]) { // 별 개수 같음
				if (a[3] != b[3]) { // 동그라미 개수 다름
					result = a[3] > b[3] ? "A" : "B"; // a어린이의 동그라미 개수가 b어린이 동그라미 개수보다 많을 때
				} else {// 별 개수 같음 & 동그라미 개수 같음
					if (a[2] != b[2]) { // 네모 개수 다름
						result = a[2] > b[2] ? "A" : "B"; // a어린이의 네모 개수가 b어린이 네모 개수보다 많을 때
					} else { // 별 개수 같음 & 동그라미 개수 같음 & 네모 개수 같음
						if (a[1] != b[1]) { // 세모 개수 다름
							result = a[1] > b[1] ? "A" : "B"; // a어린이의 세모 개수가 b어린이 세모 개수보다 많을 때
						} else { // 별 개수 같음 & 동그라미 개수 같음 & 네모 개수 같음 & 세모 개수 같음
							result = "D";
						}
					}
				}
			} else { // 별 개수 다를 때
				result = a[4] > b[4] ? "A" : "B"; // a어린이의 별 개수가 b어린이 별 개수보다 많을 때
			}
			System.out.println(result);
		}
	}

}
