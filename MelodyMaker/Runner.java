import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Runner {

    public static void main(String[] args) {
        QueueProbs tester = new QueueProbs();
        Queue<Integer> test = new LinkedList<>(Arrays.asList(3, 5, 4, 17, 6, 83, 1, 84, 16, 37));
        System.out.println(tester.evenFirst(test));
        test = new LinkedList<>(Arrays.asList(3, 8, 17, 9, 17, 8, 3));
        System.out.println(tester.numPalindrome(test));
        test = new LinkedList<>(Arrays.asList(3, 10, 17, 9, 17, 8, 3));
        System.out.println(tester.numPalindrome(test));
        System.out.println(tester.primeNum(50));
    }

}
