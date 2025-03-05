package Model.ModelItems;

public class Food extends Item {
    public Food(String name){
        super(name,"-It is food that you can use to increase your health points");

    }

    //TODO make controller interact with model for this
   /* public void describe() {
        System.out.println("-It is food that you can use to increase your health points");
    }*/

    //TODO make controller interact with model for this
    public void taken(){
        //System.out.println("FOOD TAKEN");
    }

}
