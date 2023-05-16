import java.util.Stack;

public class RecursionProbs {

    static double sumReciprocals(int n){
        if(n == 1)
            return 1;
        return 1.0/n + sumReciprocals(n - 1);
    }

    static int productOfEvens(int n) {
        if (n == 0)
            return 1;
        return 2 * n * productOfEvens(n - 1);
    }

    static String conversion(int num, int base){
        if(num == 0)
            return "";
        return conversion(num / base, base) + num % base;
    }

    static int matchingDigits(int a, int b){
        if(a == 0 && b == 0)
            return 1;
        else if ((a == 0 && b % 10 == 0) || (a % 10 == 0 && b == 0))
            return 1;
        else if (a == 0 || b == 0)
            return 0;
        else if(a % 10 == b % 10)
            return 1 + matchingDigits(a / 10, b / 10);
        return matchingDigits(a / 10, b / 10);
    }

    static void doubleUp(Stack<Integer> nums){
        if(nums.size() == 0)
            return;
        int num = nums.pop();
        doubleUp(nums);
        nums.push(num);
        nums.push(num);
    }

    static void printThis(int n){
        if(n == 1) {
            System.out.print("*");
            return;
        }
        else if(n == 2) {
            System.out.print("**");
            return;
        }
        System.out.print("<");
        printThis(n - 2);
        System.out.print(">");
    }

    static void printNums2(int n){
        if(n == 1) {
            System.out.print("1 ");
            return;
        }
        else if(n == 2) {
            System.out.print("1 1 ");
            return;
        }
        if(n % 2 == 0)
            System.out.print(n / 2 + " ");
        else
            System.out.print(n / 2 + 1 + " ");
        printNums2(n - 2);
        if(n % 2 == 0)
            System.out.print(n / 2 + " ");
        else
            System.out.print(n / 2 + 1 + " ");
    }


    public static void main(String[] args) {
        System.out.println(sumReciprocals(10));
        System.out.println(productOfEvens(4));
        System.out.println(conversion(1453,8));
        System.out.println(conversion(10, 2));
        System.out.println(matchingDigits(1000, 0));
        System.out.println(matchingDigits(298892, 7892));
        Stack temp = new Stack<Integer>();
        temp.push(3);
        temp.push(7);
        temp.push(12);
        temp.push(9);
        doubleUp(temp);
        printThis(7);
        System.out.println();
        printNums2(8);
    }
}
