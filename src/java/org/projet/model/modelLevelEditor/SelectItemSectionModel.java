package src.java.org.projet.model.modelLevelEditor;

import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;

import java.util.ArrayList;
import java.util.List;

/**Classe pour gérer les données du menu de selection des items à placer
 * sur la map de l'éditeur de niveau */
public  class SelectItemSectionModel extends AbstractModel {
    List<CaseMatrix> itemsOfSelectionMenu;
    //Item que l'utilisateur a selectionné pour positionner sur le plateau
    CaseMatrix currentSelectedItem;

    public CaseMatrix getCurrentSelectedItem() {
        return currentSelectedItem;
    }

    public void setCurrentSelectedItem(CaseMatrix currentSelectedItem) {
        this.currentSelectedItem = currentSelectedItem;
    }

    public SelectItemSectionModel() {
        super("Select Item");
        this.itemsOfSelectionMenu = new ArrayList<CaseMatrix>();

    }

    public void addItem(CaseMatrix item) {
        itemsOfSelectionMenu.add(item);
    }
    public void removeItem(CaseMatrix item) {
        itemsOfSelectionMenu.remove(item);
    }

    public List<CaseMatrix> getItemsOfSelectionMenu() {
        return itemsOfSelectionMenu;
    }

    public void setItemsOfSelectionMenu(List<CaseMatrix> itemsOfSelectionMenu) {
        this.itemsOfSelectionMenu = itemsOfSelectionMenu;
    }

    @Override
    public String toString() {
        return "SelectItemSectionModel{" +
                "itemsOfSelectionMenu=" + itemsOfSelectionMenu +
                '}';
    }
}
