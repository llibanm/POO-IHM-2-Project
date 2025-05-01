package src.java.org.projet.model.modelItems.puit;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelItems.Item;

import java.util.HashMap;

public class Back_pack extends Item {
    private HashMap<String,Item> item_list;

    public Back_pack(){
        super("Back_Pack","It is the back_pack of your hero, all of his items are inside " +
                "except the bow and the sabre");
        item_list = new HashMap<>();
    }
    public void add_item(Item item){
        item_list.put(item.getName(),item);
    }

    public void remove_item(String item){
        item_list.remove(item);
    }

    public void print_item_list(){
        for(Item item : item_list.values()){
            item.printItem();
        }
    }

    public void taken(){}

    public boolean contains_item(String name){
        return item_list.containsKey(name);
    }

    //TODO view/controller for this
   /* public void describe() {
        System.out.println("It is the back_pack of your hero, all of his items are inside " +
                            "except the bow and the sabre");
        print_item_list();
    }*/

    public HashMap<String, Item> getItem_list() {
        return item_list;
    }

    @Override
    public void use(GameModel model) {

    }
}