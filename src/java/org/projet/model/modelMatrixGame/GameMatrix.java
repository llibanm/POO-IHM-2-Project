package src.java.org.projet.model.modelMatrixGame;


import src.java.org.projet.interfaces.EffectOnPlayer;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelMap.Location;

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
