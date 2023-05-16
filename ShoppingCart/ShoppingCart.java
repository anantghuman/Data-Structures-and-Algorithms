import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<ItemOrder> a;

    public ShoppingCart(){
        a = new ArrayList<ItemOrder>();
    }

    public void add(ItemOrder itemOrder){
        if(a.contains(itemOrder))
            a.remove(itemOrder);
        a.add(itemOrder);
    }

    public double getTotal(){
        double j = 0;
        for(int i = 0; i < a.size(); i++)
            j += a.get(i).getPrice();
        return j;
    }
}
