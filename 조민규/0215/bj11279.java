package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// 최대 힙
public class bj11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder()); // reverseOrder를 활용한 최대 힙 구성
        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){
            int tmp = Integer.parseInt(br.readLine());
            if(tmp == 0){ // 입력받은 경우가 0일때
                if(!q.isEmpty()){ // && 큐가 비어있지 않으면
                    System.out.println(q.poll()); // 큐의 맨 앞 요소를 제거하고 출력
                } else { // 큐가 비어있으면
                    System.out.println(0); // 0을 출력 
                }
            } else {
                q.add(tmp); // 입력받은 정수를 최대 힙에 추가
            }
        }
    }
}
