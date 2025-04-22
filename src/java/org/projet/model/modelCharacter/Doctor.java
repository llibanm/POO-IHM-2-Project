package src.java.org.projet.model.modelCharacter;

public class Doctor extends MyCharacter{
    // THIS CHARACTER IS A FRIEND OF THE HERO,
    // HE GIVES HIM 3 LIFE POINTS WHEN HE MEETS HIM IN A LOCATION
    public Doctor(String name, int hp){
        super(name, hp);
    }


    public void printCharacter(){
        System.out.println(getName());
    }

    //TODO make controller interact with model for this
    /*@Override
    public void describe(){
        System.out.println("He is your friend, he gives you 3 life points when you meet him");
    }*/

    @Override
    public void BeAttacked(String weapon, String arg, Hero hero){}

    public void display_help(){}

    public void attack(MyCharacter hero){}
    public void mission(Hero hero){
        hero.increaseHP(2);
        //System.out.println("There is a doctor in " + hero.getPosition().getName() + ", he increased you life points by 2.");
        //TODO make controller interact with model for this
    }

}
