package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문자열
public class bj1120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N0 = str[0].length(); // 작은 문자열의 길이
        int N1 = str[1].length(); // 긴 문자열의 길이
        int maxValue = 0;

        // 현재 0이랑 1의 유사도를 브루트포스로 판별한다.
        for(int i = 0 ; i <= N1-N0 ; i++){
            int tmp = 0;
            for(int j = i ; j < i+N0 ; j++){
                if(str[0].charAt(j-i) == str[1].charAt(j)){
                    tmp++;
                }
            }
            maxValue = Math.max(maxValue, tmp); // 가장 높은 유사도를 maxValue로 처리한다.
        }

        int ans = N0 - maxValue;
        System.out.println(ans);
    }
}
