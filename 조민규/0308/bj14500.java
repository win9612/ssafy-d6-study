package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 테트리미노
public class bj14500 {
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1 (민트)

        // 2 (주황)

        // 3 (초록)

        // 4 (분홍)

        // 5 (노랑)
    }

    // x, y : 현재 위치
    // bi : i++로 할것인가, bj : j++로 할것인가
    static void block1(int x, int y, int i, int j, boolean bi, boolean bj){
        int sum = 0;

        if(bi){
            if(bj){
                for(i = x ; i < x+3 ; i++){
                    for(int j = y ; j < y+1 )
                }
            }else{

            }
        }else{

        }

    }
}
