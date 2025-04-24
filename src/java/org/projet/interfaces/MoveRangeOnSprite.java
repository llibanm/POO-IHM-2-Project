package src.java.org.projet.interfaces;

import src.java.org.projet.model.modelLevelEditor.base.Coord;

public class MoveRangeOnSprite {

    Coord spriteMoveUpRange, spriteMoveRightRange,spriteMoveLeftRange,spriteMoveDownRange;

    public MoveRangeOnSprite(Coord spriteMoveUpRange, Coord spriteMoveRightRange, Coord spriteMoveLeftRange, Coord spriteMoveDownRange) {
        this.spriteMoveUpRange = spriteMoveUpRange;
        this.spriteMoveRightRange = spriteMoveRightRange;
        this.spriteMoveLeftRange = spriteMoveLeftRange;
        this.spriteMoveDownRange = spriteMoveDownRange;
    }

    public Coord getSpriteMoveUpRange() {
        return spriteMoveUpRange;
    }

    public void setSpriteMoveUpRange(Coord spriteMoveUpRange) {
        this.spriteMoveUpRange = spriteMoveUpRange;
    }

    public Coord getSpriteMoveRightRange() {
        return spriteMoveRightRange;
    }

    public void setSpriteMoveRightRange(Coord spriteMoveRightRange) {
        this.spriteMoveRightRange = spriteMoveRightRange;
    }

    public Coord getSpriteMoveLeftRange() {
        return spriteMoveLeftRange;
    }

    public void setSpriteMoveLeftRange(Coord spriteMoveLeftRange) {
        this.spriteMoveLeftRange = spriteMoveLeftRange;
    }

    public Coord getSpriteMoveDownRange() {
        return spriteMoveDownRange;
    }

    public void setSpriteMoveDownRange(Coord spriteMoveDownRange) {
        this.spriteMoveDownRange = spriteMoveDownRange;
    }
}