public class Employee {
    private String name;
    private int id;


    public Employee(String name, int id){
        this.name = name;
        this.id = id;
    }
    @Override
    public String toString(){
        return name + " " + id;
    }

    @Override
    public boolean equals(Object other){
        if(this.id == ((Employee)(other)).id && this.name.equals(((Employee)(other)).name)){
            return true;
        } return false;
    }

    public int hashCode(){
        return this.id;
    }

}
