
// https://programmers.co.kr/learn/courses/30/lessons/67256
class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        Point currL = new Point(0, 3);
        Point currR = new Point(2, 3);
        
        for(int i = 0; i < numbers.length; i++){
            Point p;
            if(numbers[i] == 0)
                p = new Point(1, 3);
            else
                p = new Point((numbers[i]+2)%3, (numbers[i]-1)/3);
            
            if(p.x == 0){
                answer += "L";
                currL = p;
            } else if (p.x == 2){
                answer += "R";
                currR = p;
            } else {
              // 2 5 8 0인 경우   
                int ld = Math.abs(currL.x - p.x) + Math.abs(currL.y - p.y);
                int rd = Math.abs(currR.x - p.x) + Math.abs(currR.y - p.y);
                
                if(ld < rd || (ld == rd && hand.equals("left"))){
                    // 왼손이 가까운 경우 왼손을 이동
                    currL = p;
                    answer += "L";
                } else if(ld > rd || (ld == rd && hand.equals("right"))){
                    currR = p;
                    answer += "R";
                }
            }
        }
        
        return answer;
    }
}

class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
