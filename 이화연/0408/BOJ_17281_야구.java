import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {
	static boolean[] visited;
	static int order[], N, map[][], outCnt, home[], max, score;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 이닝 수
		map = new int[N][9]; // 이닝에서 얻는 결과
		visited = new boolean[9];
		order = new int[9]; // 타순
		home = new int[9]; // 각 타자가 몇 루에 있는지

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = -1;

		permu(0);
		System.out.println(max);
	}

	public static void permu(int cnt) { // 타자 순서를 정한다
		if (cnt == 9) {
			if (order[3] != 0) // 4번 타자는 이미 0으로 정해져 있음
				return;
			inning(order);
			return;
		}
    
		for (int i = 0; i < 9; i++) {
			if (visited[i])
				continue;

			order[cnt] = i;
			visited[i] = true;
			permu(cnt + 1);
			visited[i] = false;
		}
	}

	public static void inning(int[] order) { // 이닝
		int idx = 0; // 몇번째 타순인지
		score = 0; // 득점 초기화
		Arrays.fill(home, 0); // 루 배열 초기화
    
		for (int i = 0; i < N; i++) { // N번 반복
      
			while (true) {
				if (outCnt == 3) { // 3 아웃이면 현재 이닝 종료
					outCnt = 0; // 다음 이닝을 위해 아웃 초기화
					Arrays.fill(home, 0); // 이닝이 다시 시작될 때 주자는 없기 때문에 초기화
					break;
				}
        
				if (idx >= 9) { // 3 아웃 아니고 1~9번 순서 타자까지 다 쳤을 때 다시 첫번째 순서 타자부터 공격
					idx = 0;
				}
        
				int num = map[i][order[idx]]; // N번째 이닝에서 order순서대로 공격을 함
				switch (num) { // 각 선수가 N번째 이닝에서 얻는 결과에 따라 나눠짐
				case 0: // 아웃 : 모든 주자 진루 못함, 아웃 하나 증가
					outCnt++;
					break;
				case 1: // 안타 : 타자와 모든 주자가 한 루씩 진루
					go(1, order[idx]);
					break;
				case 2: // 2루타 : 타자와 모든 주자가 두 루씩 진루
					go(2, order[idx]);
					break;
				case 3: // 3루타 : 타자와 모든 주자가 세 루씩 진루
					go(3, order[idx]);
					break;
				case 4: // 홈런 : 타자와 모든 주자가 홈까지 진루
					int homerun = 0;
					for (int j = 0; j < 9; j++) {
						if (home[j] != 0) { // 현재 타자가 0이 아니면 1 or 2 or 3루에 있는 것
							homerun++; // 홈까지 진루할 선수 세기
							home[j] = 0; // 해당 선수 리셋
						}
					}
					score += (homerun + 1); // 현재 공을 친 타자포함해서 홈까지 진루할 선수를 득점에 더해줌
					break;
				}
        
				idx++;
			}
			max = Math.max(score, max);
		}
	}

	public static void go(int go, int player) {
		for (int i = 0; i < 9; i++) {
			if (home[i] != 0) { // 각 타자가 현재 있는 위치가 1 or 2 or 3루일 경우
				home[i] += go; // go값에 따라 진루해야함
				if (home[i] > 3) { // 만약 진루하고 나서 3보다 커진다면 홈까지 진루했으니
					score++; // 득점 증가
					home[i] = 0; // 해당 타자 위치 리셋
				}
			}
		}
		home[player] += go; // 공을 친 타자도 진루
	}

}
