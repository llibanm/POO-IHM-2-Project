package model.modelCharacter;







import model.modelItems.Back_pack;
import model.modelItems.Sabre;
import model.modelMap.Location;
import model.modelCharacter.Bow;
import java.util.ArrayList;

public class Hero extends MyCharacter{


    private model.modelCharacter.Bow H_bow;
    private Sabre H_sabre;
    private Back_pack backpack;
    private Location H_position;

    //TODO make controller interact with model for this
   /* @Override
    public void describe() {
        System.out.println("Your character is the hero, he had a bow and a sabre and back_pack full of items." +
                " your health points : " + this.getHP());
    }*/

    public Hero(String name, int hp, Location loc){
        super(name, hp);
        H_bow = new Bow();
        H_sabre = new Sabre();
        backpack = new Back_pack();
        H_position = loc;

    }

   /* @Override
    public void printCharacter() {
        System.out.println(getName());
    }*/


    public boolean CanAttaqueWithAr(){
        return (H_bow.getNbArrows() > 0);
    }
    public boolean CanAttaqueWithBAr(){
        return (H_bow.getNbBurningArrows() > 0);
    }



    public void loseArrow(){
        H_bow.remove_arrows();
    }
    public void loseBurningArrows(){H_bow.remove_burning_arrows();}



    public void setPosition(String position){

        if (H_position.isContainExit(position) && H_position.getExit(position).hero_unlock(this)){
            H_position = H_position.getExit(position).getneighbor();
        }
        else{
            if (!H_position.isContainExit(position)) {
                // TODO controller for this
               // System.out.println("No exit to this location, check your entry");
            }
        }
    }



    public void addItemToBackpack(String name) {
        if (H_position.isContainsItem(name)) {
            backpack.add_item(H_position.getItem(name));
            H_position.getItem(name).taken();
            H_position.remItem(name);

        } else {// TODO controller for this
            // System.out.println("ITEM NOT FOUND");}
        }
    }

    @Override
    public void BeAttacked(String weapon, String arg, Hero hero) {
        decreaseHP(2);
    }

    public Location getPosition(){
        return H_position;
    }

    public Back_pack getBackpack() {
        return backpack;
    }
    public Bow getH_bow(){
        return H_bow;
    }

    public Sabre getH_sabre(){
        return H_sabre;
    }

    public void display_help(){}

    public void attack(MyCharacter hero){}

    public void getattacked(){
        ArrayList<MyCharacter> enemies = this.getPosition().getCharacters();
        for(MyCharacter enemy : enemies){

            enemy.attack(this);
        }
    }

    public void mission(Hero hero){}

}
