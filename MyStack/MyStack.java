import java.util.EmptyStackException;

public class MyStack {

    private Integer[] stack;
    private int size;

    public MyStack(){
        this(2);
    }

    public MyStack(int initCap){
        stack = new Integer[initCap];
        size = 0;
    }

    boolean isEmpty(){
        return size == 0;
    }

    Integer peek(){
	if(size == 0)
            throw new EmptyStackException();
        return stack[size - 1];
    }

    Integer pop(){
        if(size == 0)
            throw new EmptyStackException();
        int temp = stack[size - 1];
        stack[size - 1] = null;
        size--;
        return temp;
    }

    void push(Integer item){
        if (stack.length == size)
            doubleCapacity();
        stack[size] = item;
        size++;
    }

    private void doubleCapacity() {
        Integer[] temp = new Integer[stack.length * 2];
        for (int i = 0; i < size; i++)
            temp[i] = stack[i];
        stack = temp;
    }

    public String toString(){
        String c = "";
        for(int i = size - 1; i > 0; i--) {
            if (stack[i] != null)
                c += stack[i] + "\n";
        }
        c += stack[0];
        return c;
    }

}
