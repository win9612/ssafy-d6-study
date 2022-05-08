package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Silver16198 {

	static int N, result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		ArrayList<Integer> beads = new ArrayList<>();
		ArrayList<Boolean> selected = new ArrayList<>();

		for (int i = 0; i < N; i++)
			selected.add(false);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			beads.add(Integer.parseInt(st.nextToken()));
		}
		int value = 0;
		find(beads, value, selected);
		
		System.out.println(result);

	}

	static void find(ArrayList<Integer> beads, int value, ArrayList<Boolean> selected) {

		int len = beads.size();

		if (len == 2) { // 첫번째와 마지막 구슬만 남았다면
			result = Math.max(result, value);
			return;
		}

		for (int i = 1; i < len - 1; i++) {

//			System.out.println(i+"] "+beads.toString() + " " + result + " " + value);
			ArrayList<Integer> tempBeads = new ArrayList<>();
			ArrayList<Boolean> tempSelected = new ArrayList<>();

			selected.set(i, true);
			int tempValue = value + beads.get(i - 1) * beads.get(i + 1);

			for (int temp : beads)
				tempBeads.add(temp);

			for (boolean temp : selected)
				tempSelected.add(temp);

			tempBeads.remove(i);
			tempSelected.remove(i);

			find(tempBeads, tempValue, tempSelected);
			selected.set(i, false);

		}

	}

}
