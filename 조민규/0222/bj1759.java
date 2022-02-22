package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 암호 만들기
public class bj1759 {

    static String vowel = "aeiou";

    static int L, C;

    static char[] input;
    static char[] output;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 암호 자릿수
        C = Integer.parseInt(st.nextToken()); // 대입할 수 있는 알파벳 수


        input = br.readLine().replaceAll(" ", "").toCharArray();
        output = new char[L];
        isSelected = new boolean[C];

        Arrays.sort(input);
        combination(0, 0);
    }

    public static void combination(int idx, int start){
        // 기본 파트
        if(idx == L){
            boolean vowelOK = false; // 모음이 포함되었는지 검사하는 변수
            int consCnt = 0; // 자음이 몇 개 포함되었는지 검사하는 변수
            StringBuilder sb = new StringBuilder();

            for(int i = 0 ; i < C ; i++){ // 전체 입력 알파벳에 대한 검사
                if(isSelected[i]){ // 현재 선택된 알파벳이고
                    sb.append(input[i]);
                    if(vowel.contains(Character.toString(input[i]))){ // 모음을 하나라도 포함했을 시
                        vowelOK = true;
                    } else { // 아니고 자음일 시
                        consCnt++; // 자음 카운트를 올림림
                   }
                }
            }

            if(vowelOK && (consCnt >= 2)){ // 모음이 하나 이상 포함이고 자음이 2개 이상 포함이면
                System.out.println(sb.toString());
            }
        }

        // 유도 파트
        for(int i = start ; i < C ; i++){
            isSelected[i] = true;
            combination(idx+1, i+1);
            isSelected[i] = false;
        }
    }
}


