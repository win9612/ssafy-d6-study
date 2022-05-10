package Day0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Baekjoon20291_파일정리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO 파일 정리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new TreeMap<>();
		
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			st = new StringTokenizer(str, ".");
			String name = st.nextToken();
			String hwak = st.nextToken();
			
			if(map.get(hwak)==null) {
				map.put(hwak, 1);
			}else {
				map.put(hwak, map.get(hwak)+1);
			}
			
			
		}
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}

	}
	


}
