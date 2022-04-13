import java.util.Scanner;

public class BOJ_1062_가르침 {
	static int N, K; 
	static boolean[] visited; // 26개로 해야함, 따로따로하는거 실패
	static int max = Integer.MIN_VALUE; 
	static String[] array; // anta tica 제외한 문자들
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		if(K<5) { 
			System.out.println(0);
		}else {
			array = new String[N];
			visited = new boolean[26];
			for(int i=0; i<N; i++) array[i] = sc.next(); // 자르는게 낫다? 안잘라도 상관없을듯
			visited['a'-97] = visited['c'-97] = visited['t'-97] = visited['i'-97] = visited['n'-97] = true;
			comb(0, 0);
			System.out.println(max);
		}

	}

	static void comb(int start, int cnt) {
		if(cnt == K-5) {
			max = Math.max(max, check());
			return;
		}
		
		for (int i = start; i < 26; i++) { //수가 작아서 26개로 해도 될듯
			if (!visited[i]) {
				visited[i] = true;
				comb(i, cnt+1);
				visited[i] = false;
			}
		}

	}

	static int check() {
		int cnt = 0;
		for(String str : array) {
			boolean flag = true;
			for(int s=0; s<str.length(); s++) {
				if(!visited[str.charAt(s)-97]) {
					flag = false;
					break;
				}
			}
			if(flag) cnt++;
		}
		return cnt;
	}
}