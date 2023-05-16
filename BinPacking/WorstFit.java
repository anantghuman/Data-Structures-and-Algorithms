import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
public class WorstFit{
    public void WorstFitCalc(ArrayList<Integer> f) {
        Collections.sort(f);
        Collections.reverse(f);
        int total = 0, id = 0;
        Disk d;
        PriorityQueue<Disk> disks = new PriorityQueue<>(Comparator.naturalOrder());
        for (Integer integer : f) {
            if (!disks.isEmpty() && disks.peek().getStorage() >= integer)
                d = disks.poll();
            else {
                d = new Disk(id);
                id++;
            }
            d.setStorage(d.getStorage() - integer);
            d.getFiles().add(integer);
            disks.add(d);
            total += integer;
        }
        if(disks.size() < 100) {
            System.out.print("Total size = " + total/1000000.0 + " GB" + "\nDisks req'd = " + disks.size());
            while(disks.size() > 0) {
                d = disks.poll();
                System.out.print("\n\t" + d.getId() + " " + d.getStorage() + ": ");
                for (int i = 0; i < d.getFiles().size(); i++)
                    System.out.print(d.getFiles().get(i) + " ");
            }
        }
    }
}
