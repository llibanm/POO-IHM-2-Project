package src.java.org.projet.controler.levelEditorController;

import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public class GameLogic {
    private final MatrixLvlEditorModel model;

    public GameLogic(MatrixLvlEditorModel model) {
        this.model = model;
    }

    public boolean moveHero(int deltaRow, int deltaCol) {
        if (model.moveHero(deltaRow, deltaCol)) {
            model.getHero().setCoord(new Coord(
                    model.getHero().getCoord().getRow() + deltaRow,
                    model.getHero().getCoord().getCol() + deltaCol
            ));
            return true;
        }
        return false;
    }
}
