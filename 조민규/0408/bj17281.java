package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 야구
public class bj17281 {

    static int N;
    static int[][] batters; // 이닝, 결과
    static boolean[] baseStatus; // 각 루의 현재 상황
    static int curBatter; // 현재 타자
    static int outCount; // 아웃카운트
    static int max, score; // 정답, 현재 경우 최대점수

    public static void hit(int h){
        if(h == 4){ // 홈런일 경우
            for(int i = 2 ; i >= 0 ; i--){ // 2루 => 1루 => 0루 순으로 상황을 점검한다.
                if(baseStatus[i]){ // 베이스에 주자가 있을 경우
                    score++; // 주자에 대한 점수
                    baseStatus[i] = false;
                }
            }
            score++; // 홈런 친 타자 본인에 대한 점수
            return;
        }
        
        // 안타 ~ 3루타의 경우
        for(int i = 2 ; i >= 0 ; i--){
            if(i+h >= 3){ // 주자의 현재 위치 + 이동할 수 있는 거리가 홈에 도달하기에 충분한 경우
                if(baseStatus[i]){ // 해당 위치에 주자가 있었으면
                    score++;
                    baseStatus[i] = false;
                }
            } else { // 주자의 현재 위치 + 이동할 수 있는 거리가 홈에 도달하기에 충분하지 않은 경우
                if(baseStatus[i]){ // 해당 위치에 주자가 있으면
                    baseStatus[i+h] = true; // 안타의 종류만큼 주자를 전진시킴
                    baseStatus[i] = false; // 원래 베이스에 있던 주자는 사라졌으므로 false
                }
            }
        }
        baseStatus[h-1] = true; // 타자 출루
    }


    public static void inning(int n, int start){ // 이닝, 선두타자
        outCount = 0; // 아웃카운트 초기화
        curBatter = start; // 이닝 시작 시 현재 타자 = 선두타자
        baseStatus = new boolean[3]; // 베이스 상황 초기화

        while(outCount < 3){
            int hitStatus = batters[n][curBatter % 9];
            if(hitStatus == 0){
                outCount++;
            } else {
                hit(hitStatus);
            }
            curBatter++;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        batters = new int[N][9];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 9 ; j++){
                batters[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = Integer.MIN_VALUE;

        // 1회 선두타자가 0~8번타자인 각 경우 돌려보기
        for(int start = 0 ; start < 9 ; start++){
            score = 0;
            curBatter = start;
            for(int i = 0 ; i < N ; i++){
                inning(i, curBatter);
            }
            max = Math.max(max, score);
        }
        System.out.println(max);
    }
}
