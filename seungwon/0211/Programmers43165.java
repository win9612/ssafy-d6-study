package day0208_0211;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers43165 {
	static int answer;
	//static int[] numbers = {4, 1, 2, 1};
	static int[] numbers = {1, 1, 1, 1, 1};
	static int target;
    // idx:몇 번째 인덱스인지, sum: idx까지 누적된 값
    static void dfs(int idx,int sum){
        // 모든 정수를 탐색했을 때,
        if(idx == numbers.length){   
            // 누적합이 target과 동일하면 answer++ 후 메소드 종료.
            if(sum == target) answer++;
            return;
        }
        
        // 현재 인덱스의 정수를 더한다.
        sum+=numbers[idx];
        // 다음 인덱스 dfs 수행.
        dfs(idx+1,sum);
        // 더해준 값을 다시 빼준 뒤,
        sum-=numbers[idx];
        // 현재 인덱스의 정수를 -로 합해준다.
        sum+=(-1 * numbers[idx]);
        // 다시 다음 인덱스 dfs 수행.
        dfs(idx+1,sum);
        
    }
    


	public static void main(String[] args) {
		// TODO 타켓넘버 BFS

		//target = 4;
		target = 3;
		int size = numbers.length;
		
		// answer은 static
        answer = 0;
        
        // dfs수행.
        dfs(0,0);
        
        System.out.println(answer);
		
	}


}


