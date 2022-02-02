package 시뮬레이션;

import java.util.Arrays;
import java.util.Scanner;

public class Boj1547_공 {
	static boolean[] array = new boolean[4];
	static int M;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();

		array[1] = true;

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			if (array[a] || array[b]) { // 둘중에 하나라도 true라면
				array[a] = !array[a];
				array[b] = !array[b];
			}

		}

		for (int i = 1; i < 4; i++) {
			if (array[i])
				System.out.println(i);
		}
	}

}
