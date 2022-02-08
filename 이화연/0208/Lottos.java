import java.util.Arrays;

/* Level 1 : 로또의 최고 순위와 최저 순위 */

public class Lottos {
    public static String solution(int[] lottos, int[] win_nums) {
        int count = 0; //같은 숫자가 몇 개인지 담을 변수
        int count0 = 0; //0개수가 몇 개인지 담을 변수 
        int[] answer = new int[2];
        
        //정렬
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        
        for(int i = 0; i<6; i++){
            //0개수 세기
            if(lottos[i] == 0){
                count0++;
                continue;
            }
            
            //구매한 로또에 당첨 번호와 일치하는 번호가 몇 개인지
            for(int j=0; j<6; j++){
                if(lottos[i] == win_nums[j]){
                    count++;
                }
            }
        }

        //최고순위는 구매한 로또가 당첨 번호와 일치하는 개수+알아볼 수 없는 번호의 개수가 당첨번호 안에 있을때
        answer[0] = ranking(count+count0);
        //최저는 알아볼 수 없는 번호의 개수가 당첨번호와 하나도 일치하지 않을 때
        answer[1] = ranking(count);
        
        return Arrays.toString(answer);
    }
    
    //순위 함수
    public static int ranking(int ranking){
        switch(ranking){
            case 6 : return 1; 
            case 5 : return 2;
            case 4 : return 3;
            case 3 : return 4;
            case 2 : return 5;
            default : return 6;
        }
    }
    
    public static void main(String[] args) {
		int[] lottos = {44,1,0,0,31,25};
		int[] win_nums = {31,10,45,1,6,19};
		
		System.out.println(solution(lottos, win_nums));
	}
}
