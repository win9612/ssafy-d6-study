<<<<<<< HEAD
package seungwon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Baekjoon4358 {

	public static void main(String[] args) throws IOException {
		// TODO 생태학. 나무의 분포도를 측정.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> tree_map = new HashMap<String, Integer>();
		String str;
		
		while( true ) {
			str = br.readLine();
			
			if(str ==null || str.length()==0) {
				break;
			}
			tree_map.put(str, tree_map.getOrDefault(str, 0)+1);
// getOrDefault(Object key, V DefaultValue) : 찾는 key가 존재하면 찾는 key의 값을 반환하고 없다면 default 값을 반환하는 메서드
		}
		
		// 전체 수 저장할 변수 생성
		int allcount = 0;
		// keyset을 만들어서 반복문 ㄱ
		Set<String> keys = tree_map.keySet();
		// 반복문으로 allcount 구함
		for(String key:keys) {
			allcount += tree_map.get(key);
		}
		
		// 정렬을 위해 Object형 배열 생성
		Object[] obs = tree_map.keySet().toArray();
		
		// 정렬. 이름 알파벳 순.
		Arrays.sort(obs);
		
		for(Object o: obs) {
			String a = (String) o;
			int count = tree_map.get(a);
			double ratio = (double)(count*100.0)/allcount;
			System.out.printf("%s %.4f\n",a, ratio);
		}
		
		

	}

}
=======
package seungwon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Baekjoon4358 {

	public static void main(String[] args) throws IOException {
		// TODO 생태학. 나무의 분포도를 측정.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> tree_map = new HashMap<String, Integer>();
		String str;
		
		while( true ) {
			str = br.readLine();
			
			if(str ==null || str.length()==0) {
				break;
			}
			tree_map.put(str, tree_map.getOrDefault(str, 0)+1);
// getOrDefault(Object key, V DefaultValue) : 찾는 key가 존재하면 찾는 key의 값을 반환하고 없다면 default 값을 반환하는 메서드
		}
		
		// 전체 수 저장할 변수 생성
		int allcount = 0;
		// keyset을 만들어서 반복문 ㄱ
		Set<String> keys = tree_map.keySet();
		// 반복문으로 allcount 구함
		for(String key:keys) {
			allcount += tree_map.get(key);
		}
		
		// 정렬을 위해 Object형 배열 생성
		Object[] obs = tree_map.keySet().toArray();
		
		// 정렬. 이름 알파벳 순.
		Arrays.sort(obs);
		
		for(Object o: obs) {
			String a = (String) o;
			int count = tree_map.get(a);
			double ratio = (double)(count*100.0)/allcount;
			System.out.printf("%s %.4f\n",a, ratio);
		}
		
		

	}

}
>>>>>>> 4019eb485fe88814e8a99cb3cd6e0f1646046d59
