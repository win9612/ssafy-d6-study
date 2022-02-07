package 프로그래머스;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PGS_문자열압축 {
	static String s = "aabbaccc";
	static String s0 = "abcdaaaa";
	static String s1 = "ababcdcdababcdcd";
	static String s2 = "abcabcdede";
	static String s3 = "abcabcabcabcdededededede";
	static String s4 = "xababcdcdababcdcd";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = s;
		int unit = str.length()/2; // 단위 나누기
		int min = str.length(); // 최소값 저장
		
		for(int i=1; i<= unit; i++) {
			StringBuilder list = new StringBuilder();
			int cnt = 1; // 압축가능 카운트
			String strA = str.substring(0, i); //a aa aab aabb
			
			for(int j=i; j<=str.length(); j+=i) {
				String strB = ""; // 비교할 대상 저장할 변수
				if(j+i > str.length()) { // #1. 범위를 초과하면 strB에 공백이 담긴다. 
					strB = str.substring(j, str.length()); 
				}else { // 범위안의 경우
					strB = str.substring(j, j+i); // 범
				}
				
//				System.out.println("j="+j +" " + strB); //#1
				
				if(strA.equals(strB)) { // 문자열이 같은 경우 
					cnt+=1;
				}else { // 문자열이 다른 경우, 압축 가능한 수를 셌던 cnt로 다시 체크
					if(cnt>1) { // 1이상인 경우, aa -> 2a 로 바꿔준다.
						list.append(cnt + strA);
					}else {
						list.append(strA);
					}
					strA = strB; // 압축할 다음 문자 strA에 넣기
					cnt=1; // 초기화!!
				}
			}//압축 for문
			
			list.append(strA); // #2. 마지막에 sb에 추가되지 않는 경우가 있다
//			System.out.println(i + "단위일때 문자열 "+list ); //#2 
			min = Math.min(min, list.length());
			
		}// unit for문
		System.out.println(min);
	}

}
