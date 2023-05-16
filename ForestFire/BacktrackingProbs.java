import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BacktrackingProbs {

    static void printBinary(int digits) {
        printBinaryHelper(digits, "");
    }

    private static void printBinaryHelper(int digits, String c) {
        if (digits == 0) {
            System.out.print(c);
            return;
        }
        printBinaryHelper(digits - 1, c + "0");
        System.out.print(" ");
        printBinaryHelper(digits - 1, c + "1");
    }

    public static void climbStairs(int steps){
        climbStairsHelper(steps, "");
    }
    private static void climbStairsHelper(int steps, String s){
        if(steps == 0){
            System.out.println(s);
            return;
        }
        else if(steps == 1) {
            climbStairsHelper(steps - 1, s + "1 ");
        }
        else {
            climbStairsHelper(steps - 1, s + "1, ");
            climbStairsHelper(steps - 2, s + "2, ");
        }
    }

    static void campsite(int x, int y){
        campsiteHelper(x, y, "");
    }

    private static void campsiteHelper(int x, int y, String c){
        if(x == 0 && y == 0)
            System.out.println(c);
        else if(x == 0)
            campsiteHelper(x, y - 1 , c + "N ");
        else if(y == 0)
            campsiteHelper(x - 1, y, c + "E ");
        else {
            campsiteHelper(x - 1, y, c + "E ");
            campsiteHelper(x, y - 1, c + "N ");
            campsiteHelper(x - 1, y - 1, c + "NE ");
        }
    }

    public static int getMax(List<Integer> nums, int limit){
        Queue<Integer> a = new LinkedList<Integer>();
        for(int j: nums)
            a.offer(j);
        return getMaxHelper(a, limit, 0);
    }

    private static int getMaxHelper(Queue<Integer> nums, int limit, int sum) {
        if(sum > limit)
            return Integer.MIN_VALUE;
        if(nums.isEmpty())
            return sum;
        int temp = nums.poll();
        return Math.max(getMaxHelper(nums, limit, sum + temp), getMaxHelper(nums, limit, sum));
    }





    public static void main(String[] args) {
        printBinary(3);
        System.out.println("\n");
        climbStairs(4);
        System.out.println();
        campsite(2,1);
        System.out.println();
        System.out.println(getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));
    }
}
