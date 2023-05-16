public class Person {
    private String name;

    public Person(String name){
        this.name = name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other){
        return this.name.equals(((Person)other).name);
    }
}