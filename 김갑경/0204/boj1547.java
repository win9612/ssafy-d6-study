import java.io.*;
import java.util.*;

// 백준 1547 공
// https://www.acmicpc.net/problem/1547
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int[] position = { 0, 1, 2, 3 }; // 컵의 위치를 저장할 배열, 인덱스: 컵 번호 / 값: 현재 위치

		for (int i = 0; i < m; i++) {
			// 입력부
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 서로 공 위치를 바꿔준다.
			int tmp = position[x];
			position[x] = position[y];
			position[y] = tmp;
		} // 컵 바꾸기 끝

		for (int i = 1; i <= 3; i++) {
			if (position[i] == 1) {
				System.out.println(i);
       				 return;
			}
		}
   		 // 이 문장은 빼도 됨
		// System.out.println("-1");
	} // end main
} // end class
