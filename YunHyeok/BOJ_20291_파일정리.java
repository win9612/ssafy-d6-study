import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	static int N;
	static Map<String, Integer> map = new TreeMap<>();
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	
    	while(N-- > 0) {
    		String str = sc.next();
    		String ext = str.substring(str.indexOf(".")+1);
    		if(!map.containsKey(ext)) {
    			map.put(ext, 1);
    		}else {
    			map.put(ext, map.get(ext)+1);
    		}
    	}
    	
    	for(Entry<String, Integer> entry : map.entrySet()) {
    		System.out.println(entry.getKey() + " " + entry.getValue());
    	}

    }
}