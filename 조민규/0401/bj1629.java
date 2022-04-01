package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 곱셈
public class bj1629 {

    static long modular(long A, long B, long C){
        if(B == 0) return 1;
        if(B == 1) return A % C;

        B = modular(A, B/2, C);

        if(B%2 == 1) // 홀수
            return (((A%C) * (A%C) % C) * (A%C)) % C;
        else // 짝수
            return ((A%C) * (A%C)) % C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(modular(A, B, C));
    }
}
