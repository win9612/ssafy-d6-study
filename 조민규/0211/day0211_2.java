public class day0211_2 {
    static int ans = 0;

    // idx : 현재 인덱스, sum : 현재 합, numbers : 배열, target : 목표값
    static void dfs(int idx, int sum, int[] numbers, int target){
        if(idx >= numbers.length){
            if(sum == target){
                System.out.println("*****          GotCha!!!!         *****");
                ans++;
            }
            return;
        }

        dfs(idx+1, sum+numbers[idx], numbers, target);
        dfs(idx+1, sum-numbers[idx], numbers, target);
    }

    static int solution(int[] numbers, int target){
        dfs(0,0, numbers, target);
        System.out.println(ans);
        return ans;
    }


    public static void main(String[] args) {
        int[] a = {1,1,1,1,1};
        int b = 5;
        solution(a, b);
    }
}
