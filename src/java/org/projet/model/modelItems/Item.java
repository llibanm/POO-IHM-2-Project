package src.java.org.projet.model.modelItems;



public abstract class Item {
    private final String name;
    private String description;

    public Item(String name, String d){
        this.name = name;
        this.description = d;
    }

    public void setDescription(String d){
        description = d;
    }

    public void printItem(){
        System.out.println(this.name);
    }

    public String getName(){
        return name;
    }

    public abstract void taken();

}
