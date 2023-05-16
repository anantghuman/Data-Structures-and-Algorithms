import java.util.*;

public class PrimePath {

    static HashMap<Integer, HashSet<Integer>> map;
    public static ArrayList<Integer> getPrimes() {
        Boolean[] arr = new Boolean[10000];
        Arrays.fill(arr, false);
        for (int i = 2; i * i <= 9999; i++) {
            if (arr[i] == false) {
                for (int j = i * i; j <= 9999; j += i)
                    arr[j] = true;
            }
        }
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 1000; i <= 9999; i++) {
            if (!arr[i])
                primes.add(i);
        }
        return primes;
    }


    static Boolean oneDifference(int a, int b) {
        String l = Integer.toString(a);
        String j = Integer.toString(b);
        int diff = 0;
        for(int i = 0; i < l.length(); i++){
            if(l.charAt(i) != j.charAt(i))
                diff++;
        }
        return diff == 1;
    }

    public static int numFlips(int num1, int num2) {

        ArrayList<Integer> a = getPrimes();
        map = new HashMap<>();
        for (int i : a) {
            HashSet<Integer> m = new HashSet<>();
            for (int j : a)
                if (oneDifference(i, j)) {
                    m.add(j);
                }
            map.put(i, m);
        }
        return findNumChanges(num1, num2);
    }

    private static int findNumChanges(int num1, int num2) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(num1, 0));
        HashSet<Integer> set  = new HashSet<>();
        while(queue.size() > 0){
            Node a = queue.poll();
            int num = a.num;
            if(num == num2)
                return a.distance;
            HashSet<Integer> j = map.get(num);
            for (int m: j){
                if(!set.contains(m)) {
                    queue.offer(new Node(m, a.distance + 1));
                    set.add(m);
                }
            }
        }
        return -1;
    }

    // Driver code
    public static void main(String[] args) {
        System.out.println(numFlips(1033, 8179));
        System.out.println(numFlips(1373, 8017));
        System.out.println(numFlips(1033, 1033));
    }

    public static class Node {
        int distance;
        int num;

        public Node(int num, int distance) {
            this.distance = distance;
            this.num = num;
        }

        @Override
        public boolean equals(Object obj) {
            return this.num == ((Node)obj).num;
        }

        @Override
        public int hashCode() {
            return num;
        }

        @Override
        public String toString() {
            return Integer.toString(num);
        }
    }
}
