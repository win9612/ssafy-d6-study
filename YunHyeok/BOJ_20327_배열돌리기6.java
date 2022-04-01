import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20327_배열돌리기6 {
	static int N, R, k, size, partSize;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, N);
		System.out.println(size);
		
		map = new int[size][size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		printResult();
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			
			//연산에는 단계 ℓ (0 ≤ ℓ < N) -> 1 ≤ N ≤ 7
			partSize = (int) Math.pow(2, a); // 부분배열크기
			int oSize = size;
			dfs(0, 0, oSize);
			printResult();
			
		}
		
		
		
	}
	static void dfs(int si, int sj, int oSize) {
		if(oSize == partSize) {
			rotate(si, sj, k);
			return;
		}
		
		int s= oSize/2;
		
		dfs(si, sj, s);
		dfs(si, sj+s, s);
		dfs(si+s, sj, s);
		dfs(si+s, sj+s, s);
	}
	
	static void rotate(int si, int sj, int k) {
		System.out.println(si + " " + sj + " " + k);
		
		if(k == 1) { // 부분배열 상하반전
			for(int j=sj; j<sj+partSize; j++) {
				for(int r1=si, r2=si+partSize-1; r1<r2; r1++, r2--) {
					int temp = map[r1][j];
					map[r1][j] = map[r2][j];
					map[r2][j] = temp;
				}
			}	
			
		}else if(k == 2) { // 부분배열 좌우반전
			for(int r=si; r<si+partSize; r++) {
				for(int c1=sj, c2=sj+partSize-1; c1<c2; c1++, c2--) {
					int temp = map[r][c1];
					map[r][c1] = map[r][c2];
					map[r][c2] = temp;
				}
			}	
			
		}else if(k == 3) { // 오른쪽으로 90도 회전
			
			int tmp = map[si][sj]; // 처음 저장
			for(int i=si; i<si + partSize; i++) {
				for(int j=sj; j<sj + partSize; j++) {	
					int ttmp = map[j][partSize-1-i]; //  Index -1 out of bounds for length 8 
					map[j][partSize-1-i] = tmp;
					tmp = ttmp;
				}
			}
			
		}else if(k == 4) { // 왼쪽으로 90도 회전
			int[][] result = new int[partSize][partSize];
			
			for(int i=si; i<si+partSize; i++) {
				for(int j=sj; j<sj+partSize; j++) {
					result[size-1-j][i] = map[i][j];
				}
			}
			map = result;
		}
		
	}
	
	
	
	
	
	// 맵 출력 함수
	static void printResult() {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
