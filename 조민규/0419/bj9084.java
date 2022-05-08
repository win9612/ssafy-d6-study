package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전
public class bj9084 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void test() throws IOException {
        // 입력
        int N = Integer.parseInt(br.readLine()); // 동전의 가짓수
        int[] coin = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            coin[i] = Integer.parseInt(st.nextToken());
        }
        int money = Integer.parseInt(br.readLine());
        int[] D = new int[money + 1];

        for(int i = 0 ; i < N ; i++){
            for(int j = 1 ; j <= money ; j++) {
                if (j > coin[i]) {
                    D[j] += D[j - coin[i]];
                } else if (j == coin[i]) {
                    D[j] += 1;
                }
            }
        }
        System.out.println(D[money]);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            test();
        }
    }
}
