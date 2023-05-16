import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueProbs {

    Queue<Integer> evenFirst(Queue<Integer> nums) {
        Queue<Integer> temp = new LinkedList<Integer>();
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            if (nums.peek() % 2 == 0)
                temp.offer(nums.poll());
            else
                nums.offer(nums.poll());
        }
        while (!nums.isEmpty())
            temp.offer(nums.poll());
        return temp;
    }

    boolean numPalindrome(Queue<Integer> nums) {
        Queue<Integer> temp = new LinkedList<Integer>();
        Stack<Integer> t = new Stack<>();
        int size = nums.size();
        while (!nums.isEmpty()) {
            int i = nums.poll();
            t.push(i);
            temp.offer(i);
        }
        while (!temp.isEmpty()) {
            if (t.pop() != temp.poll())
                return false;
        }
        return true;
    }

    Stack<Integer> primeNum(int n) {
        Queue<Integer> temp = new LinkedList<Integer>();
        Stack<Integer> primes = new Stack<>();
        for(int i = 2; i < n; i++)
            temp.offer(i);
        while(!temp.isEmpty()){
            primes.push(temp.peek());
            int o = temp.size();
            for(int i = 0; i < o; i++){
                if(temp.peek() % primes.peek() == 0)
                    temp.poll();
                else
                    temp.offer(temp.poll());
            }
        }
        return primes;
    }
}
