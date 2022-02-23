import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13300_방배정 {
	static int N, K, female[], male[], count[], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken()); // 수학여행 참가하는 학생 수
		K = stoi(st.nextToken()); // 한 방에 배정가능한 최대 인원 수

		female = new int[7]; // 1~6학년 여학생 수, 0인덱스는 비워놓기
		male = new int[7]; // 1~6학년 남학생 수 
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int S = stoi(st.nextToken());
			int Y = stoi(st.nextToken());
			if(S == 0) { // 0은 여학생
				female[Y]++;
			}else { // 1은 남학생
				male[Y]++;
			}
		}
		
		int sumF = 0; // 여학생 배정하기 위한 방 수
		int sum = 0; // 남학생 배정하기 위한 방 수
		for(int i=1; i<7; i++) {
			sumF += sum(female[i]);
			sum += sum(male[i]);			
		}
		System.out.println(sum+sumF);
		
	}
	
	public static int sum(int n) {
		cnt = 0;
		cnt += n/K; //학생수를 나눈 몫 저장
		if(n % K != 0) { // 나머지가 있으면 +1
			cnt++;
		}
		return cnt;
	}

	public static int stoi(String s) { // String->int
		return Integer.parseInt(s);
	}
}
