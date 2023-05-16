import java.util.ArrayList;
public class Disk implements Comparable<Disk> {
        private ArrayList<Integer> files = new ArrayList<>();
        private int storage = 1000000;
        private int id;
        @Override
        public int compareTo(Disk other) {
            if(this.storage < other.storage)
                return 1;
            else
                return -1;
        }
        public Disk(int i) {
            this.id = i;
        }
        public ArrayList<Integer> getFiles() {return files;}
        public int getStorage() {return storage;}
        public int getId() {return id;}
        public void setStorage(int storage) {this.storage = storage;}
}
