package src.java.org.projet.interfaces;

public abstract class Views4OrientationImgCharacter {
    private   String upUrlImg = "src/java/org/projet/assets/character/hero/u.png";
    private  String downUrlImg = "src/java/org/projet/assets/character/hero/d.png";
    private  String rightUrlImg = "src/java/org/projet/assets/character/hero/r.png";
    private   String leftUrlImg = "src/java/org/projet/assets/character/hero/l.png";

    public Views4OrientationImgCharacter() {}
    public Views4OrientationImgCharacter(String upUrlImg, String downUrlImg, String rightUrlImg, String leftUrlImg) {
        this.upUrlImg = upUrlImg;
        this.downUrlImg = downUrlImg;
        this.rightUrlImg = rightUrlImg;
        this.leftUrlImg = leftUrlImg;
    }

    public String getUpUrlImg() {
        return upUrlImg;
    }

    public void setUpUrlImg(String upUrlImg) {
        this.upUrlImg = upUrlImg;
    }

    public String getDownUrlImg() {
        return downUrlImg;
    }

    public void setDownUrlImg(String downUrlImg) {
        this.downUrlImg = downUrlImg;
    }

    public String getRightUrlImg() {
        return rightUrlImg;
    }

    public void setRightUrlImg(String rightUrlImg) {
        this.rightUrlImg = rightUrlImg;
    }

    public String getLeftUrlImg() {
        return leftUrlImg;
    }

    public void setLeftUrlImg(String leftUrlImg) {
        this.leftUrlImg = leftUrlImg;
    }

    public  String coordToImage(int Row, int Col) {
        if (Row == -1 && Col == 0) {
            return upUrlImg;
        }
        else if (Row == 1 && Col == 0) {
            return downUrlImg;
        }
        else if (Row == 0 && Col == 1) {
            return rightUrlImg;
        }
        else if (Row == 0 && Col == -1) {
            return leftUrlImg;
        }
        else {
            System.out.println("Paramètre non adapté coordToImage");
            return "";
        }
    }

    @Override
    public String toString() {
        return "Views4OrientationImgCharacter{" +
                "upUrlImg='" + upUrlImg + '\'' +
                ", downUrlImg='" + downUrlImg + '\'' +
                ", rightUrlImg='" + rightUrlImg + '\'' +
                ", leftUrlImg='" + leftUrlImg + '\'' +
                '}';
    }
}
