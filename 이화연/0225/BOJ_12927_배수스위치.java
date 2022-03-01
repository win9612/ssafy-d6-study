import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12927_배수스위치 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] light = br.readLine().toCharArray();
		int cnt = 0; // 스위치 눌러야 하는 횟수
		for (int i = 0; i < light.length; i++) {
			if (light[i] == 'N') { // 꺼져 있으면 넘어가
				continue;
			} else { // 켜져 있을 떄
				for (int j = i; j < light.length; j++) { // i의 배수번호를 가지는 전구 찾기
					if ((j + 1) % (i + 1) == 0) { // 스위치가 1번부터이기 때문에 +1을 해주고 나머지가 0이면 배수
						if (light[j] == 'Y')
							light[j] = 'N'; // 켜져 있으면 반대로 꺼주고
						else
							light[j] = 'Y'; // 꺼져 있으면 반대로 켜준다
					}
				}
			}
			cnt++; // 횟수 증가
		}
		System.out.println(cnt);
	}

}
