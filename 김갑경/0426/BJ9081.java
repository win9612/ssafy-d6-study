import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/9081

public class BJ9081 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			char[] arr = br.readLine().toCharArray();

			next_permutation(arr);

			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		}
	}

	private static void next_permutation(char[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i] <= arr[i - 1])
			i--;

		if (i <= 0)
			return;

		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1])
			j--;

		swap(i - 1, j, arr);
		j = arr.length - 1;

		while (i < j) {
			swap(i, j, arr);
			i++;
			j--;
		}
	}

	private static void swap(int i, int j, char[] arr) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
