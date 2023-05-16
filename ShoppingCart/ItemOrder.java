public class ItemOrder {

    private Item item;
    private int qty;

    ItemOrder(Item item, int qty){
        this.item = item;
        this.qty = qty;
    }

    public double getPrice(){
        return item.priceFor(qty);
    }

    public Item getItem(){
        return item;
    }

    public boolean equals(Object obj){
        return ((ItemOrder)obj).item.equals(this.item);
    }
}
