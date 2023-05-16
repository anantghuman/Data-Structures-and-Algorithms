import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BinaryMaze {
    public static void main(String[] args) {
        Scanner console = null;
        try {
            console = new Scanner(new File("maze.dat"));
        } catch (FileNotFoundException e) {
            System.out.println("There is an error with the file");
        }
        int col = console.nextInt();
        int row = console.nextInt();
        int l = console.nextInt();
        Integer[][] arr = new Integer[col][row];
        for(int i = 0; i < col; i++){
            for(int k = 0; k < row; k++) {
                arr[i][k] = console.nextInt();
            }
            console.nextLine();
        }


        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        while(l > 0){
            l--;
            HashSet<Coord> set = new HashSet<Coord>();
            int a = console.nextInt();
            int b = console.nextInt();
            Coord h = new Coord(a,b);
            Queue<Node> queue = new LinkedList<>();
            int fy = console.nextInt();
            int fx = console.nextInt();
            if(arr[fy][fx] == 0) {
                System.out.println("-1");
            }
            else {
                queue.offer(new Node(h, 0));
                set.add(h);
            }
            int distance = -1;
            while(queue.size() != 0) {
                Node o = queue.poll();
                Coord j = o.xy;
                if (j.x == fx && j.y == fy) //assumes that start and end are not the same
                    distance = o.distance;
                for (int i = 0; i < 4; i++) {
                    int y = j.y + dir[i][0];
                    int x = j.x + dir[i][1];
                    if (y >= 0 && x >= 0 && x < row && y < col && arr[y][x] != 0) {
                        Coord d = new Coord(y, x);
                        if(!set.contains(d)) {
                            queue.offer(new Node(d, o.distance + 1));
                            set.add(d);
                        }
                    }
                }
            }
            System.out.println(distance);
        }

    }

public static class Node{
        Coord xy;
        int distance;
        public Node(Coord xy, int distance){
            this.distance = distance;
            this.xy = xy;
        }
}
public static class Coord{
    int x, y;
    public Coord(int y, int x){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Coord)obj).x && this.y == ((Coord)obj).y;
    }

    @Override
    public int hashCode() {
        return y;
    }

    @Override
    public String toString() {
        return y + " " + x;
    }
}
}