package 문자열;

import java.util.Scanner;


public class BOJ_2607_비슷한단어 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt()-1;
		
		int[] alpha = new int[26];
		String str1 = sc.next();
		int len = str1.length(); // 첫번째 입력받은 문자 길이
		for(int s=0; s<len; s++) {
			alpha[str1.charAt(s)-'A']++;
		}
		
		int ans = 0; // 결과 저장
		while(N-- > 0) {
			int[] arr = alpha.clone(); // 깊은 복사
			String str = sc.next(); // 비교할 문자
			
			int cnt = 0;
			for(int i=0; i<str.length(); i++) {
				if(arr[str.charAt(i)-'A'] > 0) {
					cnt++;
					arr[str.charAt(i)-'A']--;
				}
			}
			
			int comp = len - str.length();
			if(comp == 0 && (cnt == len || cnt == len-1)) { // 같을때
				ans++;
			}else if(comp == -1 && cnt == len) { // 한글자 많을때
				ans++;
			}else if(comp == 1 && cnt == len-1) { // 한글자 적을때
				ans++;
			}
		}
		System.out.println(ans);

	}

}
