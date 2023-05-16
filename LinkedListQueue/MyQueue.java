public class MyQueue<T> {

    private MyLinkedList<T> queue;

    public MyQueue(){
        queue = new MyLinkedList<>();
    }

    boolean isEmpty(){
        return queue.size() == 0;
    }

    void offer(T item){
        queue.add(item);
    }

    T poll(){
        return queue.remove(0);
    }

    int size(){
        return queue.size();
    }

    void clear(){
        queue.clear();
    }

}
