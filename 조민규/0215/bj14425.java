package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 문자열 집합
public class bj14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 집합 S 입력
        Set<String> S = new HashSet<>();
        for(int n = 0 ; n < N ; n++){
            S.add(br.readLine());
        }

        // M개의 문자열을 입력받으면서 S에 포함되는지 검사
        int ans = 0;
        for(int m = 0 ; m < M ; m++){
            if(S.contains(br.readLine())) ans++;
        }

        // 결과 출력
        System.out.println(ans);
    }
}
