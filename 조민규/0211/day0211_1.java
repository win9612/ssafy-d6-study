import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1182 - 부분수열의 합
public class day0211_1 {
    static int N,S, ans;
    static int[] arr = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정수의 갯수
        S = Integer.parseInt(st.nextToken()); // 부분수열의 합이 S가 되는
        ans = 0;

        // 입력
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);
        if(S == 0){
            ans--;
        }
        System.out.println(ans);
    }

    static void dfs(int now, int sum){
        // 시작 파트
        if(now == N){
            if(sum == S){
                ans += 1;
            }
            return;
        }
        // 유도 파트
        dfs(now+1, sum+arr[now]);
        dfs(now+1, sum);
    }
}
