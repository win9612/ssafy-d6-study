package Day0313; 

import java.util.Scanner;

public class Baekjoon20364_부동산다툼 {

	static int N, Q;
	static int[] nodes;
	static boolean[] selected;
	static int max_num;
	static int answer;
	
	public static void main(String[] args) {
		// TODO 부동산 다툼
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Q = sc.nextInt();
		
		nodes = new int[Q+1];
		
		for(int i=1; i<=Q; i++) {
			nodes[i] = sc.nextInt();
			// 번호가 가장 큰 오리의 번호를 저장한다.
			max_num = Math.max(max_num, nodes[i]);
		}
		
		selected = new boolean[max_num+1];
		
		
		for(int i=1; i<=Q; i++) {
			int temp = nodes[i];
			answer = 0;
			dfs(temp, temp);
			
		}
	

	}
	

	
	static void dfs(int target, int current) {
		// 기저 조건. 루트 노드 1에 도착
		if(current == 1) {
			selected[target] = true; // 중간에 막혔어도 방문처리를 해도됨
			// 어차피 중간에서 막혔으면 그 노드 이후로는 다 못가기 때문에 방문 처리해도 무관
			System.out.println(answer);
			return;
		}
		
		
		if(selected[current]) answer = current; // return을 하지 않는 이유는 가장 먼저 막힌 곳을 정답으로 해야되기 때문.
		// 이진트리에서 상위 노드로 이동
		current = current%2==1 ? (current-1)/2 : current/2;
		
		dfs(target, current);
		
	}

}
