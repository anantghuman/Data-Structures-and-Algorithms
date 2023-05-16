import java.util.EmptyStackException;

public class MyStack implements StackADT
{
        private Square[] stack;
        private int size;

        public MyStack(){
            this(2);
        }

        public MyStack(int initCap){
            stack = new Square[initCap];
            size = 0;
        }

        @Override
        public boolean isEmpty(){
            return size == 0;
        }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++)
            stack[i] = null;
    }

    @Override
        public Square peek(){
            if(size == 0)
                throw new EmptyStackException();
            return stack[size - 1];
        }

    @Override
    public int size() {
        return size;
    }

    @Override
        public Square pop(){
            if(size == 0)
                throw new EmptyStackException();
            Square temp = stack[size - 1];
            stack[size - 1] = null;
            size--;
            return temp;
        }

        @Override
        public void push(Square item){
            if (stack.length == size)
                doubleCapacity();
            stack[size] = item;
            size++;
        }

        private void doubleCapacity() {
            Square[] temp = new Square[stack.length * 2];
            for (int i = 0; i < size; i++)
                temp[i] = stack[i];
            stack = temp;
        }

        @Override
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