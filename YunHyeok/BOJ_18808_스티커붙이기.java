import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808_스티커붙이기 {
	static int N,M,K;
	static int[][] sticker, map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 가로 
		M = Integer.parseInt(st.nextToken()); // 세로
		K = Integer.parseInt(st.nextToken()); // 스티커의 개수
		
		map = new int[N][M];
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()); // 행
			int C = Integer.parseInt(st.nextToken()); // 열
			sticker = new int[R][C];

			for(int r=0; r<R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<C; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken());
				}
			}// 스티커 입력받기
			
			for(int i=0; i<4; i++) {
				if(check(sticker)) { // 빈구간이 있는지 체크하고 붙이기 까지 수행
					break; // 붙였으면 다음 스티커로 넘어가기
				}
				rotate(sticker); // 못붙였으면 90도 회전
			}
			
		}
		count(); // 스티커가 차지한 공간 카운트
	}
	
	static boolean check(int[][] sticker) {

		for(int i=0; i<N-sticker.length+1; i++) { // 스티커 인덱스 i시작
			for(int j=0; j<M-sticker[0].length+1; j++) { // 스티커 인덱스 j시작
				boolean attachFlag = true;
				for(int si=0; si<sticker.length; si++) {
					for(int sj=0; sj<sticker[0].length; sj++) {
						if(sticker[si][sj] == 1 && map[i+si][j+sj] == 1) {
							attachFlag = false;
						}
					}
				}
				
				if(attachFlag) { // 스티커 붙이기
					for(int si=0; si<sticker.length; si++) {
						for(int sj=0; sj<sticker[0].length; sj++) {
							if(map[i+si][j+sj] == 1) continue;
							map[i+si][j+sj] = sticker[si][sj];
						}
					}
					
					return true; // 스티커를 붙였다.
				}
				
			}
		}
		
		return false; // 못붙였다.
	}
	
	
	static void rotate(int[][] sticker2) { // 90도 돌리기
		sticker = new int[sticker2[0].length][sticker2.length];
		for(int i=0; i<sticker2[0].length; i++) {
			for(int j=sticker2.length-1; j>=0; j--) {
				sticker[i][sticker2.length-1-j] = sticker2[j][i];
			}
		}
		
	}
	
	static void count() { // 스티커 한칸한칸 세기
		int ans=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) ans++;
			}
		}
		System.out.println(ans);
	}
}
