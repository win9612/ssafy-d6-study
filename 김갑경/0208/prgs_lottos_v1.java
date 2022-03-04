import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/77484?language=java
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zero = 0;
        int same = 0;
        
        for(int i = 0; i < 6; i++){
            if(lottos[i] == 0){
                zero++;
                continue;
            }
            for(int j = 0; j < 6; j++)
                if(lottos[i] == win_nums[j])
                    same++;
        }
        
        int low = Math.min(7 - same, 6);
        int high = Math.max(low - zero, 1);
        
        int[] answer = {high, low};
        return answer;
    }
}
