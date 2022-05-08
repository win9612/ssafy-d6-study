package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 단어 맞추기
public class bj9081 {

    static int W, N;
    static char[] input;

    public static void swap(int i, int j){
        char tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    private static boolean np(){
        int i = N-1;
        while(i>0 && input[i-1] >= input[i]){
            --i;
        }

        if(i==0) return false; // 교환위치 못 찾으면 false 리턴

        int j = N-1;
        while(input[i-1] >= input[j]){
            --j;
        }

        swap(i-1, j);

        int k = N-1;
        while(i<k){
            swap(i++, k--);
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        W = Integer.parseInt(br.readLine()); // 단어의 갯수

        for(int i = 0 ; i < W ; i++){
            input = br.readLine().toCharArray();
            String first = new String(input); // 처음 입력 당시 단어
            N = input.length; // 단어의 길이

            // Next Permutation
            if(np()){
                System.out.println(new String(input));
            } else {
                System.out.println(first);
            }
        }
    }
}
