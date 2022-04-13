package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 긴 부분 수열
public class bj11053 {
    static int N;
    static int[] arr;
    static int[] lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // lis
        for(int i = 0 ; i < N ; i++){
            lis[i] = 1;
            for(int j = 0 ; j < i ; j++){
                if( (arr[j] < arr[i]) && (lis[i] < 1 + lis[j]) ){
                    lis[i] = lis[j] + 1;
                }
            }
        }
        Arrays.sort(lis);
        System.out.println(lis[N-1]);
    }
}
