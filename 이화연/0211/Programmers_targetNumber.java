/* 깊이/너비 우선탐색 : 타겟 넘버 */

public class Programmers_targetNumber {
	static int target, size, count, answer;

	public static int solution(int[] numbers, int target) {

		size = numbers.length;
		answer = 0; // 타겟넘버 만드는 방법의 수

		dfs(numbers, 0, 0, target);

		return answer;
	}

	public static void dfs(int[] numbers, int idx, int sum, int target) {
		if (idx == size) { // 다 돌았을때
			if (sum == target) {
				answer++;
			}
			return;
		}

		dfs(numbers, idx + 1, sum + numbers[idx], target); //더할 때
		dfs(numbers, idx + 1, sum - numbers[idx], target); //뺄 때
	}

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };

		System.out.println(solution(numbers, 3));

	}

}
