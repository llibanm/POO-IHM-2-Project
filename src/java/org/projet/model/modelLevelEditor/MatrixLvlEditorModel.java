package src.java.org.projet.model.modelLevelEditor;


import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveAction;
import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

import java.util.*;
import java.util.logging.Logger;

/**Matrice de l'éditeur de niveau et également celle du jeu principal (similarité)
 *elle stockera une liste de case ainsi que la l'état lié aux héros, ennemies et leurs position
 *
 * */

public class MatrixLvlEditorModel extends AbstractModel {
    private  final Logger logger = Logger.getLogger(MatrixLvlEditorModel.class.getName());
    //Matrice de l'éditeur de niveau qui contiendra les items selectionnés dans le menu de choix des items
    private CaseMatrix[][] matrixEditorLvl;
    Hero hero;
    List<Ennemy> ennemies = new ArrayList<Ennemy>();
    Ennemy ActiveEnnemy ;
    Queue<MoveAction> moveQueue = new LinkedList<>();

    public Queue<MoveAction> getMoveQueue() {
        return moveQueue;
    }

    public void setMoveQueue(Queue<MoveAction> moveQueue) {
        this.moveQueue = moveQueue;
    }

    public Ennemy getActiveEnnemy() {
        return ActiveEnnemy;
    }

    public void setActiveEnnemy(Ennemy activeEnnemy) {
        ActiveEnnemy = activeEnnemy;
    }

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
        logger.info("Ajout item addItemMatrice");
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col]=items;
            matrixEditorLvl[row][col].setOccuped(true);
            fillHeroAndEnnemyList( row, col,items);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour ajouter l'item (" + row + ", " + col + ").");
            logger.info("nbofRows=" + nbOfRows + ", nbOfCols=" + nbOfCols);
            throw new IllegalArgumentException("Erreur: Coordonnées invalides pour ajouter l'item ");
        }
    }

    public boolean isOccupedCase(int row, int col) {
       return this.getCaseMatrix(row, col).isOccuped();
    }

    public boolean isOccupedCaseCoord(Coord coord) {
        return this.getCaseMatrix(coord.getRow(), coord.getCol()).isOccuped();
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
            logger.info("Déplacement du hero, nouvelle coord "+hero.getCoord().getCol()+" et "+hero.getCoord().getRow());

        return true;}
        else {
            logger.info("Le héros ne peut pas se déplacer");
            return false;
        }

    }


    public boolean moveItem(Movable item, int rowX, int colY) {
        int rowItem = item.getCoord().getRow();
        int colItem = item.getCoord().getCol();
        int row = rowItem + rowX;
        int col = colItem + colY;
        boolean isOccuped = this.getCaseMatrix(row, col).isOccuped();

        if(isValidCoordinate(row, col) && !isOccuped) {
            hero.setCoord(new Coord(rowItem,colItem));
            resetItemMatrice(rowItem,colItem);
            logger.info("Déplacement du hero, nouvelle coord "+hero.getCoord().getCol()+" et "+hero.getCoord().getRow());
            Coord coord = new Coord(
                    item.getCoord().getRow() + rowX,
                    item.getCoord().getCol() + colY
            );
            item.setCoord(coord);
            this.setOccuped(coord, true);
            freeLastCastOccupedNewCase(new Coord(rowItem,colItem), coord);
            this.getPropertyChangeSupport().firePropertyChange("move", new Coord(rowItem,colItem), item);

            return true;}
        else {
            logger.info("Le héros ne peut pas se déplacer");
            return false;
        }

    }


    public void addDynamicBow(){
        Coord heroPos = hero.getCoord();
        int row = heroPos.getRow();
        int col = heroPos.getCol();
        Coord direction = hero.directionToCoord(hero.getLastDirection());
        int rowBow = row + direction.getRow();
        int colBow = col + direction.getCol();
        CaseMatrix bow = new CaseMatrix("src/java/org/projet/assets/bule.png", rowBow, colBow);
        bow.setOccuped(true);
        this.addItemMatrice(row, col, bow);
        this.getPropertyChangeSupport().firePropertyChange("heroFire", null, bow);
        logger.info("Ajout d'un arc à la position du héros");

    }

    public void fillHeroAndEnnemyList(int row, int col,CaseMatrix items) {
        Object classOfItems = items.getClassOfItems();
        logger.info("fillHeroAndEnnemyList "+classOfItems);

        if (classOfItems instanceof Hero) {
            this.hero = new Hero("marcel",10);
            hero.setCoord(new Coord(row, col));
            logger.info("Ajout du héro  fillHeroAndEnnemyList");
        }
        else if (classOfItems instanceof Ennemy) {
            Ennemy ennemy = new Agressor("ee",10); // TODO
            ennemy.setCoord(new Coord(row, col));
            this.ennemies.add(ennemy);
            logger.info("Ajout d'un ennemie  fillHeroAndEnnemyList+ "+ennemies);
        }
        else {
            logger.info("L'item n'est ni un heros, ni un ennemi");
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


    public void freeLastCastOccupedNewCase(Coord oldPos, Coord newPos) {
        this.setOccuped(oldPos, false);
        logger.info("Etat ancienne case  (assert false)"+ isOccupedCaseCoord(oldPos));
        this.setOccuped(newPos, true);
        logger.info("Etat ancienne case  (assert true)"+ isOccupedCaseCoord(newPos));


    }
}