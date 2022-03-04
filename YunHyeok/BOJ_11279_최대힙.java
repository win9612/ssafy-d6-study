import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_11279_최대힙 {
	static int N;
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //최대힙선언
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num == 0) {//입력받은 수가 0이라면 
				System.out.println(maxHeap.isEmpty() ? 0 : maxHeap.poll());
			}else maxHeap.add(num); // 입력받은 수가 0이 아니라면 add
		}
	}
}
