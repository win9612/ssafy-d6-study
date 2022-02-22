package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 방 배정
public class bj13300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int K = Integer.parseInt(st.nextToken()); // 한 방에 배정할 수 있는 최대 인원
        int[][] room = new int[6][2]; // 6개의 학년, 2개의 성별로 만든 2차원 배열
        int ans = 0;

        for(int n = 0 ; n < N ; n++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken()) - 1;

            if(room[grade][gender] == 0){ // 방에 들어온 첫 학생일 경우
                room[grade][gender] += 1;
                ans++; // 최소 방 갯수 + 1
            } else if(room[grade][gender] == K){ // 방에 이미 K명 학생이 차 있을 경우
                room[grade][gender] = 1; // 방의 인원을 초기화하고 새 학생 한 명을 넣는다.
                ans++; // 최소 방 갯수 + 1
            } else { // 방에 들어온 첫 학생이 아니고, 방이 가득 차 있지 않을 경우
                room[grade][gender] += 1; // 해당하는 방 학생 수만 + 1 한다.
            }
        }
        System.out.println(ans);
    }
}
