package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 움직이는 미로 탈출
public class bj16954 {

    static class Player{
        int x,y;
        public Player(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final static int N = 8;
    static boolean success = false;
    static char[][] map = new char[N][N];
    Queue<Player> q = new LinkedList<>();

    static int[] di = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] dj = {-1, -1, -1, 0, 0, 0, 1, 1, 1};

    // 맵 상태를 출력해보기 위한 임시 함수
    public static void show(){
        for(int i = 0 ; i < N ; i++){
            System.out.print(i + " > ");
            for(int j = 0 ; j < N ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i < N ; i++){
            map[i] = br.readLine().toCharArray();
        }
        Player player = new Player(7, 0);

        bfs();
    }

    static void bfs(){
        // (0,7)에 도착하면 success를 true로 바꾸고 리턴

        //

    }
}
