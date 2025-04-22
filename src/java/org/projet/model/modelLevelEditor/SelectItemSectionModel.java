package src.java.org.projet.model.modelLevelEditor;

import src.java.org.projet.model.modelLevelEditor.base.ItemToPlaceOnMap;

import java.util.ArrayList;
import java.util.List;

/**Classe pour gérer les données du menu de selection des items à placer
 * sur la map de l'éditeur de niveau */
public  class SelectItemSectionModel {
    List<ItemToPlaceOnMap> itemsOfSelectionMenu;
    //Item que l'utilisateur a selectionné pour positionner sur le plateau
    ItemToPlaceOnMap currentSelectedItem;

    public ItemToPlaceOnMap getCurrentSelectedItem() {
        return currentSelectedItem;
    }

    public void setCurrentSelectedItem(ItemToPlaceOnMap currentSelectedItem) {
        this.currentSelectedItem = currentSelectedItem;
    }

    public SelectItemSectionModel() {
        this.itemsOfSelectionMenu = new ArrayList<ItemToPlaceOnMap>();
    }

    public void addItem(ItemToPlaceOnMap item) {
        itemsOfSelectionMenu.add(item);
    }
    public void removeItem(ItemToPlaceOnMap item) {
        itemsOfSelectionMenu.remove(item);
    }

    public List<ItemToPlaceOnMap> getItemsOfSelectionMenu() {
        return itemsOfSelectionMenu;
    }

    public void setItemsOfSelectionMenu(List<ItemToPlaceOnMap> itemsOfSelectionMenu) {
        this.itemsOfSelectionMenu = itemsOfSelectionMenu;
    }

    @Override
    public String toString() {
        return "SelectItemSectionModel{" +
                "itemsOfSelectionMenu=" + itemsOfSelectionMenu +
                '}';
    }
}
