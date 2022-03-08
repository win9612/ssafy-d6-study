package Day0308;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon1697_숨바꼭질 {
	
	static int N, K;
	static int min_time=Integer.MAX_VALUE;
	static boolean[] selected;
	static Queue<Node> queue;
	
	public static void main(String[] args) {
		// TODO 숨바꼭질
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		selected = new boolean[100001];
		queue = new LinkedList<Node>();
		
		bfs();
		
		System.out.println(min_time);

	}
	
	static void bfs() {
		
		queue.offer(new Node(N, 0));
		selected[N] = true;
		
		while(!queue.isEmpty()) {
			Node temp = queue.poll();
			
			if(temp.count >= min_time) continue;
			
			if(temp.num == K) {
				if(min_time>temp.count);
				min_time = temp.count;
			}
			
			if( temp.num+1<=100000 && !selected[temp.num+1]) {
				queue.offer(new Node(temp.num+1, temp.count+1));
				selected[temp.num+1] = true;
			}
			if( 0<=temp.num-1 && !selected[temp.num-1]) {
				queue.offer(new Node(temp.num-1, temp.count+1));
				selected[temp.num-1] = true;
			}
			if(temp.num*2 <=100000 && !selected[temp.num*2]) {
				queue.offer(new Node(temp.num*2, temp.count+1));
				selected[temp.num*2] = true;
			}

		}
	}
	
	static class Node{
		int num;
		int count;
		
		public Node(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
	}
}
