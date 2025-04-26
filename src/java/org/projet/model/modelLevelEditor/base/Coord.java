package src.java.org.projet.model.modelLevelEditor.base;
/**Coordonnées dans la Matrice
 *  */
public class Coord {
    private int row,col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public  static Coord addCoord(Coord c1, Coord c2){
        return new Coord(c1.getRow()+c2.getRow(), c1.getCol()+c2.getCol());
    }
    public static Coord subCoord(Coord c1, Coord c2){
        return new Coord(c1.getRow()-c2.getRow(), c1.getCol()-c2.getCol());
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}