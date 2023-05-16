import java.util.Stack;

public class StackProbs {


    static Stack<Integer> doubleUp(Stack<Integer> nums) {
        Stack<Integer> temp = new Stack<>();
        while (!nums.isEmpty()) {
            Integer j = nums.pop();
            temp.push(j);
            temp.push(j);
        }
        while (!temp.isEmpty()) {
            nums.push(temp.pop());
        }
        return nums;
    }

    static Stack<Integer> posAndNeg(Stack<Integer> nums) {
        Stack<Integer> pos = new Stack<>();
        Stack<Integer> neg = new Stack<>();
        while (!nums.isEmpty())
            if (nums.peek() < 0)
                neg.push(nums.pop());
            else
                pos.push(nums.pop());
        while (!neg.isEmpty())
            nums.push(neg.pop());
        while (!pos.isEmpty())
            nums.push(pos.pop());
        return nums;
    }

    static Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
        Stack<Integer> temp = new Stack<>();
        Stack<Integer> temp2 = new Stack<>();
        while(!nums.isEmpty())
            temp.push(nums.pop());
        for(int i = 0; i < n; i++)
            temp2.push(temp.pop());
        while(!temp.isEmpty())
            nums.push(temp.pop());
        for(int i = 0; i < n; i++)
            temp.push(temp2.pop());
        while(!temp.isEmpty())
            nums.push(temp.pop());
        return nums;
    }

    static String reverseVowels(String str) {
        Stack<Character> temp = new Stack<>();
        String a = "aieouAIEOU";
        for(int i = 0; i < str.length(); i++)
            if(a.indexOf(str.charAt(i)) > 0)
                temp.push(str.charAt(i));
        for(int i = 0; i < str.length(); i++)
            if(a.indexOf(str.charAt(i)) > 0)
                str = str.substring(0, i) + temp.pop() + str.substring(i + 1);
        return str;
    }

    static boolean bracketBalance(String s){
        Stack<Character> temp = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('  || c =='{'){
                temp.push(c);
            }
            else if(temp.isEmpty())
                return false;
            else if(c == ')')
                if (temp.pop() != '(')
                    return false;
            else if (c == '}')
                if (temp.pop() != '{')
                    return false;
            }
        return true;
    }
}