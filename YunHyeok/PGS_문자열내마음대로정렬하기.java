import java.util.*;
class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        ArrayList<String> arr = new ArrayList<>();
        
        for(int i=0; i<strings.length; i++){
            arr.add(""+strings[i].charAt(n)+strings[i]); // String으로 변환하는 방법
        }
        
        Collections.sort(arr);
        
        answer = new String[arr.size()];
        for(int i=0; i< arr.size(); i++){
            answer[i] = arr.get(i).substring(1, arr.get(i).length());
        }
        return answer;
    }
}