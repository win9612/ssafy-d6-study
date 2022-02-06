/* Level 1 : 키패드 누르기 */

public class Keypad {
	public static String solution(int[] numbers, String hand) {
		String answer = "";
		int[][] keypad = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 0, 10 } }; // keypad
		int Li = 3, Lj = 0, Ri = 3, Rj = 2;

		for (int n = 0; n < numbers.length; n++) {
			for (int i = 0; i < keypad.length; i++) {
				for (int j = 0; j < (keypad.length - 1); j++) {
					if (keypad[i][j] == numbers[n]) {
						if (j == 0) { // 1,4,7
							Li = i;
							Lj = j;
							answer += "L";
							break;
						} else if (j == 2) { // 3,6,9
							Ri = i;
							Rj = j;
							answer += "R";
							break;
						} else { // 2,5,8,0
							int distanceL = Math.abs(i - Li) + Math.abs(j - Lj); // 왼손에서 현재 키패드까지 거리
							int distanceR = Math.abs(i - Ri) + Math.abs(j - Rj); // 오른손에서 현재 키패드까지 거리

							if (distanceL < distanceR) { // 왼손 거리가 더 짧을 때
								Li = i;
								Lj = j;
								answer += "L";
							} else if (distanceL > distanceR) {
								Ri = i;
								Rj = j;
								answer += "R";
							} else { // 거리 같을 때
								if (hand.equals("right")) {
									Ri = i;
									Rj = j;
									answer += "R";
								} else {
									Li = i;
									Lj = j;
									answer += "L";
								}
							}

						}
					}
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] numbers = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		String hand = "right";

		System.out.println(solution(numbers, hand));

		int[] numbers2 = { 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 };
		System.out.println(solution(numbers2, "left"));
	}

}
