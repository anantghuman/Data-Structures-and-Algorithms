import java.util.ArrayList;

public class Catalog {

    private String name;

    private ArrayList<Item> a;


    Catalog(String name){
        this.name = name;
        a = new ArrayList<Item>();
    }

    void add(Item item) {
        a.add(item);
    }

    int size(){
        return a.size();
    }

    Item get(int index){
        return a.get(index);
    }

    String getName(){
        return name;
    }
}
