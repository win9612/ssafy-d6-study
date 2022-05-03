package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 단어 정렬
public class bj1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> arr = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            String input = br.readLine();

            // 0번째 입력이면 바로 입력하고 continue
            if(i == 0){
                arr.add(input);
                continue;
            }

            boolean flag = true;
            for(int j = 0 ; j < i ; j++){
                if(arr.get(j).length() < input.length()){
                    continue;
                } else if(arr.get(j).length() == input.length()){
                    if(arr.get(j).compareTo(input) > 0){
                        arr.add(j, input);
                        flag = false;
                        break;
                    }
                } else {
                    arr.add(j, input);
                    flag = false;
                    break;
                }
            }
            if(flag) // input이 결국 제일 큰 길이의 단어였을 때
                arr.add(input);
        }

        String tmp = "";
        for(String s : arr){
            if(tmp.equals(s)) continue;
            System.out.println(s);
            tmp = s;
        }
    }
}
