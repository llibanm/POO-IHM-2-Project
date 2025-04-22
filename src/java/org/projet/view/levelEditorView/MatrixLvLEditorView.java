package src.java.org.projet.view.levelEditorView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import src.java.org.projet.controler.LevelEditorController;
import src.java.org.projet.model.modelLevelEditor.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.LevelEditorModel;

/**Vue qui affichera sur une map les items(perso, objets)  selectionnés depuis le menu de sélection
 * dans l'éditeur de  niveaux */
public class MatrixLvLEditorView extends GridPane {
    int nbOfRows;
    int nbOfCols;


    public MatrixLvLEditorView(int nbOfRows, int nbOfCols) {
        this.nbOfRows = nbOfRows;
        this.nbOfCols = nbOfCols;

        initialize();
    }

    public void initialize() {
        //setBackground();
        fillMap();
    }
    public  void fillMap(){
        String imgUrl;
        CaseMatrix caseMatrix;
        for (int row = 0; row < nbOfRows; row++){
            for (int col = 0; col < nbOfCols; col++){
                /*
                caseMatrix = model.getCaseMatrix(nbOfRows,nbOfCols);
                imgUrl = caseMatrix.getUrlImgToShow();

                 */
                imgUrl="src/java/org/projet/assets/character/hero/run1.png";
                placeItemImg(imgUrl, col, row);



            }
        }
        imgUrl="src/java/org/projet/assets/character/ennemy/img.png";
        placeItemImgSpan(imgUrl, 1,2,1,10 );
    }
    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < nbOfRows && col >= 0 && col < nbOfCols;
    }

    public void placeItemImg(String url, int row, int col){
        if(url != null && !url.isEmpty() && isValidCoordinate(row, col)){
            Image image = new Image(url);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            this.add(imageView, col, row);
        }
    }

    /**Juste pour étendre une image sur plusieurs cases de la grille */
    public void placeItemImgSpan(String url, int row, int col, int colspan, int rowspan) {
        if (url != null && !url.isEmpty() && isValidCoordinate(row, col) && colspan >= 1
                && rowspan >= 1 && colspan+col < nbOfCols && rowspan+row < nbOfRows) {
            Image image = new Image(url);

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40*colspan);
            imageView.setFitHeight(40*rowspan);
            GridPane.setColumnSpan(imageView, colspan);
            GridPane.setRowSpan(imageView, rowspan);

            this.add(imageView, col, row);
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }
    /**Placer le fond d'écran de la grille de l'éditeur de niveau
     * */
    public void setBackground(){
        //model.getUrlBackground()
       var  backgroundImage = new Image("src/java/org/projet/spaceImagesProject/Space_Background.png");

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1200,1000,true,true,true,true)
        );
        this.setBackground(new Background(background));
    }
}
