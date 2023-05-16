import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
        public static void main(String[] args) {
            try {
                Scanner console = new Scanner(new File("input20.txt"));
                ArrayList<Integer> f = new ArrayList<>();
                while(console.hasNext())
                    f.add(console.nextInt());
                WorstFit i = new WorstFit();
                i.WorstFitCalc(f);
            } catch(Exception ex){
                System.out.println("File not found!");
        }
        }
}
