package Day0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baekjoon1655_가운데를말해요 {
	
	static PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>();
	static PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO 가운데를 말해요
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(minQueue.size() == maxQueue.size()) {
				maxQueue.offer(num);
			}else minQueue.offer(num);
				
			if(!minQueue.isEmpty() && !maxQueue.isEmpty()) {
				if(minQueue.peek() < maxQueue.peek()) { // 최소의 최소값과 최대의 최대값 비교.
					// swap
					int temp = minQueue.poll(); // min에서 제거와 동시에 반환
					minQueue.offer(maxQueue.poll());
					maxQueue.offer(temp);
				}
			}
			sb.append(maxQueue.peek() + "\n");
			
		}
		System.out.println(sb);
	}

}
