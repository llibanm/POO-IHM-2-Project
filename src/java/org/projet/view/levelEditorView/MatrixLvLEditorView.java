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
    LevelEditorModel model;
    LevelEditorController controller;

    public MatrixLvLEditorView(int nbOfRows, int nbOfCols, LevelEditorModel model, LevelEditorController controller) {
        this.nbOfRows = nbOfRows;
        this.nbOfCols = nbOfCols;
        this.model = model;
        this.controller = controller;
        initialize();
    }

    public void initialize() {
        setBackground();
        fillMap();
    }
    public  void fillMap(){
        String imgUrl;
        CaseMatrix caseMatrix;
        for (int row = 0; row < nbOfRows; row++){
            for (int col = 0; col < nbOfCols; col++){
                caseMatrix = model.getCaseMatrix(nbOfRows,nbOfCols);
                imgUrl = caseMatrix.getUrlImgToShow();
                placeItemImg(imgUrl, col, row);

            }
        }
    }

    public void placeItemImg(String url, int row, int col){
        if(url != null && !url.isEmpty()){
            Image image = new Image(url);
            ImageView imageView = new ImageView(image);
            this.add(imageView, col, row);
        }
    }
    /**Placer le fond d'écran de la grille de l'éditeur de niveau
     * */
    public void setBackground(){
       var  backgroundImage = new Image(model.getUrlBackground());

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
