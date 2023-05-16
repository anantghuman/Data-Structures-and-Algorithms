public class Node implements Comparable<Node>{
    public int val;
    public Character letter;
    public Node left, right;
    public Node(int val, Character letter){
        this.val = val;
        this.letter = letter;
    }
    public Node(int val, Character letter, Node left, Node right){
        this.val = val;
        this.letter = null;
        this.left = left;
        this.right = right;
    }
    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}