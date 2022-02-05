class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCnt = 0;
        int equalCnt = 0;
        
        for(int i=0; i<6; i++){
            if(lottos[i] == 0) zeroCnt+=1; // 0 카운트
            for(int j=0; j<6; j++){
                if(lottos[i] == win_nums[j]) equalCnt += 1; // 두 로또 비교 후 같은 경우
            }
        }
        
        int max = 7 - (equalCnt + zeroCnt);
        int min = 7 - equalCnt;
        if(max==7) max = 6; // lottos배열에 0이 없고 다 틀린 경우
        if(min==7) min = 6; // lottos배열이 다 0인 경우
  
        return new int[] {max, min};
    }
}
