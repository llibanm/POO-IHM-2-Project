package src.java.org.projet.model.modelLevelEditor;


import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.model.modelMap.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**Cette classe contient à la fois la map de l'éditeur de niveau pour placer les items
 * et le menu de sélection ou on choisit ces derniers */
public class MatrixLvlEditorModel extends AbstractModel {

    //Matrice de l'éditeur de niveau qui contiendra les items selectionnés dans le menu de choix des items
    private CaseMatrix[][] matrixEditorLvl;
    Hero hero;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Ennemy> getEnnemies() {
        return ennemies;
    }

    public void setEnnemies(List<Ennemy> ennemies) {
        this.ennemies = ennemies;
    }

    List<Ennemy> ennemies = new ArrayList<Ennemy>();

    int nbOfRows;
    int nbOfCols;
    String urlBackground;
    public MatrixLvlEditorModel(){
        super("Matrix Lvl Editor");
    }
    public MatrixLvlEditorModel(int nbOfRows, int nbOfCols, String urlBackground) {
        super("Matrix Lvl Editor");
        //Ajout de la liste d'items du menu de choix des items dans la casse parent

        if (nbOfRows <= 0 || nbOfCols <= 0) {
            throw new IllegalArgumentException("Le nombre de lignes et de colonnes doit être supérieur à zéro.");
        }
        this.nbOfRows = nbOfRows;
        this.nbOfCols = nbOfCols;
        this.matrixEditorLvl = new CaseMatrix[nbOfRows][nbOfCols];
        this.urlBackground = urlBackground; //Chemin vers l'image de l'arrière-plan de la grille

        initCaseMatrixEditorLvl();
    }

    public CaseMatrix[][] getMatrixEditorLvl() {
        return matrixEditorLvl;
    }

    public void setMatrixEditorLvl(CaseMatrix[][] matrixEditorLvl) {
        this.matrixEditorLvl = matrixEditorLvl;
    }

    public void setNbOfRows(int nbOfRows) {
        this.nbOfRows = nbOfRows;
    }

    public void setNbOfCols(int nbOfCols) {
        this.nbOfCols = nbOfCols;
    }


    public String getUrlBackground() {
        return urlBackground;
    }

    public void setUrlBackground(String urlBackground) {
        this.urlBackground = urlBackground;
    }



    /**
     * Teste la validité des coordonnées
     * @param row ligne
     * @param col colonne
     * @return si la la coordonnée est bien délimité dans la grille
     */
    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < nbOfRows && col >= 0 && col < nbOfCols;
    }

    public void initCaseMatrixEditorLvl() {
        for (int i = 0; i < nbOfRows; i++) {
            for (int j = 0; j < nbOfCols; j++) {
                matrixEditorLvl[i][j] = new CaseMatrix("",i,j);
            }
        }
    }

    /**
     *Ajouter un item sur la Matrice
     * @param row
     * @param col
     * @param items La représentation de l'items(url image, coordonnées, Classe liée)
     */
    public void addItemMatrice(int row, int col, CaseMatrix items) {
        System.out.println("Ajout item addItemMatrice");
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col]=items;
            matrixEditorLvl[row][col].setOccuped(true);
            fillHeroAndEnnemyList( row, col,items);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour ajouter l'item (" + row + ", " + col + ").");
            System.out.println("nbofRows=" + nbOfRows + ", nbOfCols=" + nbOfCols);
            throw new IllegalArgumentException("Erreur: Coordonnées invalides pour ajouter l'item ");
        }
    }

    public boolean isOccupedCase(int row, int col) {
       return this.getCaseMatrix(row, col).isOccuped();
    }

    public void setOccuped(Coord newPos, boolean occuped) {
        this.getCaseMatrix(newPos.getRow(), newPos.getCol()).setOccuped(occuped);
    }

    public boolean moveHero(int rowX, int colY) {
        int rowHero = hero.getCoord().getRow();
        int colHero = hero.getCoord().getCol();
        int row = rowHero + rowX;
        int col = colHero + colY;
        boolean isOccuped = this.getCaseMatrix(row, col).isOccuped();

        if(isValidCoordinate(row, col) && !isOccuped) {
            hero.setCoord(new Coord(rowHero,colHero));
            resetItemMatrice(rowHero,colHero);
            System.out.println("Déplacement du hero, nouvelle coord "+hero.getCoord().getCol()+" et "+hero.getCoord().getRow());

        return true;}
        else {
            System.out.println("Le héros ne peut pas se déplacer");
            return false;
        }

    }



    public void fillHeroAndEnnemyList(int row, int col,CaseMatrix items) {
        Object classOfItems = items.getClassOfItems();
        System.out.println("fillHeroAndEnnemyList "+classOfItems);

        if (classOfItems instanceof Hero) {
            this.hero = (Hero) classOfItems;
            hero.setCoord(new Coord(row, col));
            System.out.println("Ajout du héro  fillHeroAndEnnemyList");
        }
        else if (classOfItems instanceof Ennemy) {
            Ennemy ennemy = new Agressor("ee",10); //!!!
            ennemy.setPosition(new Coord(row, col));
            this.ennemies.add(ennemy);
            System.out.println("Ajout d'un ennemie  fillHeroAndEnnemyList+ "+ennemies);
        }
        else {
            System.out.println("L'item n'est ni un heros, ni un ennemi");
        }

    }
    /**
     * Remettre à zero une case de la matrice
     * @param row
     * @param col
     */
    public void resetItemMatrice(int row, int col) {
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col]=new CaseMatrix("",0,0);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour supprimer l'item (" + row + ", " + col + ").");
            throw new IllegalArgumentException(" ");
        }
    }

    public CaseMatrix getCaseMatrix(int row, int col) {return  matrixEditorLvl[row][col];}

    public void addCaseMatrix(int row, int col, CaseMatrix caseMatrix) {
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col]= caseMatrix;
        } else {
            System.err.println("Erreur: Coordonnées invalides pour supprimer l'item (" + row + ", " + col + ").");
            throw new IllegalArgumentException(" ");
        }
    }
    public int getNbOfRows() {return nbOfRows;}
    public int getNbOfCols() {return nbOfCols;}

    @Override
    public String toString() {
        return "LevelEditorModel{\n" +
                "\n, nbOfColsMatrix=" + nbOfCols +
                "\n, nbOfRowsMatrix=" + nbOfRows +
                "\n, matrixEditorLvl=" + Arrays.deepToString(matrixEditorLvl) +
                '}';
    }
}
