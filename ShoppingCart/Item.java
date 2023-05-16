public class Item {

    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;
    Item(String name, double price)
    {
        this(name, price, 1, price);
    }

    Item(String name, double price, int bulkQty, double bulkPrice){
        if(price < 0 || bulkQty < 0 || bulkPrice < 0)
            throw new IllegalArgumentException("error");
        this.name = name;
        this.price = price;
        this.bulkQty = bulkQty;
        this.bulkPrice = bulkPrice;
    }

    double priceFor(int quantity){
        if(quantity >= bulkQty)
            return quantity * bulkPrice;
        else return quantity * price;
    }

    @Override
    public boolean equals(Object obj){
        return ((Item)obj).name.equals(this.name);
    }

    @Override
    public String toString(){
        String l = "" + name + ", $" + price;
        if(bulkPrice != price)
            l += " (" + bulkQty + " for " + bulkPrice + ")";
        return l;
    }
}
