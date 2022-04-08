import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {
	static int size = 9;
	static boolean[] visited; // 방문배열
	static int[][] gameList; // 이닝이 담기는 배열
	static int[] orderList; // 타순배열
	static int N; // 총 이닝
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		gameList = new int[N][size]; //  게임 시작을 위한 선수명단 1과 4는 바뀐 상태에서 시작
		visited = new boolean[size]; // 방문체크를 위한 리스트
		orderList = new int[size]; // 순서저장하는 리스트
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()); // 선수명단 입력받기
			for(int j=0; j<size; j++) {
				gameList[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		orderList[3] = 0; //4번 타자를 1번 타자에 고정
		visited[3] = true; // 4번 선수는 순서가 정해짐
		
		dfs(1);
		System.out.println(max);
		
	}
	
	static void dfs(int start) {
		if(start == 9) {
			// 이닝 진행
			int score = play();
			max = Math.max(max, score);
			return;
		}
		
		
		for(int i=0; i<9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				orderList[i] = start;
				dfs(start+1);
				visited[i] = false;
			}
		}
	}

	static int play() {
		int pIdx=0; // 타석
		int score = 0; // 총 득점 수
		
		for(int i=0; i<N; i++) { // 총 N이닝 반복
			boolean[] base = new boolean[3]; // 1루, 2루, 3루 이닝 마다 초기화?
			int outCnt = 0; // 아웃 카운트
			inning : while(true) { // 3아웃 될때까지..
				for(int j=pIdx; j<9; j++) {
					int player = gameList[i][orderList[j]];
					
					if(player == 0) { // 아웃
						outCnt++;
					}else if(player == 1) { // 1루타
						for(int b=2; b>=0; b--) {
							if(base[b]) {
								if(b==2) {
									base[b] = false; // 3루가 홈으로 감
									score++; // 1점 올려주고
									continue;
								}
								base[b+1] = true; // 베이스 한칸씩 이동
								base[b] = false; 
							}
						}
						base[0] = true; // 1루타 쳤으면 1루로 가기
					}else if(player == 2) { // 2루타
						for(int b=2; b>=0; b--) {
							if(base[b]) {
								if(b==1 || b==2) {
									base[b] = false; // 2, 3루가 홈으로 감
									score++; // 1점 올려주고
									continue;
								}
								base[b+2] = true; // 베이스 두칸씩 이동
								base[b] = false; 
							}
						}
						base[1] = true; // 2루타 쳤으면 2루로 가기
					}else if(player == 3) { // 3루타
						for(int b=2; b>=0; b--) {
							if(base[b]) {
								base[b] = false; // 1, 2, 3루가 홈으로 감
								score++; // 1점 올려주고
							}
						}
						base[2] = true; // 3루타 쳤으면 3루로 가기
					}else { // 홈런
						for(int b=2; b>=0; b--) {
							if(base[b]) {
								base[b] = false; // 1, 2, 3루가 홈으로 감
								score++; // 1점 올려주고
							}
						}
						score++; // 0루에 있던 사람 홈런
					}
					if(outCnt == 3) {
						pIdx = j+1; // 다음 타자 저장하기
						if(pIdx == 9) pIdx = 0;
						break inning;
					}
				}
				pIdx = 0; // 무한 반복문 방지 아웃이 없는경우?
			}
		}
		
		return score;
	}
	
}
