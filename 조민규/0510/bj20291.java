package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 파일 정리
public class bj20291 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> ans = new HashMap<>(); // 정답을 출력할 맵

        for(int i = 0 ; i < N ; i++){
            String file[] = br.readLine().split("\\."); // '.' 기준으로 파일명과 확장자 분리
            if(ans.containsKey(file[1])){ // 맵에 해당 확장자가 1개 이상 존재하면
                ans.put(file[1], ans.get(file[1]) + 1); // 기존 확장자 키의 값 + 1
            } else { // 맵에 해당 확장자가 없으면
                ans.put(file[1], 1); // 새로 추가
            }
        }

        // key값을 사전순으로 정렬
        String[] mapKey = ans.keySet().toArray(new String[0]);
        Arrays.sort(mapKey);

        // 정렬된 key 순으로 출력
        for(String key : mapKey){
            System.out.println(key + " " + ans.get(key));
        }
    }
}
