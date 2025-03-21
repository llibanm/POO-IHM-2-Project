package Model.ModelCharacter;

public class Drone extends MyCharacter{

    public Drone(String name, int hp){
        super(name, hp);
    }


    //TODO controller/view for this
    /*@Override
    public void describe() {
        System.out.println("The drone is your enemy, he decreases your health points by attacking you" +
                            "you can only hit him with your bow. \nNB HP : " + getHP());
    }

        //TODO make controller interact with model for this
    @Override
    public void printCharacter(){
        System.out.println(getName());

    }*/


    public void attaqueDroneWithAr(Hero hero) {
        if (hero.CanAttaqueWithAr()){
            decreaseHP(3);
            hero.loseArrow();
        }
        else {//System.out.println("No ARROWS for the bow");
            //TODO make controller interact with model for this
        }
    }
    public void attaqueDroneWithBAr(Hero hero) {
        if (hero.CanAttaqueWithBAr()){
            decreaseHP(6);
            hero.loseBurningArrows();
        }
        else{
            //System.out.println("No BURNING ARROWS for the bow");
            //TODO make controller interact with model for this
        }
    }

    @Override
    public void BeAttacked(String weapon, String arg, Hero hero) {
        if ((this.getHP() > 0) && (weapon.equals("BOW"))) {
            if (arg.equals("ARROW")) {
                attaqueDroneWithAr(hero);
            }

            if (arg.equals("BURNINGARROW")) {
                attaqueDroneWithBAr(hero);
            }
        }
        else{
            if (arg == null) {
                //System.out.println("You can't attack the drone with this weapon");
                //TODO make controller interact with model for this
            }
            else {
                //System.out.println("the drone has no more health points. And the bow can only be used with its arrows");
                //TODO make controller interact with model for this
            }
        }
    }
    public void display_help(){}

    public void attack(MyCharacter hero){
        if (this.getHP() > 0) {
            hero.decreaseHP(2);
           // System.out.println("you've been attacked by a drone");
            //TODO make controller interact with model for this
        }
        else {//System.out.println("drone's out")
            //TODO make controller interact with model for this
        ;}

    }

    public void mission(Hero hero){}

}
