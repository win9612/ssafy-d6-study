package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Silver9081 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			System.out.println(find(br.readLine()));
		}

	}

	static String find(String s) {
		
		// 길이가 1이면 바로 리턴
		if (s.length() == 1)
			return s;


		int index = 0;
		int min = 0;
		// 오름차순이 깨지는곳을 찾는다
		loop: for (index = s.length() - 2; index >= 0; index--) {
			
			// idx보다 큰거 확인
			for (min = s.length() - 1; min > index; min--) {
				if (s.charAt(index) < s.charAt(min))
					break loop;
			}
		}

		// 완전한 오름차순인 경우 내 뒤에 아무것도 없다
		// 그니까 나 자신 리턴
		if (index == -1)
			return s;

		char[] arr = s.toCharArray();

		char temp = arr[min];
		arr[min] = arr[index];
		arr[index] = temp;

		Arrays.sort(arr, index + 1, arr.length);

		StringBuilder sb = new StringBuilder();
		for (char c : arr)
			sb.append(c);

		return sb.toString();
	}
}
