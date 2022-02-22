import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1780_종이의개수 {
	static int N, map[][], count[];
	static List<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		count = new int[3]; // -1, 0, 1의 개수를 담을 배열

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		paper(0, 0, N); // 0,0부터 시작

		System.out.println(count[0]);
		System.out.println(count[1]);
		System.out.println(count[2]);

	}

	public static boolean check(int i, int j, int size) { // 종이가 같은 수로 되어있는지 확인
		for (int x = i; x < i + size; x++) {
			for (int y = j; y < j + size; y++) {
				if (map[x][y] != map[i][j])
					return false;
			}
		}
		return true;
	}

	public static void paper(int i, int j, int N) {
		if (check(i, j, N)) {
			if (map[i][j] == -1) { // -1이면 0인덱스 ++
				count[0]++;
			} else if (map[i][j] == 0) { // 0이면 1인덱스 ++
				count[1]++;
			} else { // 1이면 2인덱스++
				count[2]++;
			}
			return;
		}

		int size = N / 3; // 9개의 영역으로 나눠야 되니까 3으로 나눔
		int ii = i + size;
		int jj = j + size;

		// 9개 영역
		// i : 1~3(i), 4~6(i+size), 7~9(i+size+size)
		// j : 1,4,7(j), 2,5,8(j+size), 3,6,9(j+size+size)
		paper(i, j, size); // 1
		paper(i, jj, size); // 2
		paper(i, jj + size, size); // 3
		paper(ii, j, size); // 4
		paper(ii, jj, size); // 5
		paper(ii, jj + size, size); // 6
		paper(ii + size, j, size); // 7
		paper(ii + size, jj, size); // 8
		paper(ii + size, jj + size, size); // 9
	}
}
