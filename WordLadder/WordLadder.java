import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        File input = new File("");
        File dictionary = new File("");
        HashSet<String> fullDict = new HashSet<>();
        HashSet<String> dict = new HashSet<>();
        HashSet<String> wordUsed = new HashSet<>();
        Scanner console;
        Scanner con;
        try {
            input = new File("input.txt");
            dictionary = new File("dictionary.txt");
        } catch (Exception e) {
            System.out.println("Input or Dictionary is invalid");
        }
        try {
            con = new Scanner(input);
            console = new Scanner(dictionary);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String a = "";
        Queue<Stack> wordLadder = new LinkedList<>();
        while(console.hasNext())
            fullDict.add(console.next().toLowerCase());
        while (con.hasNext()) {
            String start = con.next();
            String end = con.next();
            wordUsed = new HashSet<>();
            wordLadder = new LinkedList<>();
            dict = new HashSet<>();
            boolean isLadderFound = false;
            if(!fullDict.contains(start) || !fullDict.contains(end)){
                System.out.println("No ladder between " + start + " and " + end);
                continue;
            }
            for(String q: fullDict) {
                a = q.toLowerCase();
                if (start.length() != end.length()) {
                    System.out.println("No ladder between " + start + " and " + end);
                    break;
                }
                if (a.length() != start.length()) {
                    continue;
                }
                if (start.equals(end)) {
                    System.out.println("Found a ladder! >>> [" + start + "]");
                    isLadderFound = true;
                    break;
                }
                dict.add(a);
                if (oneDifference(a, start)) {
                    if (a.equals(end)) {
                        System.out.println("Found a ladder! >>> [" + start + ", " + end + "]");
                        isLadderFound = true;
                        wordLadder = new LinkedList<>();
                        break;
                    }
                    Stack<String> temp = new Stack<>();
                    wordUsed.add(a);
                    temp.push(start);
                    temp.push(a);
                    wordLadder.offer(temp);
                }
            }


            while (!wordLadder.isEmpty()) {
                Stack<String> k = wordLadder.poll();
                a = (String) k.peek();
                Stack<String> l;
                for (String j: dict) {
                    if (oneDifference(a, j) && !wordUsed.contains(j)) {
                        l = deepCopy(k);
                        wordUsed.add(j);
                        l.push(j);
                        wordLadder.offer(l);
                    } else
                        continue;
                    //System.out.println(j);
                    if (j.equals(end)) {;
                        System.out.println("Found a ladder! >>> " + l);
                        isLadderFound = true;
                        break;
                    }
                }
            }
            if (!isLadderFound)
                System.out.println("No ladder between " + start + " and " + end);

        }
    }


    private static Stack<String> deepCopy(Stack<String> k){
        Stack<String> t = (Stack<String>) k.clone();
        Stack<String> c = new Stack<>();
        Stack<String> f = new Stack<>();
        while(!t.isEmpty())
            c.push(t.pop());
        while(!c.isEmpty())
            f.push(c.pop());
        return f;
    }
    private static boolean oneDifference(String input, String test){
        int diff = 0;
        for(int i = 0; i < input.length(); i++)
            if(input.charAt(i) != test.charAt(i) && input.length() == test.length())
                diff++;
        return diff == 1;
    }


}
