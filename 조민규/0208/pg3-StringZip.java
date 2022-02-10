package day0208;

import java.util.ArrayList;
import java.util.Collections;

public class programmers2 {
    public static int solution(String s){
        int answer = 0;
        ArrayList<Integer> answerList = new ArrayList<>();

        for(int i = 1 ; i < s.length()+1 ; i++){
            System.out.println(i + "개 단위로 자를 경우");
            int current_position = 0;
            int banbok = 1;
            String tmp = "";
            StringBuilder new_s = new StringBuilder("");

            while(current_position < s.length()){
                try{
                    tmp = s.substring(current_position, current_position + i);
                }catch(StringIndexOutOfBoundsException e){
                    tmp = s.substring(current_position, s.length());
                }
                
                if(current_position == 0){
                    new_s.append(String.valueOf(banbok));
                    new_s.append(tmp);
                    current_position += i;
                    continue;
                }
                if(tmp.equals(s.substring(current_position-i, current_position))){ // 같은 문자열이 반복되는 상황이면
                    banbok += 1;
                    // new_s String을 char형 배열로 변환
                    new_s.setCharAt(0, (char)(banbok + '0'));

                }
                else {
                    banbok = 1;
                    new_s.append(String.valueOf(banbok));
                    new_s.append(tmp);
                    //System.out.println("New S : " + new_s);
                }
                current_position += i;
            }

            // 안에 써 있는 1을 모두 '' 로 변경

            //System.out.println("길이는? " + new_s.length());
            answerList.add(new_s.length());
        }

        answer = Collections.min(answerList);
        return answer;
    }
    public static void main(String[] args) {
        String s = "abcabcabcabcdededededede";

        System.out.println(solution(s));
    }
}
