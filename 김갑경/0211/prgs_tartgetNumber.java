class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int l = numbers.length;
        
        for (int i = 0; i < Math.pow(2, l); i++) {
			String s = Integer.toBinaryString(i);
			int sum = 0;

			for (int j = 0; j < l - s.length(); j++)
				sum += numbers[j];

			for (int j = l - s.length(); j < l; j++) {
				if (s.charAt(j - l + s.length()) == '0')
					sum += numbers[j];
				else
					sum -= numbers[j];
			}
			if (sum == target)
				answer++;
		}
        
        return answer;
    }
}
