public class Entry<K,V> {
    public K p;
    public V pn;
    public Entry next;

    public Entry(K p, V pn){
        this.p = p;
        this.pn = pn;
        next = null;
    }

    public void setValue(V pn) {
        this.pn = pn;
    }

    public V getValue() {
        return pn;
    }

    public K getKey() {
        return p;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object other) {
        return this.p.equals(((Entry)other).p);
    }

}
