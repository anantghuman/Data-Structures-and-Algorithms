import java.io.File;
import java.util.*;

public class Scooby {
    public static void main(String[] args) {
        Scanner console = null;
        try {
            console = new Scanner(new File("scooby.dat"));
        } catch (Exception e) {
            System.out.println("File cannot be read!");
        }
        int j = console.nextInt();
        console.nextLine();
        while (j > 0) {
            j--;
            String c = console.nextLine();
            String path = console.nextLine();
            HashMap list = new HashMap<Character, HashSet<Character>>();
            for (int i = 0; i < c.length(); i++) {
                char a = c.charAt(i);
                if (a == ' ' || i + 1 == c.length())
                    continue;
                if(c.charAt(i + 1) == ' ')
                    continue;
                char b = c.charAt(i + 1);
                if (!list.containsKey(a))
                    list.put(a, new HashSet<Character>());
                HashSet<Character> k = (HashSet<Character>) list.get(a);
                k.add(b);
                list.put(a, k);
                if (!list.containsKey(b))
                    list.put(b, new HashSet<Character>());
                HashSet<Character> m = (HashSet<Character>) list.get(b);
                m.add(a);
                list.put(b, m);
            }
            if(checkPath(path.charAt(0), path.charAt(1), list, new HashSet<Character>()))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
    private static boolean checkPath(char a, char b, HashMap<Character, HashSet<Character>> list, HashSet<Character> map){
        if(a == b)
            return true;
        if(map.contains(a))
            return false;
        map.add(a);
        if(list.get(a) == null)
            return false;
        HashSet<Character> m = list.get(a);
        boolean t = false;
        for(char p: m){
            if(checkPath(p, b, list, map))
                t = true;
        }
        return t;
    }
}