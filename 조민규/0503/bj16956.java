package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 늑대와 양
public class bj16956 {

    static class Animal{
        int x,y;

        public Animal(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R,C;
    static char[][] map;
    static Queue<Animal> wolves;

    static int[] di = {-1,0,1,0}; // 상우하좌
    static int[] dj = {0,1,0,-1}; // 상우하좌

    public static boolean isValid(int nexti, int nextj){
        return (nexti>=0 && nexti<R && nextj>=0 && nextj<C);
    }

    public static int bfs(){
        while(!wolves.isEmpty()){
            Animal wolf = wolves.poll();

            for(int d = 0 ; d < 4 ; d++){
                int nexti = wolf.x + di[d];
                int nextj = wolf.y + dj[d];
                if(isValid(nexti, nextj)){
                    if(map[nexti][nexti] == 'S'){
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        wolves = new LinkedList<>();

        for(int i = 0 ; i < R ; i++){
            String tmp = br.readLine();
            for(int j = 0 ; j < C ; j++){
                map[i][j] = tmp.charAt(j);
                if(map[i][j] == 'W'){
                    wolves.add(new Animal(i,j));
                }
            }
        }

        int result = bfs();
        System.out.println(result);
        if(result == 1){
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C ; j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }
}
