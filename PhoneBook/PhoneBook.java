public class PhoneBook implements IMap {
    private Entry[] arr;
    private int size;

    private static final int CAPACITY = 11;

    public PhoneBook() {
        this(CAPACITY);
    }

    public PhoneBook(int size) {
        arr = new Entry[size];
        this.size = 0;
    }

    private int hashFunction(Person p) {
        return Math.abs(p.hashCode()) % arr.length;
    }

    @Override
    public PhoneNumber put(Person person, PhoneNumber phone) {
        int index = hashFunction(person);
        Entry temp = new Entry(person, phone);
        if (arr[index] == null) {
            arr[index] = temp;
            size++;
            return null;
        }
        if (arr[index].equals(temp)) {
            PhoneNumber t = arr[index].getPhonenumber();
            arr[index].setPhoneNumber(phone);
            return t;
        } else {
            Entry j = arr[index];
            while (j.next != null) {
                j = j.next;
                if (temp.equals(j)) {
                    PhoneNumber t = arr[index].getPhonenumber();
                    arr[index].setPhoneNumber(phone);
                    return t;
                }
            }
            j.next = temp;
            size++;
            return null;
        }
    }

    @Override
    public PhoneNumber get(Person person) {
        int index = hashFunction(person);
        if(arr[index] == null) {
            return null;
        }
        if (arr[index].getPerson().equals(person)) {
            return ((Entry)arr[index]).getPhonenumber();
        } else {
            Entry j = arr[index];
            while (j.next != null) {
                j = j.next;
                if (j.getPerson().equals(person)) {
                    return j.getPhonenumber();
                }
            }
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public PhoneNumber remove(Person person) {
        int index = hashFunction(person);
        if (arr[index] == null) {
            return null;
        }
        if (arr[index].getPerson().equals(person)) {
            if(arr[index].next == null)
                arr[index] = null;
            else
                arr[index] = arr[index].next;
            size--;
            return arr[index].getPhonenumber();
        } else {
            Entry j = arr[index];
            while (j.next != null) {
                Entry a = j.next;
                if (a.getPerson().equals(person)) {
                    j.next = a.next;
                    size--;
                    return j.getPhonenumber();
                }
                j = j.next;
            }
            return null;
        }
    }
    public class Entry {
        public Person p;
        public PhoneNumber pn;
        public Entry next;

        public Entry(Person p, PhoneNumber pn){
            this.p = p;
            this.pn = pn;
            next = null;
        }

        public PhoneNumber getPhonenumber() {
            return pn;
        }

        public Person getPerson() {
            return p;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public void setPhoneNumber(PhoneNumber pn) {
            this.pn = pn;
        }

        @Override
        public boolean equals(Object other) {
            return this.p.equals(((Entry)other).p);
        }

    }

}
