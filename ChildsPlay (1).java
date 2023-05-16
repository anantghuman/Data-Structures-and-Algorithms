import java.io.File;
import java.util.*;

public class ChildsPlay {
    static HashSet set;
    public static void main(String[] args) {
        set = new HashSet<Integer>();
        Scanner console = null;
        try {
            console = new Scanner(new File("play.dat"));
        } catch (Exception e) {
            System.out.println("File cannot be read!");
        }
        int n = console.nextInt();
        console.nextLine();
        while (n > 0) {
            n--;
            int r = console.nextInt();
            int m = console.nextInt();
            int l = console.nextInt();
            HashMap list = new HashMap<Integer, HashSet<Integer>>();
            for (int i = 0; i < m; i++) {
                int j = console.nextInt();
                int k = console.nextInt();
                if (!list.containsKey(j))
                    list.put(j, new HashSet<Integer>());
                HashSet<Integer> p = (HashSet<Integer>) list.get(j);
                p.add(k);
                list.put(j, p);
            }
            int t = 0;
            set = new HashSet<Integer>();
            for (int i = 0; i < l; i++) {
                int u = console.nextInt();
                t += dominoCount(u, list);
            }
            System.out.println(t);
            if(console.hasNextLine())
                console.nextLine();
        }
    }
    private static int dominoCount(int num, HashMap<Integer, HashSet<Integer>> domino){
        if(set.contains(num))
            return 0;
        set.add(num);
        if(!domino.containsKey(num))
            return 1;
        HashSet<Integer> j = domino.get(num);
        int m = 0;
        for(int i: j){
            m += dominoCount(i, domino);
        }
        return m + 1;
    }
}
