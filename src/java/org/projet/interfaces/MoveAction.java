package src.java.org.projet.interfaces;

/**
 * Classe representant une entité mouvable et
 * l'orientation de son déplacement dans la map.
 */
public  class MoveAction {
    Movable entity;
    int rowX, colY;

    public MoveAction(Movable entity, int rowX, int colY) {
        this.entity = entity;
        this.rowX = rowX;
        this.colY = colY;
    }

    public Movable getEntity() {
        return entity;
    }

    public void setEntity(Movable entity) {
        this.entity = entity;
    }

    public int getRowX() {
        return rowX;
    }

    public void setRowX(int rowX) {
        this.rowX = rowX;
    }

    public int getColY() {
        return colY;
    }

    public void setColY(int colY) {
        this.colY = colY;
    }
}