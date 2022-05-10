package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 트리의 지름
public class bj1967 {
    static int N;
    static int[][] adjMatrix;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N+1][N+1];
        ans = 0;

        // 간선 정보 입력
        for(int i = 0 ; i < N-1 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjMatrix[parent][child] = adjMatrix[child][parent] = weight;
        }

        for(int i = 1; i <= N ; i++){
            // 리프노드인지 아닌지 검사
            boolean flag = false;
            for(int j = i ; j <= N ; j++){
                if(adjMatrix[i][j] != 0)
                    flag = true;
            }
            // 리프노드라면
            if(!flag){
                int cnt = 0; // 지름
                int now = i; // 현재 노드
                // 리프 ~ 루트
                while(now != 1){
                    for(int j = 1 ; j < i ; j++){
                        if(adjMatrix[i][j] != 0){
                            now = j;
                            cnt++;
                            break;
                        }
                    }
                }
                // 루트 ~ 반대쪽 리프
                // 인접행렬로는 dfs로 여기를 구할 방법이 없다....
            }
        }
    }
}
