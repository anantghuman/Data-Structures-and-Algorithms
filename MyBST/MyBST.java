import java.util.LinkedList;
import java.util.Queue;

public class MyBST {
    private BSTNode root;

    private class
    BSTNode {
        Integer val;
        BSTNode left, right;

        public BSTNode(Integer val) {
            this.val = val;
            left = right = null;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }

    public int size() {
        if (root == null)
            return 0;
        return size(root);
    }

    private int size(BSTNode j) {
        if (j.left == null && j.right == null)
            return 1;
        else if (j.left == null)
            return size(j.right) + 1;
        else if (j.right == null)
            return size(j.left) + 1;
        else
            return size(j.left) + size(j.right) + 1;
    }

    public void insert(Integer n) {
        if (root == null) {
            root = new BSTNode(n);
            return;
        }
        insert(n, root);
    }

    private void insert(Integer n, BSTNode j) {
        if (j.val > n)
            if (j.left == null)
                j.left = new BSTNode(n);
            else
                insert(n, j.left);
        else if (j.val < n)
            if (j.right == null)
                j.right = new BSTNode(n);
            else
                insert(n, j.right);
        else return;
    }

    public boolean contains(Integer n) {
        return contains(n, root);
    }

    private boolean contains(Integer n, BSTNode j) {
        if (j.val > n) {
            if (j.left == null)
                return false;
            return contains(n, j.left);
        } else if (j.val < n) {
            if (j.right == null)
                return false;
            return contains(n, j.right);
        }
        return true;
    }

    public Integer getMax() {
        if (root == null)
            return null;
        return getMax(root);
    }

    private Integer getMax(BSTNode j) {
        if (j.right == null)
            return j.val;
        return getMax(j.right);
    }

    public Integer getMin() {
        if (root == null)
            return null;
        return getMin(root);
    }

    private Integer getMin(BSTNode j) {
        if (j.left == null)
            return j.val;
        return getMin(j.left);
    }

    public void delete(Integer n) {
        if(root == null)
            return;
        delete(root, n);
    }

    private BSTNode delete(BSTNode j, int n) {
        if (j == null)
            return null;
        if (j.val > n)
            j.left = delete(j.left, n);
        else if (j.val < n)
            j.right = delete(j.right, n);
        else {
            if (j.left == null && j.right != null)
                j = j.right;
            else if (j.right == null && j.left != null)
                j = j.left;
            else if (j.left == null && j.right == null)
                j = null;
            else{
                j.val = getMin(j.right);
                delete(j.right, j.val);
            }
        }
        return j;
    }


    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(BSTNode j){
        if(j == null)
            return;
        inOrder(j.left);
        System.out.print(j.val + " ");
        inOrder(j.right);
    }

    void print() {
        print(root, 0);
    }
    private void print(BSTNode j, int i){
        if(j == null)
            return;
        print(j.right, i + 1);
        String c = "";
        for(int n = 0; n < i; n++)
            c += "    ";
        c += j.val;
        System.out.println(c);
        print(j.left, i + 1);
    }
}