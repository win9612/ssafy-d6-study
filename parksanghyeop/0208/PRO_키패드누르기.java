import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PRO_키패드누르기{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        String hand;
        while (scanner.hasNext()) {
            try {
                numbers.add(scanner.nextInt());
            } catch (Exception e) {
                break;
            }
        }
        hand = scanner.next();

        int[] arrNumbers = new int[numbers.size()];
        int size = 0;
        for (int n : numbers) {
            arrNumbers[size++] = n;
        }
        String result = solution(arrNumbers, hand);
        System.out.println(result);
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        Map<Integer, Position> phone = new HashMap<Integer, Position>();
        phone.put(1, new Position(0, 0));
        phone.put(2, new Position(0, 1));
        phone.put(3, new Position(0, 2));
        phone.put(4, new Position(1, 0));
        phone.put(5, new Position(1, 1));
        phone.put(6, new Position(1, 2));
        phone.put(7, new Position(2, 0));
        phone.put(8, new Position(2, 1));
        phone.put(9, new Position(2, 2));
        phone.put(0, new Position(3, 1));

        Position left = new Position(3, 0);
        Position right = new Position(3, 2);

        for (int n : numbers) {
            Position target = phone.get(n);

            if (n == 1 || n == 4 || n == 7) {
                answer += "L";
                left.set(target.x, target.y);
            } else if (n == 3 || n == 6 || n == 9) {
                answer += "R";
                right.set(target.x, target.y);
            } else {
                System.out.println(getDistance(target, left) + " " + getDistance(target, right));
                if (getDistance(target, left) > getDistance(target, right)) {
                    answer += "R";
                    right.set(target.x, target.y);
                } else if (getDistance(target, left) < getDistance(target, right)) {
                    answer += "L";
                    left.set(target.x, target.y);
                } else {
                    if (hand.equals("left")) {
                        left.set(target.x, target.y);
                        answer += "L";
                    } else {
                        right.set(target.x, target.y);
                        answer += "R";
                    }
                }
            }


        }
        return answer;
    }
    public static int getDistance(Position phone, Position finger) {
        int x = Math.abs(phone.x - finger.x);
        int y = Math.abs(phone.y - finger.y);
        return x+y;
    }
    static class Position {
        int x;
        int y;
        Position() { }
        Position(int x, int y) {
            this.set(x, y);
        }
        public void set(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}