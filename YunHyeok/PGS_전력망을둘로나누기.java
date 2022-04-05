import java.util.*;

class Solution {
    static List<List<Integer>> list;
    static int min = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        list = new ArrayList<>();
        
        for(int i=0; i<=n; i++){
            list.add(new ArrayList<>()); 
        } // 이중리스트 선언하기
        
        for(int i=0; i < wires.length; i++){
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        } // 전력망 입력받기
                
        for(int i=1; i<= n; i++){
            for(int num : list.get(i)){
                if(num < i) continue; // 이미 끊었던 간선을 중복 방지
                int cnt = bfs(i, num, n); 
                min = Math.min(Math.abs(cnt - (n-cnt)), min); // (n-cnt) 다른 한쪽은 총 송전탑의 수 - bfs로 연결된 송전탑을 구한 수
            }
        }
        
        return min;
    }
    
    static int bfs(int start, int end, int n){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.offer(start);
        visited[start] = true;
        int cnt = 1;
        
        while(!q.isEmpty()){
            int curr = q.poll();
            
            for(int next : list.get(curr)){
                if(!visited[next] && next != end){ // nextV가 end라면, 큐에 넣어주지 않는다.
                    cnt++;
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
        
        return cnt;
        
    }
}