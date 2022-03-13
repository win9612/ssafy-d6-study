package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부동산 다툼
public class bj20364 {

    static int N, Q; // 땅 개수, 오리 수
    static boolean[] visited;

    // 목적지부터 반대로 탐색
    // idx : 현재 위치, start : 시작 위치, firstMeet : 처음 마주친 점유지
    public static void dfs(int idx, int start, int firstMeet){
        // 루트 땅까지 도착
        if(idx == 1){
            if(firstMeet == 0) // 중간에 마주친 점유지가 없을 경우
                visited[start] = true;// 이 땅은 이제 주인이 있다
            System.out.println(firstMeet);
            return;
        }

        // 점유지를 지나갈 경우
        if(visited[idx]){
            firstMeet = idx;
        }

        dfs(idx/2, start, firstMeet);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 땅 개수
        Q = Integer.parseInt(st.nextToken()); // 오리 수
        visited = new boolean[N+1];
        for(int i = 0 ; i < Q ; i++){
            int input = Integer.parseInt(br.readLine());
            dfs(input, input, 0);
        }
    }
}
