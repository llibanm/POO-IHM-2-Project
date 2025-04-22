
package src.java.org.projet.model.modelCharacter;
public class Agressor extends MyCharacter {


    public Agressor(String name, int hp) {
        super(name, hp);

    }

    /*@Override
    public void describe() {
        System.out.println("He is your enemy, he decreases your health points by attacking you.\nNB HP : " + getHP());
    }

    public void printCharacter(){
        System.out.println(getName());
    }*/

    public void attaqueAgressorWithAr(Hero hero) {
        if (hero.CanAttaqueWithAr()){
            decreaseHP(3);
            hero.loseArrow();
        }
        else {//System.out.println("No ARROWS for the bow")
        ;}
    }
    public void attaqueAgressorWithBAr(Hero hero) {
        if (hero.CanAttaqueWithBAr()){
            decreaseHP(6);
            hero.loseBurningArrows();
        }
        else{//System.out.println("No BURNING ARROWS for the bow")
        ;}
    }

    @Override
    public void BeAttacked(String weapon, String arg, Hero hero) {
        if ((this.getHP() > 0) && weapon.equals("BOW")) {
            if (arg.equals("ARROW")) {
                attaqueAgressorWithAr(hero);
            }
            if (arg.equals("BURNINGARROW")) {
                attaqueAgressorWithBAr(hero);
            }
        }else{
            if ((weapon.equals("SABRE")) && (this.getHP() > 0)){
                this.decreaseHP(3);
            }
            else {
                //System.out.println("the agressor has no more health points.");
            }
        }
    }


    public void display_help(){}

    public void attack(MyCharacter hero){
        if (this.getHP() > 0) {
            hero.decreaseHP(2);
            //System.out.println("You've been attacked by an agressor" );
        }
        else {//System.out.println("agressor's out")
        ;}
    }

    public void mission(Hero hero){}

}
