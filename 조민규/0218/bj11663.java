package month2;

import com.sun.org.apache.bcel.internal.generic.IUSHR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj11663 {

    static class Line{
        int left, right;
        Line(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    static int N, M;
    static int[] dot;
    static Line[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        dot = new int[N];
        line = new Line[M];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N ; i++){
            dot[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(dot);
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            line[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            binarySearch(line[i]);
        }


    }

    public static void binarySearch(Line line){
        int start = 0;
        int end = N-1;
        int half = (start+end) / 2;

        while(start <= end){

            if((line.left > dot[half] && line.right < dot[half+1]) || line.left > dot[half+1]){ // 선이
                System.out.println(0);
                break;
            }

            if(dot[half] >= line.left && dot[half] <= line.right){ // 중간값이 선 안에 있을 경우

            } else if(dot[half] < line.left){ // 중간값이 선보다 앞에 있을 경우
                start = half + 1;
            } else if(dot[half] > line.right){ // 중간값이 선보다 뒤에 있을 경우
                end = half - 1;
            }
            half = (start+end) / 2;
        }

    }
}
