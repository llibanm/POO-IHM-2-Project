package src.java.org.projet.view.levelEditorView;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.java.org.projet.controler.levelEditorController.MatrixLvlEditorController;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Vue qui affichera sur une map les items(perso, objets)  selectionnés depuis le menu de sélection
 * dans l'éditeur de  niveaux
 */
public class MatrixLvLEditorView extends GridPane {
    private final MyLogger logger = new MyLogger(MatrixLvLEditorView.class);
    Dataset dataset = Dataset.getInstance();
    int nbOfRows;
    int nbOfCols;
    int default_case_size = dataset.getMesure("DEFAULT_CASE_SIZE_VIEW");




    /**
     * Constructeur de la vue
     * @param nbOfRows
     * @param nbOfCols
     */
    public MatrixLvLEditorView(int nbOfRows, int nbOfCols) {
        this.nbOfRows = nbOfRows;
        this.nbOfCols = nbOfCols;
        //this.setWidth(500);
        //this.setWidth(500);

        initialize();
    }

    public void initialize() {
        //setBackground();
        fillMap();
    }

    /**
     * Efface les cases de la vue de la grille puis rempli
     */
    public void initializeReset() {
        //setBackground();
        for (int row = 0; row < nbOfRows; row++) {
            for (int col = 0; col < nbOfCols; col++) {
                //removeImgFromGridPane(row, col);
                removeItemFromGridPane(row, col);
            }
        }
        fillMap();
    }

    public void fillMap() {
        String imgUrl;
        CaseMatrix caseMatrix;
        for (int row = 0; row < nbOfRows; row++) {
            for (int col = 0; col < nbOfCols; col++) {
                addRectOnGrid(row, col);
            }
        }
        /*
        imgUrl="src/java/org/projet/assets/character/ennemy/img.png";
        placeItemImgSpan(imgUrl, 1,2,1,10 );
        *
         */
    }



    /**
     * Ajoute un rectangle autour d'une case
     * @param row
     * @param col
     */
    public void addRectOnGrid(int row, int col) {
        this.add(getRect(), row, col);
    }

