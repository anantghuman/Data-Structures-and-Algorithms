import java.io.File;
import java.util.*;
public class Dijkstra
{

    Graph graph;

    public Dijkstra(Graph g)
    {
        graph = g;
        dijkstra(graph.start);
        System.out.println(graph.v[graph.end].distance);
    }

    private void dijkstra(int source)
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        graph.v[source].distance = 0;
        queue.offer(source);
        while (!queue.isEmpty())
        {
            int temp = queue.poll();
            graph.v[temp].visited = true;
            for (int i = 0; i < graph.v[temp].edges.size(); i++)
            {
                int t = graph.v[temp].edges.get(i);
                if(!graph.v[t].visited)
                    queue.offer(t);
                double d = graph.v[temp].distance + graph.v[temp].getDistance(graph.v[t]);
                double d2 = graph.v[t].distance;
                if (d < d2)
                {
                    graph.v[t].distance = d;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Dijkstra test = new Dijkstra(new Graph());
    }
}
