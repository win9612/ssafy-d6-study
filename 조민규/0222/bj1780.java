package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 종이의 개수
public class bj1780 {

    static int N;
    static int[][] arr;

    static int minus = 0;
    static int zero = 0;
    static int plus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine()); // 종이 크기
        arr = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0,0,N);

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(plus);
    }

    public static void recursive(int starti, int startj, int size){
        int shape = arr[starti][startj];

        boolean isSame = true;
        check : for(int i = starti ; i < starti+size ; i++){
            for(int j = startj ; j < startj+size ; j++){
                if(arr[i][j] != shape){
                    isSame = false;
                    break check;
                }
            }
        }

        if(isSame){ // 만약에 범위 내 모든 모양이 다 똑같아서 for문을 끝까지 다 돌았다면
            if(shape == -1) minus++;
            else if(shape == 0) zero++;
            else plus++;

            return;
        }

        size /= 3;
        recursive(starti, startj, size);
        recursive(starti, startj+size, size);
        recursive(starti, startj+size*2, size);
        recursive(starti+size, startj, size);
        recursive(starti+size, startj+size, size);
        recursive(starti+size, startj+size*2, size);
        recursive(starti+size*2, startj, size);
        recursive(starti+size*2, startj+size, size);
        recursive(starti+size*2, startj+size*2, size);
    }
}
