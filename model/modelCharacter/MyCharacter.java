package model.modelCharacter;


public abstract class MyCharacter {
    private final String name;
    private int HP;

    public MyCharacter(String name, int hp) {
        this.name = name;
        this.HP = hp;
    }

    public void decreaseHP(int nbp){
        this.HP -= nbp;
    }

    public void increaseHP(int nbp){
        this.HP += nbp;
    }

//    public abstract void printCharacter();
    public abstract void BeAttacked(String weapon, String arg, Hero hero);

    public int getHP() {
        return HP;
    }

    public String getName() {
        return name;
    }

    public abstract void display_help();

    public abstract void mission(Hero hero);
    public abstract void attack(MyCharacter hero);

}
