package src.java.org.projet.model.modelLevelEditor.base;

public class CaseMatrix {
    //Image de l'item
    String urlImgToShow;
    Class<?> classOfItems;
    int coordX;

    public Class<?> getClassOfItems() {
        return classOfItems;
    }

    public void setClassOfItems(Class<?> classOfItems) {
        this.classOfItems = classOfItems;
    }

    int coordY;
    //largeur,hauteur occupé par l'item sur la matrice de l'éditeur de niveau
    int width;
    int height;



    public CaseMatrix(){}
    public CaseMatrix(String urlImgToShow, Class<?> classOfItems, int coordX, int coordY, int width, int height) {
        this.urlImgToShow = urlImgToShow;
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = width;
        this.height = height;
        this.classOfItems = classOfItems;
    }
    //Constructeur secondaire pour le cas ou l'objet occupe une seule case de la matrice
    public CaseMatrix(String urlImgToShow, int coordX, int coordY) {
        this.urlImgToShow = urlImgToShow;
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = 1;
        this.height = 1;
    }

    @Override
    public String toString() {
        return "CaseMatrix{" +
                "urlImgToShow='" + urlImgToShow + '\'' +
                ", classNameITem='" + classOfItems + '\'' +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
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

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
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
