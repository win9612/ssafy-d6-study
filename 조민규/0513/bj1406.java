package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 에디터
public class bj1406 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<String> left = new Stack<>();
    static Stack<String> right = new Stack<>();

    static void go() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        String command = st.nextToken();
        switch(command){
            case "L":
                if(!left.isEmpty())
                    right.push(left.pop());
                break;
            case "D":
                if(!right.isEmpty())
                    left.push(right.pop());
                break;
            case "B":
                if(!left.isEmpty())
                    left.pop();
                break;
            case "P":
                left.push(st.nextToken());
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    public static void main(String[] args) throws IOException {
        String input = br.readLine(); // 문자열
        for(int i = 0 ; i < input.length() ; i++){
            left.push(input.substring(i,i+1));
        }
        int N = Integer.parseInt(br.readLine()); // 입력 횟수
        for(int i = 0 ; i < N ; i++){
            go();
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        while(!left.isEmpty()){
            right.push(left.pop());
        }
        while(!right.isEmpty()){
            sb.append(right.pop());
        }
        System.out.println(sb.toString());
    }
}
