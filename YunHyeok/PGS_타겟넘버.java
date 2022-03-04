// 재귀풀이
class Solution {
    static int cnt=0;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return cnt;
    }
    
    static void dfs(int idx, int sum, int[] numbers, int target){
        if(idx == numbers.length){
            if(sum == target) cnt++;
            return;
        }

        dfs(idx+1, sum + numbers[idx], numbers, target);
        dfs(idx+1, sum - numbers[idx], numbers, target);
    }
}

// bfs풀이
import java.util.*;
class Solution {
    static Queue<int[]> q = new LinkedList<>();
    public int solution(int[] numbers, int target) {
        int answer = 0;
        q.offer(new int[] {numbers[0], 0}); // '+' 로 시작
        q.offer(new int[] {-1 * numbers[0], 0}); // '-' 로 시작
        
        while(!q.isEmpty()){
            int num = q.peek()[0];
            int idx = q.peek()[1];
            q.poll();
            idx += 1;
            
            if(idx < numbers.length){ 
                q.offer(new int[] {num + numbers[idx], idx});
                q.offer(new int[] {num + (-1 * numbers[idx]), idx});
            }
            
            if(idx == numbers.length){
                if(num == target) answer++;
            }
        }
        
        return answer;
    }
}

