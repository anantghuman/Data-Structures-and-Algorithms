public class Tour
{
    private Node head;
    private int size;

    private class Node {
        Point val;
        Node next;
        public Node(Point v){
            val = v;
        }
        public String toString(){
            return "" + val;
        }
    }
    /** create an empty tour */
    public Tour()
    {
        head = null;
    }

    /** create a four-point tour, for debugging */
    public Tour(Point a, Point b, Point c, Point d)
    {
        add(a);
        add(b);
        add(c);
        add(d);
        head.next.next.next.next = head;
    }

    private void add(Point x){
        if(head == null)
            head = new Node(x);
        else{
            Node temp = head;
            while(temp.next != null)
                temp = temp.next;
            temp.next = new Node(x);
        }
        size++;
    }

    /** print tour (one point per line) to std output */
    public void show()
    {
            Node temp = head;
            for(int i = 0; i < size; i++){
                System.out.println(temp);
                temp = temp.next;
        }
    }

    /** draw the tour using StdDraw */
    public void draw()
    {
        Node temp = head;
        do {
            temp.val.drawTo(temp.next.val);
            temp = temp.next;
        } while(temp != head);
    }

    /** return number of nodes in the tour */
    public int size() {
        return size;
    }

    /** return the total distance "traveled", from start to all nodes and back to start */
    public double distance()
    {
        if(head == null || head.next == head)
            return 0;
        double d = head.val.distanceTo(head.next.val);
        Node temp = head.next;
        while(temp != head.next) {
            d += temp.val.distanceTo(temp.next.val);
        }
        return d;
    }

    /** insert p using nearest neighbor heuristic */
    public void insertNearest(Point p)
    {
        Node node = new Node(p);
        if(head == null) {
            head = node;
            head.next = head;
            return;
        }
        Node n;
        if(head.next.equals(head)) {
            n = head;
        }
        else {
            double dis = head.val.distanceTo(p);
            n = head;
            Node temp = head.next;
            while(temp != head.next) {
                double d = p.distanceTo(temp.val);
                if (d < dis) {
                    dis = d;
                    n = temp;
                }
                temp = temp.next;
            }
        }
        node.next = n.next;
        n.next = node;
        size++;
    }

    /** insert p using smallest increase heuristic */
    public void insertSmallest(Point p)
    {
        Node node = new Node(p);
        if(head == null) {
            head = node;
            head.next = head;
            return;
        }

        Node i;
        if(head.next == head) {
            i = head;
        }
        else {
            double distance = head.val.distanceTo(p) + p.distanceTo(head.next.val) - head.val.distanceTo(head.next.val);
            i = head;
            Node temp = head.next;
            while(temp != head) {
                double d = temp.val.distanceTo(p) + p.distanceTo(temp.next.val) - temp.val.distanceTo(temp.next.val);
                if (d < distance) {
                    distance = d;
                    i = temp;
                }
                temp = temp.next;
            }
        }
        node.next = i.next;
        i.next = node;
        size++;
    }

    public static void main(String[] args) {
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);
        Tour squareTour = new Tour(a, b, c, d);
        squareTour.show();
        System.out.println(squareTour.distance());
        squareTour.draw();
    }
}