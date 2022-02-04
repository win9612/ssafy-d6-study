import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1547 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int M = Integer.parseInt(br.readLine());
		
		int[] arr = {1,2,3};
		int temp;
		
		for(int i=0; i<M; i++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			
			int X = Integer.parseInt(line.nextToken());
			int Y = Integer.parseInt(line.nextToken());
			
			for(int j=0; j<arr.length; j++) {
				if(arr[j] == X) {
//					System.out.println("j :"+j+" arr[j] :"+arr[j]);
					for(int k=0; k<arr.length; k++) {
						if(arr[k] == Y) {
							temp = arr[j];
							arr[j] = arr[k];
							arr[k] = temp;
//							System.out.println("k :"+k+" arr[k] :"+arr[k]);
//							System.out.println(arr[0]+", "+arr[1]+", "+arr[2]);
//							System.out.println("=============================");
							break;
						}
					}
					break;
				}
				
			}
		}
		System.out.println(arr[0]);
		
	}
}
