package Day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon14696_딱지놀이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO 딱지놀이
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			int[] countA = new int[5];
			int[] countB = new int[5];
			// A 어린이 count
			String str1 = br.readLine();
			StringTokenizer st1 = new StringTokenizer(str1, " ");
			int k1 = Integer.parseInt(st1.nextToken());
			for (int j = 0; j < k1; j++) {
				int a = Integer.parseInt(st1.nextToken());
				if (a == 4) {
					countA[4]++;
				} else if (a == 3) {
					countA[3]++;
				} else if (a == 2) {
					countA[2]++;
				} else {
					countA[1]++;
				}
			}
			
			// B 어린이 count
			String str2 = br.readLine();
			StringTokenizer st2 = new StringTokenizer(str2, " ");
			int k2 = Integer.parseInt(st2.nextToken());
			for (int j = 0; j < k2; j++) {
				int a = Integer.parseInt(st2.nextToken());
				if (a == 4) {
					countB[4]++;
				} else if (a == 3) {
					countB[3]++;
				} else if (a == 2) {
					countB[2]++;
				} else {
					countB[1]++;
				}
			}


			if (countA[4] > countB[4])
				System.out.println("A");
			else if (countA[4] < countB[4])
				System.out.println("B");
			else {
				if (countA[3] > countB[3])
					System.out.println("A");
				else if (countA[3] < countB[3])
					System.out.println("B");
				else {
					if (countA[2] > countB[2])
						System.out.println("A");
					else if (countA[2] < countB[2])
						System.out.println("B");
					else {
						if (countA[1] > countB[1])
							System.out.println("A");
						else if (countA[1] < countB[1])
							System.out.println("B");
						else {
							System.out.println("D");
						}
					}
				}
			}

		}

	}

}
