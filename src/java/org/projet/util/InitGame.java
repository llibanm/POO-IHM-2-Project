package src.java.org.projet.util;

import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;

import java.util.ArrayList;
import java.util.List;

public class InitGame {

    public static  List<MatrixLvlEditorModel> generateAllsLevels(){
        int size = 20;
        MatrixLvlEditorModel matrixLvlEditorModel = new MatrixLvlEditorModel(size,size,"src/java/org/projet/assets/planet/planet03.png");
        MatrixLvlEditorModel matrixLvlEditorModelMars = new MatrixLvlEditorModel(size,size,"src/java/org/projet/assets/planet/planet00.png");
        MatrixLvlEditorModel matrixLvlEditorModelSaturn = new MatrixLvlEditorModel(size,size,"src/java/org/projet/assets/planet/planet02.png");
        MatrixLvlEditorModel matrixLvlEditorModelJupiter = new MatrixLvlEditorModel(size,size,"src/java/org/projet/assets/planet/planet04.png");
        List<MatrixLvlEditorModel> matrixLvlEditorModelList = new ArrayList<>();

        matrixLvlEditorModelList.add(matrixLvlEditorModel);
        matrixLvlEditorModelList.add(matrixLvlEditorModelMars);
        matrixLvlEditorModelList.add(matrixLvlEditorModelSaturn);
        matrixLvlEditorModelList.add(matrixLvlEditorModelJupiter);
        return matrixLvlEditorModelList;
    }
}