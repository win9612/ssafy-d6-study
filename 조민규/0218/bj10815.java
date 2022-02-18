package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 숫자 카드
public class bj10815 {

    static int N, M;
    static int sanggeun[], cards[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());
        sanggeun = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N ; i++){
            sanggeun[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        cards = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < M ; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sanggeun); // 이분탐색을 위해 탐색할 배열 오름차순 정렬
        for(int i = 0 ; i < M ; i++){
            searchRecursive(i);
        }
    }

    public static void searchRecursive(int key){
        int start = 0;
        int end = N;
        int half = end / 2;

        while(true){
            if(end-start <= 1){ // 이분탐색의 마지막 단계까지 왔을 때
                if(cards[key] == sanggeun[start]) // 마지막 하나 남은 게 답이면
                    System.out.print(1 + " ");
                else // 마지막 하나조차 답이 아니면
                    System.out.print(0 + " ");
                break;
            }

            if(cards[key] == sanggeun[half]){ // 현재 중간값이 찾는 값과 일치할 때
                System.out.print(1 + " ");
                break;
            }
            else if(cards[key] < sanggeun[half]){ // 현재 중간값이 찾는 값보다 클 때
                end = half;
            }
            else { // 현재 중간값이 찾는 값보다 작을 때
                start = half;
            }
            half = (end+start) / 2; // 중간값 재조정
        }
    }
}
