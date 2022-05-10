package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 로봇청소기
public class bj14503 {
    static int N, M;
    static int R, C, D;
    static int ans;
    static int[][] map;

    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    public static void show(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isValid(int nexti, int nextj){
        return nexti>=0 && nexti<N && nextj>=0 && nextj<M && map[nexti][nextj]==0;
    }

    public static void dfs(int i, int j, int dir){

        // 1. 현재 위치를 청소한다.
        map[i][j] = -1;
        //show();

        // 2. 현재 위치에서 인접한 칸 탐색
        for(int d = 0 ; d < 4 ; d++){
            dir = (dir+3) % 4;
            int nexti = i + di[dir];
            int nextj = j + dj[dir];

            // a-1. 현재 위치의 바로 왼쪽에 청소하지 않은 빈 공간

            if(isValid(nexti, nextj)){
                ans++;
                dfs(nexti, nextj, dir);
                return;
            }
        }

        // 인접한 4방향 모두 청소된 상태일 때
        int backDir = (dir+2) % 4;
        int ni = i + di[backDir];
        int nj = j + dj[backDir];

        if(ni>=0 && ni<N && nj>=0 && nj<M && map[ni][nj] != 1){
            dfs(ni,nj,dir);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 맵 크기 설정
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 로봇 초기위치, 방향
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 맵 정보 설정
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 1;
        dfs(R,C,D);
        System.out.println(ans);
    }
}
