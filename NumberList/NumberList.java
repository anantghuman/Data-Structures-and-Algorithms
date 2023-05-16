import java.util.*;
public class NumberList {
    private Integer[] list;
    private int size;

    public NumberList() {
        size = 0;
        list = new Integer[2];

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        String c = "[";
        for(int i = 0; i < size; i++) {
            if (list[i] != null)
                c += list[i] + ", ";
        }
        if(c.length() > 2)
            c = c.substring(0, c.length() - 2);
        c += "]";
        return c;
    }

    private void doubleCapacity() {
        Integer[] temp = new Integer[list.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }

    public void add(int index, Integer a) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (list.length == size)
            doubleCapacity();
        for (int i = size-1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = a;
        size++;
    }

    public boolean add(int a) {
        add(size, a);
        return true;
    }

    public Integer get(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        return list[index];
    }

    public Integer set(int index, int j) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        int temp = list[index];
        list[index] = j;
        return temp;
    }

    public Integer remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Integer temp = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
        return temp;
    }

}
