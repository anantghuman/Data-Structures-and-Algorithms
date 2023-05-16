public class MyLinkedList<T> {

    private ListNode head;
    private ListNode tail;
    private int size;

    private class ListNode {
        T val;
        ListNode next;

        public ListNode(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }

    MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    MyLinkedList(T val) {
        add(val);
    }

    @SafeVarargs
    public MyLinkedList(T... vals){
        for(T temp: vals){
            add(temp);
        }
    }

    void add(T newVal) {
        if (head == null){
            head = new ListNode(newVal);
            tail = head;
        } else{
            tail.next = new ListNode(newVal);
            tail = tail.next;
        }
        size++;
    }
    boolean contains(T target){
        ListNode temp = head;
        while(temp != null) {
            if (temp.val == target)
                return true;
            temp = temp.next;
        }
        return false;
    }
    T get(int index) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            if (count == index)
                return temp.val;
            count++;
            temp = temp.next;
        }
        throw new IndexOutOfBoundsException();
    }

    int indexOf(T target){
        ListNode temp = head;
        int count = 0;
        while(temp != null){
            if(temp.val == target)
                return count;
            temp = temp.next;
            count++;
        }
        return -1;
    }

    void set(T newVal, int index){
        ListNode temp = head;
        int count = 0;

        while (temp != null) {
            if (count == index){
                temp.val = newVal;
                return;
            }
            count++;
            temp = temp.next;
        }
    }
    int size() {
        return size;
    }
    int sizeRecursive(ListNode i){
        if(i != null)
            return 1 + sizeRecursive(i.next);
        return 0;
    }
    boolean isEmpty() {
        return head == null;
    }
    T remove(int index){
        ListNode temp = head;
        ListNode i = head;
        int count = 0;
        if(index == 0) {
            head = temp.next;
            size--;
            return temp.val;
        }
        while (temp != null) {
            if (count == index) {
                i.next = temp.next;
                size--;
                return temp.val;
            }
            i = temp;
            temp = temp.next;
            count++;
        }
        throw new IndexOutOfBoundsException(); //if index is not valid
    }
    void add(T newVal, int index){
        ListNode temp = head;
        ListNode i = new ListNode(newVal);
        int count = 0;
        if(index == 0) {
            i.next = this.head;
            this.head = i;
            size++;
            return;
        }
        while(count != (index -1)) {
            temp = temp.next;
            count++;
        }
        i.next = temp.next;
        temp.next = i;
        size++;
    }
    public String toString(){
        String c = "[";
        ListNode temp = head;
        while(temp != null){
            c += temp.val + ", ";
            temp = temp.next;
        }
        if(c.length() > 2)
            c = c.substring(0,c.length()-2);
        c += "]";
        return c;
    }

    void clear(){
        head = null;
        tail = null;
        size = 0;
    }
}
