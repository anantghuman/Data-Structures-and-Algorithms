public class MyHashTable<K,V>{
    private Object[] arr;
    private int size;

    private static final int CAPACITY = 11;

    public MyHashTable() {
        this(CAPACITY);
    }

    public MyHashTable(int size) {
        arr = new Entry[size];
        this.size = 0;
    }

    private int hashFunction(K p) {
        return Math.abs(p.hashCode()) % arr.length;
    }

    public V put(K p, V pn) {
        int index = hashFunction(p);
        Entry temp = new Entry(p, pn);
        if (arr[index] == null) {
            arr[index] = temp;
            size++;
            return null;
        }
        if (arr[index].equals(temp)) {
            V t = (V) ((Entry)arr[index]).getValue();
            ((Entry)arr[index]).setValue(pn);
            return t;
        } else {
            Entry j = (Entry)arr[index];
            while (j.next != null) {
                j = j.next;
                if (temp.equals(j)) {
                    V t = (V) ((Entry)arr[index]).getValue();
                    ((Entry)arr[index]).setValue(pn);
                    return t;
                }
            }
            j.next = temp;
            size++;
            return null;
        }
    }

    public V get(K p) {
        int index = hashFunction(p);
        if(arr[index] == null) {
            return null;
        }
        if (((Entry)arr[index]).getKey().equals(p)) {
            return (V)((Entry)arr[index]).getValue();
        } else {
            Entry j = (Entry)arr[index];
            while (j.next != null) {
                j = j.next;
                if (j.getKey().equals(p)) {
                    return (V)((Entry)j).getValue();
                }
            }
            return null;
        }
    }

    public int size() {
        return size;
    }

    public V remove(K p) {
        int index = hashFunction(p);
        if (arr[index] == null) {
            return null;
        }
        if (((Entry)arr[index]).getKey().equals(p)) {
            V t = (V)((Entry)arr[index]).getValue();
            if((Entry)(arr[index]) == null)
                arr[index] = null;
            else
                arr[index] = ((Entry)arr[index]).next;
            size--;
            return t;
        } else {
            Entry j = (Entry)arr[index];
            while (j.next != null) {
                Entry a = j.next;
                if (((a).getKey().equals(p))) {
                    j.next = a.next;
                    size--;
                    return (V)a.getValue();
                }
                j = j.next;
            }
            return null;
        }
    }

}
