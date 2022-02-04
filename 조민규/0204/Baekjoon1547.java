package c0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1547 {

    static int swap(int a, int b){
        // b = swap(a, a = b);
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cups = {1, 0, 0};
        int x, y;
        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++){
            String str = br.readLine();
            String[] strarr = str.split(" ");
            x = Integer.parseInt(strarr[0]) - 1;
            y = Integer.parseInt(strarr[1]) - 1;
            cups[y] = swap(cups[x], cups[x] = cups[y]);
        }

        for(int i=0; i<cups.length; i++){
            if(cups[i] == 1) {
                System.out.println(i+1);
                return;
            }
        }
        System.out.println(-1);
    }
}
