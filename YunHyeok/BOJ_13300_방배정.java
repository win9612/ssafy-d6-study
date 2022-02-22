import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13300_방배정 {
	static int N,K,cnt;
	static int[][] array = new int[7][2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			array[Y][S] += 1;
		}
		
		for(int i=1; i<7; i++) {
			for(int j=0; j<2; j++) {
				cnt += array[i][j] / K; // 몫을 더하기
				if(array[i][j] % K != 0) cnt++; // 나머지 더하기
			}
		}
		System.out.println(cnt);
	}

}

