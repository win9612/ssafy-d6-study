package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 스타트와 링크
public class bj14889 {

    static int N; // 전체 사람 수
    static int[][] arr; // 입력값 저장 배열
    static int[][] team; // 각 팀의 플레이어 정보
    static int min = Integer.MAX_VALUE; // 차이 최솟값 저장 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        team = new int[2][N/2];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(min);
    }

    public static void combination(int idx, int start){
        if(idx == N/2){
            int team2idx = 0;

            // 두 개의 팀에 플레이어를 나눠 저장한다.
            for(int i = 0 ; i < N ; i++){
                boolean flag = false;
                for(int j = 0 ; j < N/2 ; j++){
                    if(i == team[0][j]) flag = true;
                }
                if(!flag){
                    team[1][team2idx] = i;
                    team2idx++;
                }
            }

            // 각 팀의 조합 점수 합을 계산한다.
            int[] sum = new int[2];
            for(int n = 0 ; n < 2 ; n++){
                for(int i = 0 ; i < N/2 ; i++){
                    for(int j = 0 ; j < N/2 ; j++){
                        sum[n] += arr[team[n][i]][team[n][j]];
                    }
                }
            }

            // 두 팀의 접수합 차와 현재 최솟값 중 더 작은값을 min에 저장한다.
            min = Math.min(min, Math.abs(sum[1]-sum[0]));
            return;
        }

        for(int i = start ; i < N ; i++){
            team[0][idx] = i;
            combination(idx+1, i+1);
        }
    }
}
