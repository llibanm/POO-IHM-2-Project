package src.java.org.projet.controler.levelEditorController;

import javafx.scene.layout.HBox;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.view.levelEditorView.SelectItemView;

public class SelectItemSectionController {
        SelectItemSectionModel model;
        SelectItemView view;

    public SelectItemSectionController(SelectItemSectionModel model, SelectItemView view) {
        this.model = model;
        this.view = view;
    }

    public  void onItemClicked(CaseMatrix caseMatrix) {

        System.out.println("onItemClicked"+ caseMatrix);
    }
}
