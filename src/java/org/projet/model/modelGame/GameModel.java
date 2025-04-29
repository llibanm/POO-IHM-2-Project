package src.java.org.projet.model.modelGame;

import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;

import java.util.List;

public class GameModel {
    List<MatrixLvlEditorModel> allLevels;

    MatrixLvlEditorModel currentLevel;

    public GameModel(List<MatrixLvlEditorModel> allLevels) {
        this.allLevels = allLevels;
        this.currentLevel = allLevels.get(0);
    }

    public List<MatrixLvlEditorModel> getAllLevels() {
        return allLevels;
    }

    public void setAllLevels(List<MatrixLvlEditorModel> allLevels) {
        this.allLevels = allLevels;
    }

    public MatrixLvlEditorModel getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(MatrixLvlEditorModel currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public String toString() {
        return "GameModel{" +
                "allLevels=" + allLevels +
                ", currentLevel=" + currentLevel +
                '}';
    }
}