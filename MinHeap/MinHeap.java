import java.sql.Array;
import java.util.Arrays;

public class MinHeap{

    private Integer[] heap;
    private int size;
    static final int DEFAULT_CAPACITY = 8;

     public MinHeap(int size){
         heap = new Integer[size];
     }

     public MinHeap(){
        this(DEFAULT_CAPACITY);
     }

     public MinHeap(Integer... vals){
         heap = new Integer[vals.length];
         for(int i = 0; i < vals.length; i++){
             size++;
             if(size ==  heap.length)
                 doubleCapacity();
             heap[i+1] = vals[i];
         }
         buildHeap();
     }

     private void buildHeap(){
         for(int i = size/2; i > 0; i--)
             siftDown(i);
     }

     public int getSize(){
         return size;
     }

     public boolean isEmpty(){
         return size == 0;
     }

     public int peekMinimum(){
         return heap[1];
     }

     public int getLeftChild(int index){
         return index * 2;
     }

     public int getRightChild(int index){
         return index * 2 + 1;
     }

     public int getParentIndex(int index){
         return index / 2;
     }

     private void doubleCapacity(){
         Integer[] temp = new Integer[heap.length * 2];
         for(int i = 1; i < size; i++){
             temp[i] = heap[i];
         }
         heap = temp;
     }

     public void insert(int value){
         size++;
         if(size == heap.length)
             doubleCapacity();
         heap[size] = value;
         bubbleUp(size);
     }
     private void bubbleUp(int index){
        int parent = getParentIndex(index);
        if(parent < 1)
            return;
        if(heap[parent] > heap[index]) {
            int temp = heap[parent];
            heap[parent] = heap[index];
            heap[index] = temp;
        }
        bubbleUp(parent);
     }

     public int popMinimum(){
         int temp = heap[1];
         heap[1] = heap[size];
         heap[size] = null;
         size--;
         siftDown(1);
         return temp;
     }

     private void siftDown(int index){
         int left = getLeftChild(index);
         int right = getRightChild(index);
         if(left > size)
             return;
         if(right > size) {
             if (heap[left] < heap[index]) {
                 int temp = heap[index];
                 heap[index] = heap[left];
                 heap[left] = temp;
                 siftDown(left);
             }
         }
         else if(heap[left] < heap[right] && heap[left] < heap[index]){
             int temp = heap[index];
             heap[index] = heap[left];
             heap[left] = temp;
             siftDown(left);
         }
         else if(heap[left] > heap[right] && heap[right] < heap[index]) {
             int temp = heap[index];
             heap[index] = heap[right];
             heap[right] = temp;
             siftDown(right);
         }
     }
     @Override
     public String toString()
     {
        String output = "";
        for (int i = 1; i <= getSize(); i++)
            output += heap[i] + ", ";
        return output.substring(0, output.lastIndexOf(","));
     }

    public void display(){
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j <= this.getSize())
        {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');
            System.out.print((heap[j] == null)? "" : heap[j]);
            if (++column == itemsPerRow) {
            nBlanks /= 2;
            itemsPerRow *= 2;
            column = 0;
            System.out.println();
        } else
            for (int k = 0; k < nBlanks * 2 - 2; k++)
                System.out.print(' ');
            j++;
        }
        System.out.println("\n" + dots + dots);
    }
}
