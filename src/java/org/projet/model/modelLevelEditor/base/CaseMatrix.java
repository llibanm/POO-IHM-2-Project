package src.java.org.projet.model.modelLevelEditor.base;


/**
 * Un case de la matrice de jeux contient les éléments principaux l'image de fond, son
 *état occupé ou non, et ses coordonnées les items seront insérés dessus
 *
 *
 */
public class CaseMatrix {
    //Image de l'item
    String urlImgToShow;
    Object classOfItems;
    boolean isOccuped;
    int coordRow;
    int coordCol;
    //largeur,hauteur occupé par l'item sur la matrice de l'éditeur de niveau
    int width;
    int height;



    public CaseMatrix(){}
    public CaseMatrix(String urlImgToShow, Object classOfItems, int coordrow, int coordcol, int width, int height) {
        this.urlImgToShow = urlImgToShow;
        this.coordRow = coordrow;
        this.coordCol = coordcol;
        this.width = width;
        this.height = height;
        this.classOfItems = classOfItems;
    }
    //Constructeur secondaire pour le cas ou l'objet occupe une seule case de la matrice
    public CaseMatrix(String urlImgToShow, int coordrow, int coordcol) {
        this.urlImgToShow = urlImgToShow;
        this.coordRow = coordrow;
        this.coordCol = coordcol;
        this.width = 1;
        this.height = 1;
    }

    public boolean isOccuped() {
        return isOccuped;
    }

    public void setOccuped(boolean occuped) {
        isOccuped = occuped;
    }

    public Object getClassOfItems() {
        return classOfItems;
    }

    public void setClassOfItems(Object classOfItems) {
        this.classOfItems = classOfItems;
    }

    @Override
    public String toString() {
        return "CaseMatrix{" +
                "urlImgToShow='" + urlImgToShow + '\'' +
                ", classNameITem='" + classOfItems + '\'' +
                ", coordRow=" + coordRow +
                ", coordCol=" + coordCol +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public String getUrlImgToShow() {
        return urlImgToShow;
    }

    public void setUrlImgToShow(String urlImgToShow) {
        this.urlImgToShow = urlImgToShow;
    }

    public int getCoordRow() {
        return coordRow;
    }

    public void setCoordRow(int coordRow) {
        this.coordRow = coordRow;
    }

    public int getCoordCol() {
        return coordCol;
    }

    public void setCoordCol(int coordCol) {
        this.coordCol = coordCol;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
