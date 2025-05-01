package src.java.org.projet.model.modelCharacter;
import com.fasterxml.jackson.annotation.JsonProperty;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.services.SpriteService;
import src.java.org.projet.interfaces.MoveRangeOnSprite;

import src.java.org.projet.model.modelItems.Bow;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.model.modelMap.Location;

public class Hero extends MyCharacter{

    Dataset dataset = Dataset.getInstance();
    private Bow H_bow;

    @JsonProperty("position")
    private Location H_position;

    public Hero() {
        super("Hero", 0,
                new MoveRangeOnSprite(new Coord(3,-1), new Coord(2,-1),new Coord(1,-1),new Coord(0,-1)),
                new SpriteService(Dataset.getInstance().getString("HEROPNG"),32,48,4,4),
                Dataset.getInstance().getString("DEFAULTHEROPNG")
        );
    }

    public Hero(String name, int hp, Location loc){


        super(name, hp,
                new MoveRangeOnSprite(new Coord(3,-1), new Coord(2,-1),new Coord(1,-1),new Coord(0,-1)),
                new SpriteService(Dataset.getInstance().getString("HEROPNG"),32,48,4,4),
                Dataset.getInstance().getString("DEFAULTHEROPNG")
        );
        H_bow = new Bow(new Coord(0,0));
        H_position = loc;

    }


    public Hero(String name, int hp){
        super(name, hp,
                new MoveRangeOnSprite(new Coord(3,-1), new Coord(2,-1),new Coord(1,-1),new Coord(0,-1)),
                new SpriteService(Dataset.getInstance().getString("HEROPNG"),32,48,4,4),
                Dataset.getInstance().getString("DEFAULTHEROPNG")
        );
        H_bow = new Bow(new Coord(0,0));

    }



    public void setH_bow(Bow h_bow) {
        H_bow = h_bow;
    }


    public Location getH_position() {
        return H_position;
    }

    public void setH_position(Location h_position) {
        H_position = h_position;
    }
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



    @Override
    public void BeAttacked(String weapon, String arg, Hero hero) {
        decreaseHP(2);
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Coord getMoveDirection() {
       return directionToCoord(getLastDirection());
    }

    @Override
    public void setMoveDirection(int deltaRow, int deltaCol) {

    }

    public Location getPosition(){
        return H_position;
    }

    public Bow getH_bow(){
        return H_bow;
    }


    public void display_help(){}
    public void attack(MyCharacter hero){}
    public void mission(Hero hero){}

}