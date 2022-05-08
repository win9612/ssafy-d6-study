package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 감시 피하기
public class bj18428 {

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N; // 맵 크기
    static char[][] map; // 맵
    static boolean result; // Yes or No
    static ArrayList<Point> teachers; // 선생님들 위치

    static ArrayList<Point> combInput; // 조합에 쓸 빈 공간 위치들
    static Point[] combSelected; // 장애물 설치 위치

    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teachers = new ArrayList<>();
        combInput = new ArrayList<>();
        combSelected = new Point[3];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'T')
                    teachers.add(new Point(i,j));
                else if(map[i][j] == 'X')
                    combInput.add(new Point(i,j));
            }
        }

        result = false;
        combination(0,0);

        if(result) System.out.println("YES");
        else System.out.println("NO");
    }

    public static boolean isValid(int nexti, int nextj){
        return (nexti>=0 && nexti<N && nextj>=0 && nextj<N && map[nexti][nextj]!='O');
    }

    public static void simulation(){
        for(Point teacher : teachers){
            for(int d = 0 ; d < 4 ; d++){
                int nexti = teacher.x + di[d];
                int nextj = teacher.y + dj[d];
                while(isValid(nexti, nextj)){
                    if(map[nexti][nextj] == 'S'){ // 학생이 있으면
                        return; // 시뮬레이션 종료
                    }
                    nexti += di[d];
                    nextj += dj[d];
                }
            }
        }
        result = true; // 모든 감시를 다 통과하면 true
    }

    public static void combination(int cnt, int start){
        if(cnt == 3){
            for(int i = 0 ; i < 3 ; i++){
                int puti = combSelected[i].x;
                int putj = combSelected[i].y;
                map[puti][putj] = 'O';
            }
            simulation();
            for(int i = 0 ; i < 3 ; i++){
                int puti = combSelected[i].x;
                int putj = combSelected[i].y;
                map[puti][putj] = 'X';
            }
            return;
        }

        int size = combInput.size();
        for(int i = start ; i < size ; i++){
            combSelected[cnt] = combInput.get(i);
            combination(cnt+1, i+1);
        }
    }
}
