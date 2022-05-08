import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어 {
	static int mapNo[][], mapD[][], eat, sharkD;
	static int di[] = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	static int dj[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static Point fish[], shark;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		mapNo = new int[4][4]; // 각 칸 물고기 번호
		mapD = new int[4][4]; // 각 칸 물고기 방향
		fish = new Point[17]; // 1<= 물고기 <=16

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				mapNo[i][j] = Integer.parseInt(st.nextToken());
				fish[mapNo[i][j]] = new Point(i, j); // 해당 물고기 번호에 맞게 좌표 설정
				mapD[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}

		eat = 0;

		// 처음에 상어 0,0칸으로 변경
		eat += mapNo[0][0];
		mapNo[0][0] = 0;
		shark = new Point(0, 0);
		sharkD = mapD[0][0];

		while (true) {
			moveFish();

			print();

			// 상어가 바라보는 방향에서 가장 큰 값을 먹을것이다..
			int max = -1;
			boolean flag = false;
			int nexti = shark.x;
			int nextj = shark.y;
			
			//이때 제일 큰 값이 아니라 bfs로 하나씩 다해봐야하는건가??????/???????????
			while (true) {
				System.out.println(nexti + "," + nextj);
				nexti += di[sharkD];
				nextj += dj[sharkD];
				if (nexti < 0 || nexti >= 4 || nextj < 0 || nextj >= 4) {
					break;
				}
				if (max < mapNo[nexti][nextj] && mapNo[nexti][nextj] != 0) { // 제일 큰 값을 찾고, 물고기가 없는 칸으로 안가기
					max = mapNo[nexti][nextj];
					shark = new Point(nexti, nextj);
					flag = true;
				}
			}
			System.out.println("상어 :" + shark.x + "," + shark.y);
			sharkD = mapD[shark.x][shark.y]; // 방향 현재 상어위치 좌표로 바꾸기
			eat += mapNo[shark.x][shark.y]; // 먹은 물고기 더해주기
			mapNo[shark.x][shark.y] = 0; // 상어 있는 자리 0으로 갱신

			if (!flag) { // 더이상 상어가 이동할 수 없으면 집으로
				System.out.println(eat);
				return;
			}
			print();
		}

	}

	public static void moveFish() { // 물고기 번호 작은 순서대로 이동하기
		for (int i = 1; i < 17; i++) {

			int nowi = fish[i].x;
			int nowj = fish[i].y;
			int nowD = mapD[nowi][nowj];
//			System.out.println("물고기 " + i + "번 : " + nowi + "," + nowj + " 방향 : " + nowD);

			if (mapNo[nowi][nowj] == 0) { // 해당 번호 칸에 상어가 지나가서 물고기 먹으면 0이됨 그떄는 다음 번호로
				continue;
			}

			int cnt = 0; // 회전 횟수
			while (cnt <= 7) { // 다 돌면서 확인해도 이동할 수 없을때까지?
				int nexti = nowi + di[nowD];
				int nextj = nowj + dj[nowD];

				// 유효한 칸이고 상어 칸이 아닐때
				if (nexti >= 0 && nexti < 4 && nextj >= 0 && nextj < 4 && !(nexti == shark.x && nextj == shark.y)) {
//					System.out.println("next " + nexti + "," + nextj);
					fish[mapNo[nowi][nowj]] = new Point(nexti, nextj); // 물고기 위치 갱신
					fish[mapNo[nexti][nextj]] = new Point(nowi, nowj);

					int tmp = mapNo[nexti][nextj];
					int tmpD = mapD[nexti][nextj];
					mapNo[nexti][nextj] = mapNo[nowi][nowj];
					mapD[nexti][nextj] = mapD[nowi][nowj];
					mapNo[nowi][nowj] = tmp;
					mapD[nowi][nowj] = tmpD;

					break;
				}
				nowD = (nowD + 1) % 8; // 45도 반시계 회전해서 이동 확인
				cnt++;
			}

//			System.out.println("=================");
		}
	}

	public static void print() {
		for (int[] mm : mapNo) {
			for (int m : mm) {
				System.out.print(m + " ");
			}
			System.out.println();
		}
	}

}
