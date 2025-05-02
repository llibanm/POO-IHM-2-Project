package src.java.org.projet.util;

import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Initialisation des model, ... du Jeu
 */
public class InitGame {

    Dataset dataset = Dataset.getInstance();

    /**
     * Initiatilisation des 4 maps
     * @return maps listes 4 maps
     */
    public static  List<MatrixLvlEditorModel> generateAllsLevels(){
        int size = Dataset.getInstance().getMesure("DEFAULT_NB_ROW_COL");
        MatrixLvlEditorModel matrixLvlEditorModel = new MatrixLvlEditorModel(size,size,Dataset.getInstance().getString("PLANET_0_PATH"));
        MatrixLvlEditorModel matrixLvlEditorModelMars = new MatrixLvlEditorModel(size,size, Dataset.getInstance().getString("PLANET_1_PATH"));
        MatrixLvlEditorModel matrixLvlEditorModelSaturn = new MatrixLvlEditorModel(size,size,Dataset.getInstance().getString("PLANET_2_PATH"));
        MatrixLvlEditorModel matrixLvlEditorModelJupiter = new MatrixLvlEditorModel(size,size,Dataset.getInstance().getString("PLANET_3_PATH"));
        List<MatrixLvlEditorModel> matrixLvlEditorModelList = new ArrayList<>();

        matrixLvlEditorModelList.add(matrixLvlEditorModel);
        matrixLvlEditorModelList.add(matrixLvlEditorModelMars);
        matrixLvlEditorModelList.add(matrixLvlEditorModelSaturn);
        matrixLvlEditorModelList.add(matrixLvlEditorModelJupiter);
        return matrixLvlEditorModelList;
    }
}