package day0208;

import java.util.Arrays;

public class programmers1 {
    static public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {7, 7};

        for(int i = 0 ; i < lottos.length ; i++){
            int tmp = lottos[i];
            if(Arrays.stream(win_nums).anyMatch(k -> k == tmp)){
                answer[0] -= 1;
                answer[1] -= 1;
            }
            if(lottos[i] == 0){ // 0
                answer[0] -= 1;
            }
        }

        for(int i = 0 ; i < answer.length ; i++){
            if(answer[i] == 7) answer[i] = 6;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] a = {44,1,0,0,31,25}; // 예시1 - 내 번호
        int[] b = {31,10,45,1,6,19}; // 예시2 - 정답 번호
        int[] c = solution(a, b);
        System.out.print(c[0] + " ");
        System.out.print(c[1]);
        System.out.println();
    }
}
