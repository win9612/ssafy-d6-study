package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 에너지 모으기
public class bj16198 {

    static int N;
    static ArrayList<Integer> W;
    static int max;

    public static void dfs(int sum){
        if(W.size() <= 2) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 1 ; i < W.size()-1 ; i++) {
            int tmpnow = W.get(i);
            int tmpsum = W.get(i-1) * W.get(i+1);
            W.remove(i); // i번째 위치 제거
            dfs(sum + tmpsum); // i-1번째와 i+1번째 곱해서 값에 더하기
            W.add(i, tmpnow); // 원래대로 되돌려놓기
            System.out.println(W.size());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            W.add(Integer.parseInt(st.nextToken()));
        }
        max = Integer.MIN_VALUE;
        dfs(0);
        System.out.println(max);
    }
}
