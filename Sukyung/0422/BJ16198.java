package day0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ16198 {
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> beads = new ArrayList<Integer>();
		answer = Integer.MIN_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			beads.add(Integer.parseInt(st.nextToken()));
		}
		getEnergy(0, beads);
		System.out.println(answer);
	}

	public static void getEnergy(int energy, ArrayList<Integer> beads) {
		if (beads.size() == 2) {
			answer = Math.max(answer, energy);
			return;
		}

		for (int i = 1; i < beads.size() - 1; i++) { // 첫 번째와 마지막 구슬 제외
			ArrayList<Integer> copy = new ArrayList<Integer>();
			for (Integer b : beads) { // 복사
				copy.add(new Integer(b));
			}
			int energySum = energy + beads.get(i - 1) * beads.get(i + 1); // 에너지 모으기
			copy.remove(i); // x번째 구슬 삭제
			getEnergy(energySum, copy); // 재귀 사용
		}
	}
}
