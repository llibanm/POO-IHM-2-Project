package Model.ModelItems;

public class Key extends Item{
    int id;
    String description;

    public Key(String name, String description) {
        super(name,"It's a Key to open a KeyDoor");
        //this.id = id;
        this.description = description;

    }

    public int getId(){
        return id;
    }

    //TODO make controller interact with model for this
    /*@Override
    public void describe() {
        System.out.println("It's a Key to open a KeyDoor");
    }*/

    public void taken(){
        System.out.println("KEY TAKEN");
    }
}
