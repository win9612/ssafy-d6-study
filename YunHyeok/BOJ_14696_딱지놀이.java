import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class BOJ_14696_딱지놀이 {
	static int N;
	static Integer[] aList;
	static Integer[] bList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 총 라운드 수 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int aLen = Integer.parseInt(st.nextToken()); // A가 내는 딱지에 나온 그림의 총 개수
			
			aList = new Integer[aLen];
			for(int a=0; a<aLen; a++) { // 개수 만큼 리스트에 넣어주기
				aList[a] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			int bLen = Integer.parseInt(st.nextToken()); // B가 내는 딱지에 나온 그림의 총 개수
			
			bList = new Integer[bLen];
			for(int b=0; b<bLen; b++) { // 개수 만큼 리스트에 넣어주기
				bList[b] = Integer.parseInt(st.nextToken());
			}
			
			// 내림차순 정렬하기
			Arrays.sort(aList, Collections.reverseOrder());
			Arrays.sort(bList, Collections.reverseOrder());
			
			boolean flag = false;
			for(int j=0; j<Math.min(aLen, bLen); j++) { // 더 적은 그림을 가진 개수만큼
				if(aList[j] > bList[j]) { // A가 이기면
					sb.append("A" + "\n");
					flag = true;
					break;
				}else if(aList[j] == bList[j]) { // 무승부
					continue;
				}else {// B가 이기면
					sb.append("B" + "\n");
					flag = true;
					break;
				}
			}
			
			if(!flag) { // 끝내 무승부인 경우와 그림개수가 가 더 많은 쪽이 승리하는 경우
				if(aLen == bLen) {
					sb.append("D" + "\n");
				}else {
					if(aLen > bLen) {
						sb.append("A" + "\n");
					}else {
						sb.append("B" + "\n");
					}
				}
			}			
			
		}
		
		System.out.println(sb);
		
	}

}
