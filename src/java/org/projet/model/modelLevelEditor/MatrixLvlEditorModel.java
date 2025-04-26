package src.java.org.projet.model.modelLevelEditor;


import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveAction;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelCharacter.MyCharacter;
import src.java.org.projet.model.modelItems.Bow;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
//@formatter:on

/**
 * Matrice de l'éditeur de niveau et également celle du jeu principal (similarité)
 * elle stockera une liste de case ainsi que la l'état lié aux héros, ennemies et leurs position
 */
public class MatrixLvlEditorModel extends AbstractModel {
    private final MyLogger logger = new MyLogger(MatrixLvlEditorModel.class);
    //Matrice de l'éditeur de niveau qui contiendra les items selectionnés dans le menu de choix des items
    private CaseMatrix[][] matrixEditorLvl;
    Hero hero;
    List<Movable> ennemies = new ArrayList<Movable>();
    Ennemy ActiveEnnemy;
    Queue<MoveAction> moveQueue = new ConcurrentLinkedQueue<>();
    int nbOfRows;
    int nbOfCols;
    String urlBackground;

    public MatrixLvlEditorModel() {
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

    /**
     * Ajouter un item sur la Matrice
     *
     * @param row
     * @param col
     * @param items La représentation de l'items(url image, coordonnées, Classe liée)
     */
    public void addItemMatrice(int row, int col, CaseMatrix items) {
        logger.info("Ajout item addItemMatrice");
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col] = items;
            matrixEditorLvl[row][col].setOccuped(true);
            fillHeroAndEnnemyList(row, col, items);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour ajouter l'item (" + row + ", " + col + ").");
            logger.info("nbofRows=" + nbOfRows + ", nbOfCols=" + nbOfCols);
            throw new IllegalArgumentException("Erreur: Coordonnées invalides pour ajouter l'item ");
        }
    }



    public void addItemMatriceCoord(Coord c, CaseMatrix items) {
        int row = c.getRow();
        int col = c.getCol();
        logger.info("Ajout item addItemMatrice");
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col] = items;
            matrixEditorLvl[row][col].setOccuped(true);
            fillHeroAndEnnemyList(row, col, items);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour ajouter l'item (" + row + ", " + col + ").");
            logger.info("nbofRows=" + nbOfRows + ", nbOfCols=" + nbOfCols);
            throw new IllegalArgumentException("Erreur: Coordonnées invalides pour ajouter l'item ");
        }
    }

    public boolean moveItem(Movable item, int rowX, int colY) {
        logger.warning("moveItem "+ item+ " rowX=" + rowX + " colY=" + colY);
        Coord currentItemPos = item.getCoord();
        Coord newItemPos = Coord.addCoord(currentItemPos, new Coord(rowX,colY));
        boolean isNextCaseOccuped = true;
        if (isValidCoordinateCoord(newItemPos)) {
             isNextCaseOccuped = this.getCaseMatrixCoord(newItemPos).isOccuped();
            // Nouveau: Vérifie si la case contient un ennemi
            if (isNextCaseOccuped && item instanceof Bow) {
                CaseMatrix nextCase = getCaseMatrixCoord(newItemPos);
                if (nextCase.getClassOfItems() instanceof Ennemy) {
                    // Supprime l'ennemi
                    Ennemy enemy = (Ennemy) nextCase.getClassOfItems();
                    resetItemMatriceCoord(newItemPos); // Efface l'ennemi de la grille
                    ennemies.remove(enemy); // Retire de la liste
                    this.getPropertyChangeSupport().firePropertyChange("move", currentItemPos, item);
                    this.getPropertyChangeSupport().firePropertyChange("removeItem", currentItemPos, item);
                    this.getPropertyChangeSupport().firePropertyChange("removeItem", nextCase, item);
                    logger.severe("Ennemi touché et supprimé !");
                }
            }
        }

        logger.info("Coordonnées valides? pour le déplacement de l'item " + item+"Coord :" + newItemPos+"etat case suivante:" + isNextCaseOccuped);
        if (isValidCoordinateCoord(newItemPos) && !isNextCaseOccuped) {
            logger.info("Coordonnées valides pour le déplacement de l'item " + item+"Coord :" + newItemPos+"etat case suivante:" + isNextCaseOccuped);
            resetItemMatriceCoord(currentItemPos);
            item.setCoord(newItemPos);
            freeLastCastOccupedNewCase(item,currentItemPos, newItemPos);
            this.getPropertyChangeSupport().firePropertyChange("move", currentItemPos, item);

            logger.info(item+ " Déplacement d'un item, nouvelle coord " + newItemPos+" ancienne coord " + currentItemPos);
            return true;
        } else {
            if (item instanceof Bow) {
                logger.severe("Suppression de la flèche...");
                this.getMoveQueue().removeIf(action -> action.getEntity().equals(item)); // Supprime toutes ses actions
                this.ennemies.remove(item); // <-- Ajoutez cette ligne
                this.resetItemMatriceCoord(currentItemPos);
                this.getPropertyChangeSupport().firePropertyChange("removeItem", currentItemPos, item);
                return false;
            }
            logger.info(item+" L'item ne peut pas se déplacer Coordonnées invalident "+item);
            return false;
        }
    }


    public void fillHeroAndEnnemyList(int row, int col, CaseMatrix items) {
        Object classOfItems = items.getClassOfItems();
        logger.info("fillHeroAndEnnemyList " + classOfItems);

        if (classOfItems instanceof Hero) {
            this.hero = new Hero("mMrcel", 10);
            hero.setCoord(new Coord(row, col));
            this.getCaseMatrix(row, col).setClassOfItems(hero);
            logger.info("Ajout du héro  fillHeroAndEnnemyList");
        } else if (classOfItems instanceof Ennemy) {
            Agressor ennemy = new Agressor("Agressor", 10); // TODO
            ennemy.setCoord(new Coord(row, col));
            this.ennemies.add(ennemy);
            this.getCaseMatrix(row, col).setClassOfItems(ennemy);
            logger.info("Ajout d'un ennemie  fillHeroAndEnnemyList+ " + ennemies);
        } else {
            logger.info("L'item n'est ni un heros, ni un ennemi");
        }

    }

    public void freeLastCastOccupedNewCase(Movable item, Coord oldPos, Coord newPos) {
        this.addCaseMatrix(oldPos.getRow(),oldPos.getCol(),new CaseMatrix("", null, oldPos.getRow(), oldPos.getCol(),1,1));
        logger.info("maj de l'ancienne case " + getCaseMatrix(oldPos.getRow(),oldPos.getCol()));
        this.setOccuped(oldPos, false);
        logger.info("Etat ancienne case  (assert false) " + isOccupedCaseCoord(oldPos));

        this.addCaseMatrix(newPos.getRow(),newPos.getCol(),new CaseMatrix("", item, newPos.getRow(), newPos.getCol(),1,1));
        this.setOccuped(newPos, true);
        logger.info("maj de la nouvelle case " + getCaseMatrix(newPos.getRow(),newPos.getCol()));
        logger.info("Etat ancienne case  (assert true) " + isOccupedCaseCoord(newPos));


    }

    /**
     * Remettre à zero une case de la matrice
     *
     * @param row
     * @param col
     */
    public void resetItemMatrice(int row, int col) {
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col] = new CaseMatrix("", 0, 0);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour supprimer l'item (" + row + ", " + col + ").");
            throw new IllegalArgumentException(" ");
        }
    }

    public void resetItemMatriceCoord(Coord itemc) {
        int row = itemc.getRow();
        int col = itemc.getCol();
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col] = new CaseMatrix("", row, col);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour supprimer l'item (" + row + ", " + col + ").");
            throw new IllegalArgumentException(" ");
        }
    }

    public void initCaseMatrixEditorLvl() {
        for (int i = 0; i < nbOfRows; i++) {
            for (int j = 0; j < nbOfCols; j++) {
                matrixEditorLvl[i][j] = new CaseMatrix("", i, j);
            }
        }
    }


    public void addDynamicBow(MyCharacter character) {
        logger.info("Tire de la flèche model");
        Coord heroPos = character.getCoord();
        Coord direction = character.directionToCoord(character.getLastDirection());
        Coord BowPos = Coord.addCoord(heroPos, direction);
        CaseMatrix bow = new CaseMatrix("src/java/org/projet/assets/bule.png", BowPos);
        Bow bowObj = new Bow(BowPos);
        bowObj.setMoveDirection(direction.getRow(), direction.getCol());
        ennemies.add(bowObj);
        getMoveQueue().add(new MoveAction(bowObj, direction.getRow(), direction.getCol()));
        this.addItemMatrice(BowPos.getRow(), BowPos.getCol(), bow);
        this.getPropertyChangeSupport().firePropertyChange("fireHero", null, bow);
        logger.info("Ajout d'un arc à la position du héros");

    }


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

    public List<Movable> getEnnemies() {
        return ennemies;
    }

    public void setEnnemies(List<Movable> ennemies) {
        this.ennemies = ennemies;
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
     *
     * @param row ligne
     * @param col colonne
     * @return si la la coordonnée est bien délimité dans la grille
     */
    public boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < nbOfRows && col >= 0 && col < nbOfCols;
    }
    public boolean isValidCoordinateCoord(Coord c) {
        int row = c.getRow();
        int col = c.getCol();
        return row >= 0 && row < nbOfRows && col >= 0 && col < nbOfCols;
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

    public CaseMatrix getCaseMatrix(int row, int col) {
        if (row < nbOfRows && col < nbOfCols) {
            return matrixEditorLvl[row][col];
        }
        else {
            throw new RuntimeException("Case incorrect") ;
        }
    }

    public CaseMatrix getCaseMatrixCoord(Coord coord) {
        int row = coord.getRow();
        int col = coord.getCol();
        if (isValidCoordinate(row, col)) {
            return matrixEditorLvl[row][col];
        }
        else {
            throw new RuntimeException("Case incorrect") ;
        }
    }

    public void addCaseMatrix(int row, int col, CaseMatrix caseMatrix) {
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col] = caseMatrix;
        } else {
            System.err.println("Erreur: Coordonnées invalides pour supprimer l'item (" + row + ", " + col + ").");
            throw new IllegalArgumentException(" ");
        }
    }

    public int getNbOfRows() {
        return nbOfRows;
    }

    public int getNbOfCols() {
        return nbOfCols;
    }

    @Override
    public String toString() {
        return "LevelEditorModel{\n" +
                "\n, nbOfColsMatrix=" + nbOfCols +
                "\n, nbOfRowsMatrix=" + nbOfRows +
                "\n, matrixEditorLvl=" + Arrays.deepToString(matrixEditorLvl) +
                '}';
    }


}