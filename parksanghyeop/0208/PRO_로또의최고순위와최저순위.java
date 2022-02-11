package programmers;

import java.util.Scanner;

public class PRO_로또의최고순위와최저순위 {
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		PRO_로또의최고순위와최저순위 sol = new PRO_로또의최고순위와최저순위();
		
		int[] lottos = new int[6];
		int[] win_nums = new int[6];
		int[] answer = new int[2];
		
		for(int i=0;i<6;i++)
			lottos[i] = sc.nextInt();
		for(int i=0;i<6;i++)
			win_nums[i] = sc.nextInt();
		
		answer = sol.solution(lottos, win_nums);
		
		System.out.printf("%d %d\n", answer[0], answer[1]);
		
		
	}
	
	public int[] solution(int[] lottos, int[] win_nums) {
        
        
        int zeroCount = 0;
        int matchCount = 0;
        
        int[] answer = {0, 0};
        
        for(int lotto : lottos) {
        	for(int j=0;j<6;j++) {
        		if(lotto==0) {
        			zeroCount++;
        			break;
        		} else {
        			if(lotto == win_nums[j])
        				matchCount++;
        		}
        	}
        }
        answer[0] = (7-(matchCount+zeroCount)<6) ? 7-(matchCount+zeroCount) : 6;
        answer[1] = (7-matchCount<6) ? 7-matchCount : 6;
        
        
        return answer;
    }
	
		
}
