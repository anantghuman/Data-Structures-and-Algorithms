import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>
{

    int ID;
    int x;
    int y;
    List<Integer> edges;
    boolean visited;
    double distance;

    public Vertex(int ID, int x, int y)
    {
        this.ID = ID;
        this.x = x;
        this.y = y;
        visited = false;
        distance = Double.POSITIVE_INFINITY;
        edges = new ArrayList<Integer>();
    }

    public double getDistance(Vertex other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    @Override
    public int compareTo(Vertex other) {
        return (int) (distance - other.distance);
    }
}