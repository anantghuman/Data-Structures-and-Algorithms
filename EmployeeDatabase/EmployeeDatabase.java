import java.util.Arrays;

public class EmployeeDatabase {
    private int size;
    private Employee[] arr;
    private static final int CAPACITY = 11;
    public EmployeeDatabase() {
        this(CAPACITY);
    }

    public EmployeeDatabase(int size) {
        this.size = 0;
        arr = new Employee[size];
    }

    public int hashFunction(Employee key){
        return key.hashCode() % arr.length;
    }

    boolean add(Employee key){
        if(size == arr.length)
            return false;
        if(key == null || contains(key)){
            return false;
        }
        int temp = hashFunction(key);
        for(int i = 1; i < arr.length + 1; i++){
            if(arr[temp % arr.length] == null){
                arr[temp % arr.length] = key;
                size++;
                return true;
            }
            /* linear probing
                temp++;
            */
            // quadratic probing
            temp = hashFunction(key) + i*i;
        }
        return false;
    }
    boolean contains(Employee key) {
        if(key == null)
            return false;
        int temp = hashFunction(key);
        for(int i = 1; i < arr.length + 1; i++){
            if(arr[temp % arr.length] == null)
                return false;
            else if(arr[temp % arr.length].equals(key))
                return true;
            /* linear probing
                temp++;
            */
            // quadratic probing
            temp = hashFunction(key) + i*i;
        } return false;
    }
    boolean remove(Employee key){
        if(key == null || !contains(key))
            return false;
        int temp = hashFunction(key);
        for(int i = 1; i < arr.length + 1; i++){
            if(arr[temp].equals(key)) {
                arr[temp] = null;
                rehash();
                return true;
            }
            /* linear probing
                temp++;
            */
            // quadratic probing
            temp = hashFunction(key) + i*i;
        } return false;
    }

    private void rehash(){
        Employee[] a = Arrays.copyOf(arr, arr.length);
        arr = new Employee[a.length];
        for(Employee i: a){
            add(i);
        }
    }

    @Override
    public String toString(){
        return Arrays.toString(arr);
    }

    int size(){
        return size;
    }

}
