package src.java.org.projet.model.modelLevelEditor;


import com.fasterxml.jackson.annotation.JsonIgnore;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveAction;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.Boss;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelCharacter.MyCharacter;
import src.java.org.projet.model.modelItems.Bow;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.model.modelMap.Location;
import src.java.org.projet.model.modelMap.SimpleDoor;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
//@formatter:on

/**
 * Matrice de l'éditeur de niveau et également celle du jeu principal (similarité)
 * elle stockera une liste de case ainsi que la l'état lié aux héros, ennemies et leurs position
 */
public class MatrixLvlEditorModel extends AbstractModel {
    @JsonIgnore
    private final MyLogger logger = new MyLogger(MatrixLvlEditorModel.class);
    //Matrice de l'éditeur de niveau qui contiendra les items selectionnés dans le menu de choix des items
    private CaseMatrix[][] matrixEditorLvl;
    @JsonIgnore
    Hero hero;
    /**
     * Liste des entités  mobiles de la map
     */
    @JsonIgnore
    List<Movable> movable = new ArrayList<Movable>();

    /**
     * File des actions à traiter (mouvements, projectiles...)
     */
    @JsonIgnore
    Queue<MoveAction> moveQueue = new ConcurrentLinkedQueue<>();
    int nbOfRows;
    int nbOfCols;
    String urlBackground;
    @JsonIgnore
    Dataset dataset = Dataset.getInstance();


