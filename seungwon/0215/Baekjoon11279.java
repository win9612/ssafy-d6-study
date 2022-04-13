package day0215_0218;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Baekjoon11279 {

	public static void main(String[] args) {
		// TODO 최대 힙
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)-> o2-o1);
		// default 는 최소힙이라 위와 같은 방식으로 최대힙으로 바꿔준다.
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i=0; i<N; i++) {
			int temp = sc.nextInt();
			
			if(temp>0) {
				pq.offer(temp);
			}else if(temp==0) {
				if(pq.isEmpty()) System.out.println(0);
				else System.out.println(pq.poll());
				
			}
			
		}

	}

}
