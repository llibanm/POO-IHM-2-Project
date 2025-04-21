package model.modelLevelEditor;

import java.util.List;

/**Classe pour gérer les données du menu de selection des items à placer
 * sur la map de l'éditeur de niveau */
public abstract class SelectItemSectionModel {
    List<ItemToPlaceOnMap> itemsOfSelectionMenu;

    public SelectItemSectionModel(List<ItemToPlaceOnMap> itemsOfSelectionMenu) {
        this.itemsOfSelectionMenu = itemsOfSelectionMenu;
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
