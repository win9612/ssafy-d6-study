import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808_스티커붙이기 {
	static int N, M, K, R, C, map[][], ans;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노트북 행
		M = Integer.parseInt(st.nextToken()); // 노트북 열
		K = Integer.parseInt(st.nextToken()); // 스티커 개수
		visited = new boolean[N][M]; // 스티커 붙여져있는지 체크

		ans = 0; // 스티커 붙일 수 없을때로 초기화
		for (int k = 0; k < K; k++) { // 스티커 개수만큼 붙일수 있는지 없는지 확인
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // 스티커 행
			C = Integer.parseInt(st.nextToken()); // 스티커 열

			map = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < 4; r++) { // 회전 4번
				if (findLocation(map.length, map[0].length, map)) { // 스티커 붙이기 성공
					break;
				} else { // 스티커 붙이기 실패, 회전해서 다시
					map = rotation(map);
				}
			}
		}

		for (boolean[] bb : visited) { // 스티커 붙인 영역 세기
			for (boolean b : bb) {
				if (b)
					ans++;
			}
		}
		System.out.println(ans);
	}

	public static boolean findLocation(int r, int c, int[][] map) {
		if (r <= N && c <= M) {
			for (int i = 0; i <= N - r; i++) {
				for (int j = 0; j <= M - c; j++) {
					if (!check(i, j, r, c, map)) { // 스티커 붙일 수 없으면
						continue; // 다음 자리 탐색
					} else { // 스티커 붙일 수 있음
						sticker(i, j, r, c, map); // 스티커 붙이기
						return true;
					}
				}
			}
		}
		return false; // 자리 모두 탐색했는데도 스티커 붙일 수 없음
	}

	// 스티커를 붙일 수 있는지 없는지 확인
	public static boolean check(int start_i, int start_j, int r, int c, int[][] map) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 1 && visited[i + start_i][j + start_j]) { // 스티커가 붙어있는 칸인데 이미 방문처리 됐으면 붙일 수 없음
					return false;
				}
			}
		}
		return true;
	}

	// 행렬 회전
	public static int[][] rotation(int[][] old_map) {
		int R = old_map.length; // 회전하기 전 행
		int C = old_map[0].length; // 회전하기 전 열
		int[][] rotationMap = new int[C][R]; // 행,열 바꿔서 생성
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				rotationMap[j][R - 1 - i] = old_map[i][j];
			}
		}
		return rotationMap;
	}

	// 스티커 붙이기
	public static void sticker(int start_i, int start_j, int r, int c, int[][] map) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 1) {
					visited[i + start_i][j + start_j] = true;
				}
			}
		}
	}

}
