package src.java.org.projet.controler.levelEditorController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

public class GameLogic {
    private final MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    Timeline enemyMovementLoop;

    public GameLogic(MatrixLvlEditorModel model, MatrixLvLEditorView view) {
        this.model = model;
        this.view = view;
        startEnemyMovementLoop();
    }

    public boolean moveHero(int deltaRow, int deltaCol) {
        if (model.moveHero(deltaRow, deltaCol)) {
            Coord coord = new Coord(
                    model.getHero().getCoord().getRow() + deltaRow,
                    model.getHero().getCoord().getCol() + deltaCol
            );
            model.getHero().setCoord(coord);
            model.setOccuped(coord, true);
            return true;
        }
        return false;
    }



    public void startEnemyMovementLoop() {
        enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(1.0), e -> moveEnemies()));
        enemyMovementLoop.setCycleCount(Animation.INDEFINITE);
        enemyMovementLoop.play();
    }

    public void moveEnemies() {
        for (Ennemy ennemy : model.getEnnemies()) {
            System.out.println(ennemy);
            Coord oldPos = ennemy.getPosition();
            System.out.println("oldPos: " + oldPos);
            Coord newPos = aiComputeNextMove(ennemy);
            System.out.println("newPos: " + newPos);

            //view.moveItem(oldPos, newPos);

            if(!model.isOccupedCase(newPos.getRow(), newPos.getCol())) {
                ennemy.setPosition(newPos);
                model.setOccuped(oldPos, false);
                model.setOccuped(newPos, true);
                view.updateHeroPositionView(oldPos.getRow(),oldPos.getCol(),"src/java/org/projet/assets/character/ennemy/img.png",newPos.getRow(),newPos.getCol());
            }
        }
    }



    public Coord aiComputeNextMove(Ennemy ennemy) {
        Coord heroPos = model.getHero().getCoord();
        Coord ennemyPos = ennemy.getPosition();

        int newRow = ennemyPos.getRow();
        int newCol = ennemyPos.getCol();


        if (heroPos.getRow() < ennemyPos.getRow()) newRow--;
        else if (heroPos.getRow() > ennemyPos.getRow()) newRow++;
        else if (heroPos.getCol() < ennemyPos.getCol()) newCol--;
        else if (heroPos.getCol() > ennemyPos.getCol()) newCol++;

        if (!model.isOccupedCase(newRow, newCol)) {
            return new Coord(newRow, newCol);
        } else {
            return ennemyPos;
        }
    }



}
