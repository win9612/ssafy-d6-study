import java.util.*;
import java.io.*;
class Solution {
    static int[][] graph = {{1,2,3}, {4,5,6}, {7,8,9}, {-1, 0, -2}};
    static int[][] visited;
    static Queue<int[]> q;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder(numbers.length);
        List<String> result = new ArrayList<>(); 
        int curL = -1;
        int curR = -2;

        for(int i=0; i<numbers.length; i++){
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){ // 1, 4, 7 이면 현재 왼손의 위치로 저장
                curL = numbers[i];
                sb.append("L");
            }else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){ // 3, 6, 9 이면 현재 오른손의 위치로 저장
                curR = numbers[i];
                sb.append("R");
            }else{ // 2, 5, 8, 0 이 눌린 경우
                int leftCnt = bfs(curL, numbers[i]);
                int rightCnt = bfs(curR, numbers[i]);
                if(leftCnt < rightCnt){ // 왼손이 더 가깝다면 현재 왼손의 위치로 저장
                    curL = numbers[i];
                    sb.append("L");
                }else if(leftCnt > rightCnt){ // 오른손이 더 가깝다면 현재 오른손의 위치로 저장
                    curR = numbers[i];
                    sb.append("R");
                }else{ // 동일하면 hand변수가 왼손인지, 오른손이지 판별
                    if(hand.equals("left")){ 
                        curL = numbers[i];
                        sb.append("L");
                    }else{
                        curR = numbers[i];
                        sb.append("R");
                    }
                }
            }
        }

        return sb.toString();
    }
    static int bfs(int start, int end){
        visited = new int[4][3];
        int cnt = 0;
        q = new LinkedList<>();

        // 이중for문을 통해 입력받은 start변수(왼손 또는 오른손의 현재 위치)가 키패드에 어느 좌표에 있는지
        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                if(graph[i][j] == start){
                    q.offer(new int[] {i, j});
                    visited[i][j] = 1;
                } 
            }
        }
        
        while(!q.isEmpty()){
            int y = q.peek()[0];
            int x = q.peek()[1];
            q.poll();
            
            if(graph[y][x] == end){
                cnt = visited[y][x];
                break;
            }

            for(int l=0; l<4; l++){
                int yy = y + dy[l];
                int xx = x + dx[l];
                if(yy>=0 && yy <4 && xx>=0 && xx <3){
                    if(visited[yy][xx] == 0){
                        q.offer(new int[] {yy, xx});
                        visited[yy][xx] = visited[y][x] + 1;
                    }
                }
            }
        }
        return cnt-1;
    }
 
}
