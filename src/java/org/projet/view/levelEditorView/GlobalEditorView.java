package src.java.org.projet.view.levelEditorView;

import javafx.scene.layout.BorderPane;

/**Vue contenant à la fois la map contenant les objets selectionnés(MatrixLvlEditorView)
 * et le menu de selection  */
public class GlobalEditorView extends BorderPane {


    public GlobalEditorView(int width, int height, int nbOfRows,
                            int nbOfColumns) {
        super();

        this.setWidth(width);
        this.setHeight(height);


    }
}