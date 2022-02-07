// https://programmers.co.kr/learn/courses/30/lessons/60057?language=java
class Solution {
    public static int solution(String s) {
        int l = s.length();
        int answer = l;

        for (int i = 1; i <= l / 2; i++) {
            String tmp1 = s.substring(0, i);
            int start = i;
            int end = 2 * i;
            int cnt = 1;
            String result = "";
            while (end <= l) {
                String tmp2 = s.substring(start, end);
                if (tmp1.equals(tmp2)) {
                    // 문자열이 일치한다면 cnt+1
                    cnt++;
                } else {
                    // 문자열이 불일치한다면 지금까지의 문자열을 result에 넣고
                    if (cnt > 1)
                        result += cnt;
                    result += tmp1;
                    // 비교당할 문자열을 새로 갱신
                    tmp1 = tmp2;
                    cnt = 1;
                }
                start = end;
                end = start + i;
            }
            // 남는 문자열들을 마저 이어붙여준다.
            if (cnt > 1)
                result += cnt;
            result += tmp1;
            result += s.substring(start, l);
            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}
