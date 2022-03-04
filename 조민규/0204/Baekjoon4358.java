package c0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        int sum = 0;
        Map<String, Integer> jong = new HashMap<>();

        while(true){
            str = br.readLine();
            if(str.equals("exit")) break;

            if(!(jong.containsKey(str)))// 등록된 종에 없으면
                jong.put(str, 1);
            else
                jong.put(str, jong.get(str) + 1);
        }

        Iterator<String> keys = jong.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            sum += jong.get(key);
        }

        Iterator<String> printKeys = jong.keySet().iterator();
        while(printKeys.hasNext()){
            String key = printKeys.next();
            double rate = (double)jong.get(key) / (double)sum * 100;
            System.out.println(key + " " + String.format("%.4f", rate));
        }
    }
}
