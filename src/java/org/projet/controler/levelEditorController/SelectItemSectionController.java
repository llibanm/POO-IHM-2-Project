package src.java.org.projet.controler.levelEditorController;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.java.org.projet.controler.AbstractControler;
import src.java.org.projet.interfaces.MyConcreteObservable;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.view.levelEditorView.SelectItemView;

public class SelectItemSectionController extends MyConcreteObservable {
        SelectItemSectionModel model;
        SelectItemView view;


    public SelectItemSectionController(SelectItemSectionModel model, SelectItemView view) {
        this.model = model;
        this.view = view;
        //model.addPropertyChangeListener(this.getPropertyChangeListener());
        //var listItem = this.view.getVbox().getChildren();
        //addListenerToIems();

    }

    /**On ajoute des listener pour chaque item du menu afin de les selectionner pour déposer
     * sur la map
     *  */
    public void addListenerToIems() {
        VBox vbox = view.getVbox();
        int i = -1;
        var itemsList = model.getItemsOfSelectionMenu();
        if (vbox != null) {
            for (Node item : vbox.getChildren()) {
                i++;
                int finalI = i;
                item.setOnMouseClicked(e-> {
                    System.out.println("Item click from menu : " + item);
                    model.setCurrentSelectedItem(itemsList.get(finalI));
                    System.out.println("Item selected from menu "+model.getCurrentSelectedItem());
                    this.getPropertyChangeSupport().firePropertyChange("selectedItem", null, model.getCurrentSelectedItem());

                });
            }
        }
        else {
            System.out.println("VBox is null");
        }
    }
    public  void onItemClicked(CaseMatrix caseMatrix) {

        System.out.println("onItemClicked"+ caseMatrix);

    }
}
