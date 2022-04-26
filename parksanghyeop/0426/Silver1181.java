package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Silver1181 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> wordMap = new HashMap<>();
		List<String> list = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			int length = word.length();
			
			wordMap.put(word, wordMap.getOrDefault(word, length));
		}
		
		List<Map.Entry<String, Integer>> entryList = new LinkedList<>(wordMap.entrySet());
	
		entryList.sort(Map.Entry.comparingByKey());
		entryList.sort(Map.Entry.comparingByValue());
		
		for(Map.Entry<String, Integer> entry : entryList) {
			System.out.println(entry.getKey());
		}			
	}

	

}
