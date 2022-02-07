import java.util.Scanner;

public class BOJ_1547_공 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();
        int circle = 1;

        for(int tc = 0;tc<TC;tc++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(circle == x ){
                circle =y;
            }
            else if(circle == y){
                circle = x;
            }
            else{continue;} //제외해도 됨
        }
        System.out.println(circle);
    }
}
