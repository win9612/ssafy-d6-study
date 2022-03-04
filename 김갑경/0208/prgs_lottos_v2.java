import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/77484?language=java
// 시간 최적화 
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        int li = 0;
        int wi = 0;

        int zero = 0;
        int same = 0;
        while(li < 6 && wi < 6){
            if(lottos[li] == 0){
                zero++;
                li++;
            } else if(lottos[li] == win_nums[wi]){
                li++;
                wi++;
                same++;
            } else if(lottos[li] < win_nums[wi])
                li++;
            else
                wi++;
        }

        int low = Math.min(7 - same, 6);
        int high = Math.max(low - zero, 1);

        int[] answer = {high, low};
        return answer;
    }
}
