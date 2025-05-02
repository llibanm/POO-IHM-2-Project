package src.java.org.projet.model.modelLevelEditor;

import src.java.org.projet.controler.levelEditorController.MatrixLvlEditorController;
import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**Classe pour gérer les données du menu de selection des items à placer
 * sur la map de l'éditeur de niveau */
public  class SelectItemSectionModel extends AbstractModel {
    private  final Logger logger = Logger.getLogger(SelectItemSectionModel.class.getName());
    List<CaseMatrix> itemsOfSelectionMenu;
    /**Item que l'utilisateur a selectionné pour positionner sur le plateau*/
    CaseMatrix currentSelectedItem;


    public SelectItemSectionModel() {
        super("Select Item");
        this.itemsOfSelectionMenu = new ArrayList<CaseMatrix>();

    }

    /**
     * @return Obtenir la case active du menu de sélection
     */
    public CaseMatrix getCurrentSelectedItem() {
        return currentSelectedItem;
    }

    /**
     * Changer la selection courante et notifier les observateurs
     * @param currentSelectedItem
     */
    public void setCurrentSelectedItem(CaseMatrix currentSelectedItem) {
        CaseMatrix oldValue = this.currentSelectedItem;
        this.currentSelectedItem = currentSelectedItem;
        logger.info(oldValue + " " + this.currentSelectedItem);
        getPropertyChangeSupport().firePropertyChange("currentSelectedItem", oldValue, currentSelectedItem);
    }


    /**
     *Ajouter un item dans le menu de selection
     * @param item
     */
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
