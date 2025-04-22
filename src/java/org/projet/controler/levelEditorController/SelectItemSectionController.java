package src.java.org.projet.controler.levelEditorController;

import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.view.levelEditorView.SelectItemView;

public class SelectItemSectionController {
        SelectItemSectionModel model;
        SelectItemView view;

    public SelectItemSectionController(SelectItemSectionModel model, SelectItemView view) {
        this.model = model;
        this.view = view;
    }

    public  void onItemClicked(){
        System.out.println("onItemClicked");
    }
}
