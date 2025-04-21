package model.modelMatrixGame;

import interfaces.EffectOnPlayer;
import model.modelCharacter.Hero;
import model.modelMap.Location;

class Case {

    // Item qui aura un effet spécifique sur le joueur lorsqu'ils sera proche de lui(volontairement ou non)
    // ex: augmenter/reduire ses pdv pour potion, lui faire changer de location,...
    // chaque item et charactère devra redéfinir la méthode effect() de l'interface EffectOnPlayer
    // pour définir son effet sur le joueur
    private EffectOnPlayer element;

    public boolean isOccupied(){
        return element != null;
    }
    public void setElement(EffectOnPlayer element){
        this.element = element;
    }

    public void performEffectOnHero(){
        element.effect();
    }
}



public class GameMatrix {
    private Case[][] matrix;
    private Hero hero;
    private Location mainLocation;

    public GameMatrix(int nbRow, int nbColumn) {
        Case[][] matrix = new Case[nbRow][nbColumn];
        initMatrix();
        mainLocation = new Location("home", "first location", "go");
    }

    public void initMatrix(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Case();
            }
        }
        hero = new Hero("Mars",100, mainLocation);
        //setItemOnCase(0,0, hero);





    }

    //Ajouter un Item sur une case de la matrice, qui aura un effet sur le joueur
    public void setItemOnCase(int row, int col, EffectOnPlayer item){
        matrix[row][col].setElement(item);
    }
}
