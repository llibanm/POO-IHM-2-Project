package src.java.org.projet.view.levelEditorView;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.java.org.projet.controler.levelEditorController.MatrixLvlEditorController;
import src.java.org.projet.interfaces.MyLogger;
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
    int nbOfRows;
    int nbOfCols;
    int default_case_size = 40;


    public MatrixLvLEditorView(int nbOfRows, int nbOfCols) {
        this.nbOfRows = nbOfRows;
        this.nbOfCols = nbOfCols;

        initialize();
    }

    public void initialize() {
        //setBackground();
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

    public void addRectOnGrid(int row, int col) {
        this.add(getRect(), row, col);
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < nbOfRows && col >= 0 && col < nbOfCols;
    }

    public Rectangle getRect() {
        Rectangle cell = new Rectangle(default_case_size, default_case_size, Color.TRANSPARENT);
        cell.setStroke(Color.TRANSPARENT);
        return cell;
    }

    public void resetCell(int row, int col) {

        removeItemFromGridPane(row, col);
        addRectOnGrid(row, col);
    }

    public void replaceNodeAt(int row, int col, Node newNode) {
        List<Node> nodes = new ArrayList<>(this.getChildren());

        for (Node node : nodes) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && rowIndex == row && colIndex != null
                    && colIndex == col) {
                this.getChildren().remove(node);
            }
        }
        // S'il est déjà parent de ce GridPane, le retirer
        if (newNode.getParent() != null) {
            ((Pane) newNode.getParent()).getChildren().remove(newNode);
        }
        this.add(newNode, col, row);
    }

    public void moveItemFromGridPane(int oldRow, int oldCol, int newRow, int newCol) {
        boolean itemMoved = false;

        // Copie temporaire pour éviter ConcurrentModificationException
        List<Node> nodes = new ArrayList<>(this.getChildren());

        for (Node node : nodes) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && rowIndex == oldRow && columnIndex != null
                    && columnIndex == oldCol && node instanceof ImageView) {
                logger.info("Element trouvé à déplacer : " + node);
                this.replaceNodeAt(newRow, newCol, node);
                logger.info("Element déplacé de la grille moveItemFromGridPane");
                itemMoved = true;
            }
        }

        if (!itemMoved) {
            logger.info("Element non déplacé de la grille moveItemFromGridPane");
        }
    }

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

    public boolean removeImgFromGridPane(int row, int column) {
        boolean removed = false;

        // Utilisation d'un iterator pour éviter ConcurrentModificationException
        Iterator<Node> iterator = this.getChildren().iterator();

        while (iterator.hasNext()) {
            Node node = iterator.next();
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && rowIndex == row &&
                    columnIndex != null && columnIndex == column &&
                    node instanceof ImageView) {

                iterator.remove();  // Suppression sûre pendant l'itération
                removed = true;
                // Ne pas retourner tout de suite pour supprimer TOUTES les images
            }
        }

        if (removed) {
            logger.info("Image(s) supprimée(s) de la grille");
        } else {
            logger.info("Aucune image trouvée à cette position");
        }

        return removed;
    }

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
            pane.getChildren().addAll(/*getRect(),*/img);
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
        //model.getUrlBackground()
        var backgroundImage = new Image(url);

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1200, 1000, true, true, true, true)
        );
        this.setBackground(new Background(background));
    }
}