    /**
     * Constructeur
     * @param nbOfRows nombre de lignes de la map
     * @param nbOfCols nombre de colonnes de la map
     * @param urlBackground Image de fond de la map
     */
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
    public MatrixLvlEditorModel() {
        super("Matrix Lvl Editor");
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
            items.setCoordCol(col);
            items.setCoordRow(row);
            matrixEditorLvl[row][col] = items;
            matrixEditorLvl[row][col].setOccuped(true);

            fillHeroAndEnnemyList(row, col, items);
        } else {
            System.err.println("Erreur: Coordonnées invalides pour ajouter l'item (" + row + ", " + col + ").");
            logger.info("nbofRows=" + nbOfRows + ", nbOfCols=" + nbOfCols);
            throw new IllegalArgumentException("Erreur: Coordonnées invalides pour ajouter l'item ");
        }
    }

    /**
     * Remplir les les listes d'items en parcourant la matrice
     */
    public void initEntityWithMatrix(){
        CaseMatrix currentEntity;
        for (int i = 0; i < nbOfRows; i++) {
            for (int j = 0; j < nbOfCols; j++) {
                currentEntity = getCaseMatrix(i, j);
                fillHeroAndEnnemyList(i,j,currentEntity);
            }
        }

    }

    /**
     * Envoyer les cases de la matrice pour affichage
     */
    public void showItemModel() {
        MatrixLvlEditorModel mod = this;
        getPropertyChangeSupport().firePropertyChange("showModelCase", null, this);

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

    /**
     * Déplacer  un item dans la map
     * @param item
     * @param rowX
     * @param colY
     * @return succès du déplacement
     */
    public boolean moveItem(Movable item, int rowX, int colY) {
        logger.warning("moveItem " + item + " rowX=" + rowX + " colY=" + colY);
        Coord currentItemPos = item.getCoord();
        Coord newItemPos = Coord.addCoord(currentItemPos, new Coord(rowX, colY));

        if(item ==null)return false;
        boolean validCoord = isValidCoordinateCoord(newItemPos);
        if (!validCoord) {
            logger.info(item + " déplacement hors limites, suppression.");
            removeItem(item, currentItemPos);
            return false;
        }

        CaseMatrix nextCase = getCaseMatrixCoord(newItemPos);
        boolean isOccupied = nextCase.isOccuped();

        if (item instanceof Bow) {
            if (isOccupied) {
                handleArrowTouchSomeBody(item, nextCase, newItemPos, currentItemPos);
                return false;
            } else { // case libre, on déplace
                moveMovable(item, currentItemPos, newItemPos);
                return true;
            }
        }

        // Si case libre pour les autres objets
        if (!isOccupied) {
            moveMovable(item, currentItemPos, newItemPos);
            return true;
        }

        logger.info(item + " ne peut pas se déplacer, case occupée.");
        return false;
    }

    /**
     * Cas ou le projectile touche une entité
     * @param item item déplacé
     * @param nextCase
     * @param newItemPos
     * @param currentItemPos
     */
    private void handleArrowTouchSomeBody(Movable item, CaseMatrix nextCase, Coord newItemPos, Coord currentItemPos) {
        Object target = nextCase.getClassOfItems();
        if (target instanceof Ennemy enemy) {
            handleEnnemyAttacked(item, enemy, newItemPos, nextCase);
        } else if (target instanceof Hero) {
            handleHeroAttacked();
        }
        removeItem(item, currentItemPos);
    }

    /**
     * Cas ou le projectil atteint un ennemi
     * @param item
     * @param enemy
     * @param newItemPos
     * @param nextCase
     */
    private void handleEnnemyAttacked(Movable item, Ennemy enemy, Coord newItemPos, CaseMatrix nextCase) {
        logger.severe("Ennemi touché et supprimé !");
        resetItemMatriceCoord(newItemPos);
        movable.remove(enemy);
        getPropertyChangeSupport().firePropertyChange(dataset.getString("removeItem"), nextCase, item);
        if (item instanceof Boss){
            getPropertyChangeSupport().firePropertyChange("endOfTheGame", nextCase, item);
        }
    }

    /**
     * Cas ou le projectile atteint le héro
     */
    private void handleHeroAttacked() {
        hero.decreaseHP(dataset.getMesure("DEFAULT_ARROW_REMOVE_HP_HERO"));
        logger.info("Héros touché par une flèche");
        if (hero.getHP() <= 0) {
            logger.info("Héros mort");
            getPropertyChangeSupport().firePropertyChange("endOfTheGame", null, hero);
        } else {
            getPropertyChangeSupport().firePropertyChange("UpdateHeroState", null, hero);
        }
    }

    /**
     * Déplacer une entité mobile
     * @param item entité à déplacer
     * @param from coordonnée initiale
     * @param to  coordonnée cible
     */
    private void moveMovable(Movable item, Coord from, Coord to) {
        resetItemMatriceCoord(from);
        item.setCoord(to);
        freeLastCastOccupedNewCase(item, from, to);
        this.getPropertyChangeSupport().firePropertyChange(dataset.getString("move"), from, item);
        logger.info(item + " déplacé vers " + to);
    }

    /**
     * Supprimé un item mobile
     * @param item
     * @param from Coordonnées de l'item
     */
    private void removeItem(Movable item, Coord from) {
        getMoveQueue().removeIf(action -> action.getEntity().equals(item));
        movable.remove(item);
        resetItemMatriceCoord(from);
        getPropertyChangeSupport().firePropertyChange(dataset.getString("removeItem"), from, item);
    }

    /**
     * Remplir les paramètres internes à la classe, héro, Liste des entités mobiles...
     * vie une case(les cases) de la map.
     * @param row
     * @param col
     * @param items
     */
    public void fillHeroAndEnnemyList(int row, int col, CaseMatrix items) {

            Object classOfItems = items.getClassOfItems();
            String className = items.getClassNameOfItem();
        if(classOfItems != null) {
            if (classOfItems instanceof Hero || className.equals("Hero")) {
                hero = new Hero("Marcel", 10);
                hero.setCoord(new Coord(row, col));
                getCaseMatrix(row, col).setClassOfItems(hero);
                getPropertyChangeSupport().firePropertyChange("UpdateHeroState", null, hero);
                logger.info("Ajout du héro  fillHeroAndEnnemyList");
            } else if (classOfItems instanceof Ennemy || className.equals("Agressor")||className.equals("Boss")) {
                if(className.equals("Agressor")) {
                    Agressor ennemy = new Agressor("Agressor", 10); // TODO
                    ennemy.setCoord(new Coord(row, col));
                    movable.add(ennemy);
                    getCaseMatrix(row, col).setClassOfItems(ennemy);
                    logger.info("Ajout d'un ennemie  fillHeroAndEnnemyList+ " + movable);
                }
                else if( className.equals("Boss")){
                    Boss ennemy = new Boss("Boss", 10); // TODO
                    ennemy.setCoord(new Coord(row, col));
                    movable.add(ennemy);
                    getCaseMatrix(row, col).setClassOfItems(ennemy);
                    logger.info("Ajout d'un ennemie  fillHeroAndEnnemyList+ " + movable);
                }
            }

            else {
                logger.info("L'item n'est ni un heros, ni un ennemi");
            }
        }
    }

    /**
     * Retourne les cases adjacentes à une case donnée
     *
     * @param row
     * @param col
     * @return
     */
    public List<CaseMatrix> getNeighbors(int row, int col) {
        List<CaseMatrix> neighbors = new ArrayList<>();
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] d : directions) {
            int newRow = row + d[0];
            int newCol = col + d[1];
            if (newRow >= 0 && newRow < nbOfRows && newCol >= 0 && newCol < nbOfCols) {
                neighbors.add(this.getCaseMatrix(newRow, newCol));
            }
        }
        return neighbors;
    }

    /**
     * Libérer une case et occuper la nouvelle lors du déplacement
     * @param item item à déplacer
     * @param oldPos anciennes coordonnées
     * @param newPos nouvelles coordonnées
     */
    public void freeLastCastOccupedNewCase(Movable item, Coord oldPos, Coord newPos) {
        addCaseMatrix(oldPos.getRow(), oldPos.getCol(), new CaseMatrix("", null, oldPos.getRow(), oldPos.getCol(), 1, 1));
        logger.info("maj de l'ancienne case " + getCaseMatrix(oldPos.getRow(), oldPos.getCol()));
        setOccuped(oldPos, false);
        logger.info("Etat ancienne case  (assert false) " + isOccupedCaseCoord(oldPos));

        addCaseMatrix(newPos.getRow(), newPos.getCol(), new CaseMatrix("", item, newPos.getRow(), newPos.getCol(), 1, 1));
        setOccuped(newPos, true);
        logger.info("maj de la nouvelle case " + getCaseMatrix(newPos.getRow(), newPos.getCol()));
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

    /**
     * Initialiser les cases de la matrice
     */
    public void initCaseMatrixEditorLvl() {
        for (int i = 0; i < nbOfRows; i++) {
            for (int j = 0; j < nbOfCols; j++) {
                matrixEditorLvl[i][j] = new CaseMatrix("", i, j);
            }
        }
    }


    /**
     * Ajouter un projectile sur la map
     * @param character  personnage ayant tiré
     */
    public void addDynamicBow(MyCharacter character) {
        logger.info("Tire de la flèche model");
        Coord heroPos = character.getCoord();
        Coord direction = character.directionToCoord(character.getLastDirection());
        Coord BowPos = Coord.addCoord(heroPos, direction);
        CaseMatrix bow;
        Bow bowObj = new Bow(BowPos);

        if (character instanceof Hero) {

            bowObj.setBowUrlImage(dataset.getString("DEFAULT_HERO_BULLET_PATH"));
            bow = new CaseMatrix(bowObj.getBowUrlImage(), BowPos);
        } else {
            bowObj.setBowUrlImage(dataset.getString("DEFAULT_ENNEMY_BULLET_PATH"));
            bow = new CaseMatrix(bowObj.getBowUrlImage(), BowPos);
        }
        bowObj.setMoveDirection(direction.getRow(), direction.getCol());
        movable.add(bowObj);
        getMoveQueue().add(new MoveAction(bowObj, direction.getRow(), direction.getCol()));
        addItemMatrice(BowPos.getRow(), BowPos.getCol(), bow);
        getPropertyChangeSupport().firePropertyChange(dataset.getString("fireHero"), null, bow);
        getPropertyChangeSupport().firePropertyChange("UpdateHeroState", null, hero);

        logger.info("Ajout d'un arc à la position du héros");

    }


    public Queue<MoveAction> getMoveQueue() {
        return moveQueue;
    }

    public void setMoveQueue(Queue<MoveAction> moveQueue) {
        this.moveQueue = moveQueue;
    }

    /**
     * @return Héro
     */
    public Hero getHero() {
        return hero;
    }


    public void setHero(Hero hero) {
        logger.info("Changement de l'instance du héros");
        Coord heroPos = hero.getCoord();
        setCaseMatrix(heroPos.getRow(), heroPos.getCol(), new CaseMatrix(hero.getDefaultImgPath(), hero, heroPos.getRow(), heroPos.getCol(), 1, 1));
        this.hero = hero;
    }

    /**
     * @return Liste des entités movable de la map courante
     */
    public List<Movable> getMovable() {
        return movable;
    }

    public void setMovable(List<Movable> movable) {
        this.movable = movable;
    }

    /**
     * @return Matrice de la map
     */
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

    /**
     * Changer l'image de fond de la map
     * @param urlBackground
     */

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

    /**
     * Détecte si une case est occupée
     * @param row
     * @param col
     * @return
     */
    public boolean isOccupedCase(int row, int col) {
        return getCaseMatrix(row, col).isOccuped();
    }

    public boolean isOccupedCaseCoord(Coord coord) {
        return this.getCaseMatrix(coord.getRow(), coord.getCol()).isOccuped();
    }

    /**
     * Fixer l'occupation d'une case
     * @param newPos
     * @param occuped
     */
    public void setOccuped(Coord newPos, boolean occuped) {
        this.getCaseMatrix(newPos.getRow(), newPos.getCol()).setOccuped(occuped);
    }

    /**
     * Obtenir une case de la map
     * @param row
     * @param col
     * @return
     */
    public CaseMatrix getCaseMatrix(int row, int col) {
        if (row < nbOfRows && col < nbOfCols) {
            return matrixEditorLvl[row][col];
        } else {
            throw new RuntimeException("Case incorrect");
        }
    }

    public void setCaseMatrix(int row, int col, CaseMatrix caseM) {
        if (row < nbOfRows && col < nbOfCols) {
            matrixEditorLvl[row][col] = caseM;
        }

    }

    public CaseMatrix getCaseMatrixCoord(Coord coord) {
        int row = coord.getRow();
        int col = coord.getCol();
        if (isValidCoordinate(row, col)) {
            return matrixEditorLvl[row][col];
        } else {
            throw new RuntimeException("Case incorrect");
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

    /**
     * @return nombres de lignes de la map
     */
    public int getNbOfRows() {
        return nbOfRows;
    }

    /**
     * nombre de colonnes de la map
     * @return
     */
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



    /**
     * Changer de  map
     * @param cross nouvelle location
     */
    public void changeLocation(Location cross) {
        logger.info("Changement de la location");
        if (cross != null) {
            getPropertyChangeSupport().firePropertyChange("location", null, cross);
            logger.info("Changement de la location " + cross);
        } else {
            logger.info("Aucune location à changer");
        }
    }
}