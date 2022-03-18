package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 봄버맨
public class bj16918 {

    static class Bomb{
        int startTime, x, y; // 설치시간, 좌표

        public Bomb(int startTime, int x, int y) {
            this.startTime = startTime;
            this.x = x;
            this.y = y;
        }

        public void peong(){
            //System.out.println("BOMB ["+x+","+y+"]");
            for(int d = 0 ; d < 4 ; d++){
                try{
                    if(map[x][y] == 'O' && visited[x+di[d]][y+dj[d]]!=startTime){ // 현재 폭탄이 다른 폭탄에 의해 터지지 않았고, 연쇄반응 지점에 폭탄이 없을경우
                        map[x+di[d]][y+dj[d]] = '.';
                        visited[x+di[d]][y+dj[d]] = -1;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
            map[x][y] = '.';
            visited[x][y] = -1;
        }
    }

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int R,C,N;
    static char[][] map;
    static int[][] visited; // 연쇄반응 시 모든 폭탄이 터질 수 있게 하도록 만드는 장치
    static Queue<Bomb> queue = new LinkedList<>(); // 현재 존재하는 폭탄들을 담은 큐

    public static void showMap(char[][] map){
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void showBoolean(int[][] b){
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                System.out.print(b[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void play(int time){
        if(time == N+1) return;
        //System.out.println("Time : " + time);

        if(time%2==0){ // 2초마다 새로운 폭탄을 설치
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C ; j++){
                    if(map[i][j] == '.'){
                        map[i][j] = 'O';
                        visited[i][j] = time;
                        //System.out.println("["+i+","+j+"]에 폭탄 설치");
                        queue.add(new Bomb(time, i, j));
                    }
                }
            }
        }

        while(queue.peek() != null && time-queue.peek().startTime >= 3){ // 3초된 폭탄이 다 나올때까지 진행
            queue.poll().peong();
        }



//        System.out.println("======= time "+time+" =======");
//        showMap(map);
//        System.out.println();
//        showBoolean(visited);
//        System.out.println();
        play(++time);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new int[R][C];
        for(int r = 0 ; r < R ; r++){
            String str = br.readLine();
            for(int c = 0 ; c < C ; c++){
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'O'){
                    visited[r][c] = 0;
                    queue.add(new Bomb(0, r, c));
                }
            }
        }
        play(2);
        showMap(map);
    }
}
