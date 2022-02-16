package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1074 {
    static int N, r, c;
    static int result = 0;
    //static long beforeTime, afterTime;

    public static void search(int startI, int endI, int startJ, int endJ){
        // 나눠질대로 다 나눠지면
        if((endI-startI == 2) && (endJ-startJ == 2)){
            // 탐색한다. (해당 배열 위치에 result 기록)
            for(int i = startI ; i < endI ; i++){
                for(int j = startJ ; j < endJ ; j++){
                    if(i==r && j==c){
                        System.out.println(result);
                        //afterTime = System.currentTimeMillis(); // 시간측정2
                        //.out.println("Time : " + (afterTime - beforeTime)); // 시간측정3
                        System.exit(0);
                    } else {
                        result++;
                    }
                }
            }
            return;
        }

        // 4방으로 나눠서 재귀
        search(startI, (startI+endI)/2, startJ,(startJ+endJ)/2);
        search(startI, (startI+endI)/2, (startJ+endJ)/2, endJ);
        search((startI+endI)/2, endI, startJ, (startJ+endJ)/2);
        search((startI+endI)/2, endI, (startJ+endJ)/2, endJ);
    }

    public static void main(String[] args) throws IOException {
        //beforeTime = System.currentTimeMillis(); // 시간측정1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        search(0, (int)Math.pow(2,N), 0, (int)Math.pow(2,N));
    }
}
