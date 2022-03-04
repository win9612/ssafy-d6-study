import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	static int N, map[][], min;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // N명

		map = new int[N][N];
		visited = new boolean[N];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);
		System.out.println(min);
	}

	public static void comb(int idx, int cnt) {
		if (cnt == N / 2) { // N/2명 골랐을 때 리턴
			ArrayList<Integer> start = new ArrayList<Integer>();
			ArrayList<Integer> link = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				if (visited[i]) { // 선택한 번호는 start에 넣고
					start.add(i);
				} else { // 선택하지 않은 번호는 link에 넣기
					link.add(i);
				}
			}
			min = Math.min(min, Math.abs(add(start) - add(link)));
			return;
		}

		if (idx == N) {
			return;
		}

		visited[idx] = true;
		comb(idx + 1, cnt + 1);
		visited[idx] = false;
		comb(idx + 1, cnt);
	}

	public static int add(ArrayList<Integer> list) {
		int sum = 0;
		for (int i = 0; i < list.size() - 1; i++) { // ij, ji 능력치 더하기
			for (int j = i + 1; j < list.size(); j++) {
				sum += map[list.get(i)][list.get(j)] + map[list.get(j)][list.get(i)];
			}
		}
		return sum;
	}

}