    /**
     * Teste la validité des coordonnées dans la vue
     * @param row
     * @param col
     * @return
     */
    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < nbOfRows && col >= 0 && col < nbOfCols;
    }

    /**
     * Obtenir un Rectangle pour remplir une case de la grille
     * @return
     */
    public Rectangle getRect() {
        Rectangle cell = new Rectangle(default_case_size, default_case_size, Color.TRANSPARENT);
        cell.setStroke(getColorFromString(dataset.getString("DEFAULT_RECT_COLOR")));  //TODO
        return cell;
    }

    /**
     * Convertir un texte en couleur
     * @param color
     * @return
     */
    public  Color getColorFromString(String color) {
        switch (color) {
            case "TRANSPARENT":
                return Color.TRANSPARENT;
                case "GREEN":
                    return Color.GREEN;
                    case "BLUE":
                        return Color.BLUE;
                        default:
                            return Color.BLACK;

        }
    }

    public void resetCell(int row, int col) {

        removeItemFromGridPane(row, col);
        addRectOnGrid(row, col);
    }


    /**
     * Efface la case dans la map de la vue
     * @param row
     * @param column
     * @return
     */
    public boolean removeItemFromGridPane(int row, int column) {
        List<Node> nodesToRemove = new ArrayList<>();

        for (Node node : this.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);
            if (rowIndex != null && rowIndex == row && columnIndex != null && columnIndex == column) {
                nodesToRemove.add(node);
            }
        }

        if (!nodesToRemove.isEmpty()) {
            logger.info("Noeud à supprimer de la grille : " + nodesToRemove);
            this.getChildren().removeAll(nodesToRemove);
            logger.info("Éléments supprimés de la grille removeAllItemsFromGridPane");
            return true;
        }

        logger.info("Aucun élément supprimé de la grille removeAllItemsFromGridPane");
        return false;
    }


    /**
     *
     * @param row
     * @param column
     * @return
     */
    public Node getNodeAt(int row, int column) {
        for (Node node : this.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            // Gère les valeurs null (par défaut 0)
            int actualRow = rowIndex == null ? 0 : rowIndex;
            int actualCol = columnIndex == null ? 0 : columnIndex;

            if (actualRow == row && actualCol == column) {
                return node;
            }
        }
        return null;
    }

    /**
     * Placer une image dans une case de la map
     * @param url
     * @param row
     * @param col
     */

    public void placeItemImgBis(String url, int row, int col) {
        if (url != null && !url.isEmpty() && isValidCoordinate(row, col)) {
            StackPane pane = new StackPane();
            ImageView img = createImg(url);
            pane.getChildren().addAll(getRect(), img);
            GridPane.setColumnIndex(pane, col);
            GridPane.setRowIndex(pane, row);
            this.getChildren().add(pane);
        }
    }

    public Rectangle updateHeroPositionView(int oldRowHero, int oldColHero, String url, int newRow, int newCol) {
        this.removeItemFromGridPane(oldRowHero, oldColHero);
        Rectangle rectangle = this.setRecBis(oldRowHero, oldColHero);
        this.placeItemImgBis(url, newRow, newCol);
        return rectangle;
    }

    public Rectangle updateHeroPositionViewBis(int oldRowHero, int oldColHero, ImageView url, int newRow, int newCol) {
        this.removeItemFromGridPane(oldRowHero, oldColHero);
        Rectangle rectangle = this.setRecBis(oldRowHero, oldColHero);
        url.setFitHeight(rectangle.getHeight());
        url.setFitWidth(rectangle.getWidth());
        this.placeItemImgBis(url, newRow, newCol);
        return rectangle;
    }

    public Rectangle setRecBis(int oldRow, int oldCol) {
        Rectangle rec = this.getRect();
        GridPane.setRowIndex(rec, oldRow);
        GridPane.setColumnIndex(rec, oldCol);

        this.getChildren().add(rec);
        return rec;
    }

    /**
     * Afficher chaque case issue du modèle
     * @param model
     */
    public  void showModelView(MatrixLvlEditorModel model) {
        for (int i = 0; i < model.getNbOfRows(); i++) {
            for (int j = 0; j < model.getNbOfCols(); j++) {
                CaseMatrix case_ = model.getCaseMatrix(i,j);
                placeItemImgBis(case_.getUrlImgToShow(), i,j);
            }
        }
    }


    public void placeItemImgBis(ImageView img, int row, int col) {
        if (img != null && isValidCoordinate(row, col)) {
            StackPane pane = new StackPane();
            pane.getChildren().addAll(getRect(), img);
            this.add(pane, col, row);
        }
    }

    public void placeItemImg(String url, int row, int col) {
        if (url != null && !url.isEmpty() && isValidCoordinate(row, col)) {
            StackPane pane = new StackPane();
            ImageView img = createImg(url);
            pane.getChildren().addAll(getRect(),img);
            this.add(pane, col, row);
        }
    }

    public ImageView createImg(String imgUrl) {
        Image image = new Image(imgUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(this.default_case_size);
        imageView.setFitHeight(this.default_case_size);
        return imageView;
    }

    /**
     * Juste pour étendre une image sur plusieurs cases de la grille
     * certains objets pourrait occuper plus d'une case
     */
    public void placeItemImgSpan(String url, int row, int col, int colspan, int rowspan) {
        if (url != null && !url.isEmpty() && isValidCoordinate(row, col) && colspan >= 1
                && rowspan >= 1 && colspan + col < nbOfCols && rowspan + row < nbOfRows) {
            Image image = new Image(url);

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(this.default_case_size * colspan);
            imageView.setFitHeight(this.default_case_size * rowspan);
            GridPane.setColumnSpan(imageView, colspan);
            GridPane.setRowSpan(imageView, rowspan);

            this.add(imageView, col, row);
        } else {
            throw new IndexOutOfBoundsException("Vous essayez de placer une image Hors cadre!");
        }
    }

    /**
     * Placer le fond d'écran de la grille de l'éditeur de niveau
     */
    public void setBackground(String url) {
        var backgroundImage = new Image(url);

        int width = nbOfCols * dataset.getMesure("DEFAULT_CASE_SIZE_VIEW");
        int height = nbOfRows * dataset.getMesure("DEFAULT_CASE_SIZE_VIEW");

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(
                        width, height,
                        false, false,
                        false, false
                )
        );
        this.setBackground(new Background(background));
    }



}