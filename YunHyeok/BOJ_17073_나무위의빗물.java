import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17073_나무위의빗물 {
	static int N, W, leaf;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		tree = new int[N+1];
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			tree[from]++;
			tree[to]++;
		}
		
		for(int i=2; i<=N; i++) { // 루트노드는 제외!
			if(tree[i] == 1) leaf++;
		}
		
//		System.out.println(Arrays.toString(tree));
		System.out.println((double) W/leaf);

	}

}
