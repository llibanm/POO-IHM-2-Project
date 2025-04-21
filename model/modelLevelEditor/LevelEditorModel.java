package model.modelLevelEditor;


import java.util.Arrays;
import java.util.List;

/**Cette classe contient à la fois la map de l'éditeur de niveau pour placer les items
 * et le menu de sélection ou on choisit ces derniers */
public class LevelEditorModel extends  SelectItemSectionModel{

    //Matrice de l'éditeur de niveau qui contiendra les items selectionnés dans le menu de choix des items
    private CaseMatrix[][] matrixEditorLvl;
    int nbOfRows;
    int nbOfCols;


    public LevelEditorModel( int nbOfRows, int nbOfCols, List<ItemToPlaceOnMap> itemsOfSelectionMenu) {
        //Ajout de la liste d'items du menu de choix des items dans la casse parent
        super(itemsOfSelectionMenu);
        if (nbOfRows <= 0 || nbOfCols <= 0) {
            throw new IllegalArgumentException("Le nombre de lignes et de colonnes doit être supérieur à zéro.");
        }
        this.nbOfRows = nbOfRows;
        this.nbOfCols = nbOfCols;
        this.matrixEditorLvl = new CaseMatrix[nbOfRows][nbOfCols];
        initCaseMatrixEditorLvl();
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
                matrixEditorLvl[nbOfRows][nbOfCols] = new CaseMatrix("",0,0);
            }
        }
    }

    /**
     *
     * @param row
     * @param col
     * @param items La représentation de l'items(url image, coordonnées, Classe liée)
     */
    public void addItemMatrice(int row, int col, CaseMatrix items) {
        if (isValidCoordinate(row, col)) {
            matrixEditorLvl[row][col]=items;
        } else {
            System.err.println("Erreur: Coordonnées invalides pour ajouter l'item (" + row + ", " + col + ").");
            throw new IllegalArgumentException(" ");
        }
    }
    public void removeItemMatrice(int row, int col) {
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
        return "LevelEditorModel{" +
                "itemsOfSelectionMenu=" + itemsOfSelectionMenu +
                ", nbOfColsMatrix=" + nbOfCols +
                ", nbOfRowsMatrix=" + nbOfRows +
                ", matrixEditorLvl=" + Arrays.toString(matrixEditorLvl) +
                '}';
    }
}
