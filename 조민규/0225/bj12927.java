package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 배수 스위치
public class bj12927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] status = ("N" + br.readLine()).toCharArray(); // 0번째 인덱스는 꺼져있는것으로 가정
        int N = status.length; // 배열의 길이
        int ans = 0; // 전구를 끄는 데 필요한 횟수 카운트
        boolean allN = true;

        // 애초에 모두 N이었는지 검사하고, 그럴경우 0 출력 후 종료
        for(int i = 0; i < N ; i++){
            if(status[i] == 'Y') allN = false;
        }
        if(allN){
            System.out.println(0);
            return;
        }

        for(int i = 1 ; i < N ; i++){
            if(status[i] == 'Y'){ // 현재 검사하는 전구가 켜진 전구일 경우
                for(int j = i ; j < N ; j += i){ // 현재 전구와 배수의 전구들을 다 반전시킨다.
                    if(status[j] == 'Y'){ // 전구가 켜져 있을 경우
                        status[j] = 'N'; // 꺼버린다
                    }
                    else { // 전구가 꺼져 있을 경우
                        //more = true; // 배수를 반전시키는 과정에서 꺼진게 켜져버릴 경우 체크
                        status[j] = 'Y'; // 켜버린다
                    }
                }
                ans++;
                //if(!more) break; // 더 전구를 볼 필요가 없을 경우 break
            }
        }
        System.out.println(ans);
    }
}
