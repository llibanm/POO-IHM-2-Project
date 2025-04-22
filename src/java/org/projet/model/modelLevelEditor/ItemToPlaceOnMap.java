package src.java.org.projet.model.modelLevelEditor;

/**
 * Juste la liste des items qu'on choisira pour placer sur la map de l'editeur de  niveau
 */
public class ItemToPlaceOnMap {
    Class<?> classOfItems;
    String urlOfItemImg;
    String nameOfItem;

    public ItemToPlaceOnMap(Class<?> classOfItems, String urlOfItemImg, String nameOfItem) {
        this.classOfItems = classOfItems;
        this.urlOfItemImg = urlOfItemImg;
        this.nameOfItem = nameOfItem;
    }
    @Override
    public String toString() {
        return "ItemToPlaceOnMap{" +
                "classOfItems=" + classOfItems +
                ", urlOfItemImg='" + urlOfItemImg + '\'' +
                ", nameOfItem='" + nameOfItem + '\'' +
                '}';
    }
    public Class<?> getClassOfItems() {
        return classOfItems;
    }

    public void setClassOfItems(Class<?> classOfItems) {
        this.classOfItems = classOfItems;
    }

    public String getUrlOfItemImg() {
        return urlOfItemImg;
    }

    public void setUrlOfItemImg(String urlOfItemImg) {
        this.urlOfItemImg = urlOfItemImg;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }


}
