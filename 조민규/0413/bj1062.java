package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가르침
public class bj1062 {

    static String[] words;
    static boolean[] isUsed;
    static int N, K, max;

    public static int wordsCheck(){
        int count = 0;
        for(int i = 0 ; i < N ; i++){
            boolean able = true;
            int length = words[i].length();
            for(int j = 0 ; j < length ; j++){
                if(!isUsed[words[i].charAt(j) - 97]){
                    able = false;
                    break;
                }
            }
            if(able)
                count++;
        }
        return count;
    }

    public static void backtracking(int cnt, int start){
        if(cnt == K){
            max = Math.max(max, wordsCheck());
            return;
        }
        if(start == isUsed.length) return;

        int length = isUsed.length;
        for(int i = start ; i < length ; i++){
            if(!isUsed[i]){
                isUsed[i] = true;
                backtracking(cnt+1, i);
                isUsed[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K < 5){
            System.out.println(0);
            return;
        }
        words = new String[N];
        for(int i = 0 ; i < N ; i++){
            words[i] = br.readLine();
            words[i] = words[i].substring(4, words[i].length()-4);
        }

        isUsed = new boolean[26]; // 아스키코드 : '알파벳' - 97
        isUsed['a' - 97] = true;
        isUsed['n' - 97] = true;
        isUsed['t' - 97] = true;
        isUsed['i' - 97] = true;
        isUsed['c' - 97] = true;
        K -= 5;

        max = Integer.MIN_VALUE;
        backtracking(0,0);
        System.out.println(max);
    }
}
