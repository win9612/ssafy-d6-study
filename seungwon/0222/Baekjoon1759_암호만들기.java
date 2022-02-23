package Day0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1759_암호만들기 {
	static int L, C;
	static String[] input, result;
	static String[] jaum = { "a", "e", "i", "o", "u" };

	public static void main(String[] args) throws IOException {
		// 암호 만들기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		StringTokenizer st = new StringTokenizer(str, " ");

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		input = new String[C];
		result = new String[C];

		String str2 = br.readLine();
		StringTokenizer st2 = new StringTokenizer(str2, " ");
		// Input 입력받기
		for (int i = 0; i < C; i++) {
			input[i] = st2.nextToken();
		}

		Arrays.sort(input);
		Combination(0, 0);

	}
	
	static void Combination(int idx, int start) {
		// 기저부분
		if(idx == L) {
			int jaum_count = 0;
			for(String a : jaum) {
				for(String b : result) {
					if(a.equals(b)) jaum_count++;
				}
			}
			
			if(jaum_count<1 || L-2<jaum_count) return;
			
			for(int i=0; i<L; i++) {
				System.out.print(result[i]);
			}
			System.out.println();
			return;
		}
			
		for(int i = start;i<C;i++) {
			result[idx] = input[i];
			Combination(idx+1, i+1);
		}
	
	}

}
