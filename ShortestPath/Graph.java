import java.io.File;
import java.util.Scanner;

public class Graph
{

    Vertex[] v;
    int start;
    int end;

    public Graph()
    {
        Scanner console = null;
        try {
            console = new Scanner(new File("input6.txt"));
        } catch (Exception e) {}

        v = new Vertex[console.nextInt()];
        int e = console.nextInt();

        for (int i = 0; i < v.length; i++) {
            v[i] = (new Vertex(console.nextInt(), console.nextInt(), console.nextInt()));
        }

        for (int i = 0; i < e; i++) {
            int a = console.nextInt();
            int b = console.nextInt();
            v[a].edges.add(b);
            v[b].edges.add(a);
        }

        start =  console.nextInt();
        end =  console.nextInt();
    }

    public double distance(int to, int from)
    {
        return v[to].getDistance(v[from]);
    }
}