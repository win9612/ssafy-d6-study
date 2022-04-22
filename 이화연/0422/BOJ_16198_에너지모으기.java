import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_16198_에너지모으기 {
	static int N, result[], max;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구슬 개수

		list = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		max = 0;// 최댓값
		dfs(0);
		System.out.println(max);
	}

	public static void dfs(int sum) {
		if (list.size() == 2) { // 첫번째, 마지막 구슬만 남으면
			max = Math.max(sum, max);
			return;
		}

		for (int i = 1; i < list.size() - 1; i++) {
			int temp = list.get(i);
			int energy = list.get(i - 1) * list.get(i + 1); // 앞, 뒤 구슬 곱하기
			list.remove(i);
			dfs(sum + energy);
			list.add(i, temp); // 다음 경우의 수를 위해 다시 더해줌
		}

	}

}
