package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14696 {

    static int N;
    static int[] childA, childB;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int n = 0 ; n < N ; n++){
            childA = new int[4];
            childB = new int[4];

            // 입력 A
            st = new StringTokenizer(br.readLine());
            int numA = Integer.parseInt(st.nextToken());
            for(int i = 0 ; i < numA ; i++){
                childA[Integer.parseInt(st.nextToken())-1]++; // 해당 카드에 쓰인 숫자를 인덱스로 활용
            }

            // 입력 B
            st = new StringTokenizer(br.readLine());
            int numB = Integer.parseInt(st.nextToken());
            for(int i = 0 ; i < numB ; i++){
                childB[Integer.parseInt(st.nextToken())-1]++;
            }

            for(int i = 3 ; i >= 0 ; i--){ // 큰 숫자의 카드를 더 많이 갖고있는게 중요하므로 큰 숫자부터 확인
                if(childA[i] > childB[i]){
                    System.out.println("A");
                    break;
                }
                else if(childA[i] < childB[i]){
                    System.out.println("B");
                    break;
                }
                else if(i==0 && childA[i]==childB[i]){ // 1 카드를 비교하고, 모든 카드들이 비겼을 경우
                    System.out.println("D");
                }
            }
        }
    }
}
