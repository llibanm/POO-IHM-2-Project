package src.java.org.projet.view.levelEditorView;

import javafx.scene.layout.BorderPane;
import src.java.org.projet.controler.LevelEditorController;
import src.java.org.projet.model.modelLevelEditor.LevelEditorModel;

/**Vue contenant à la fois la map contenant les objets selectionnés(MatrixLvlEditorView)
 * et le menu de selection  */
public class LvlEditorView extends BorderPane {
    MatrixLvLEditorView matrixLvLEditorView;
    LevelEditorModel model;
    LevelEditorController controller;

    public LvlEditorView(int width, int height, int nbOfRows,
                         int nbOfColumns, LevelEditorModel model,
                         LevelEditorController controller) {
        super();

        this.setWidth(width);
        this.setHeight(height);
        this.model = model;
        this.controller = controller;
        matrixLvLEditorView = new MatrixLvLEditorView(nbOfRows,nbOfColumns,model,controller);



    }



}
