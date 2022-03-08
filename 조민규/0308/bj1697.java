package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 숨바꼭질 - bfs
public class bj1697 {
    static int N, K;
    static int result = 0;
    static boolean[] visited;

    static void bfs(int N){
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        //int level = 0;

        while(!q.isEmpty()){
            int tmp = q.poll();
            visited[tmp] = true;
            System.out.println("tmp : " + tmp);

            if(tmp == K){
                System.out.println("도달했습니다");
                break;
            }

            int[] next = {tmp-1, tmp+1, tmp*2};
            for(int i = 0 ; i < 3 ; i++){
                if(next[i] > 0 && next[i] < K*2 && !visited[next[i]]){
                    q.add(next[i]);
                    result++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[K*2];

        if(N == K){
            System.out.println(0);
            return;
        }
        bfs(N);
        System.out.println(result);
    }
}
