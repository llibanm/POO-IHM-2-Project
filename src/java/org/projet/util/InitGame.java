package src.java.org.projet.util;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Initialisation des model, ... du Jeu
 */
public class InitGame {

    Dataset  dataset = Dataset.getInstance();

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

    public static  Pane getTopPaneView() {
        Dataset  dataset = Dataset.getInstance();
        //Section supérieur
        Pane myPane = new Pane();
        MenuBar menubar = new MenuBar();
        final Menu filemenu = new Menu("Jeu par défaut");
        //Item filemenu
        final MenuItem itemNew = new MenuItem(dataset.getString("JOUERMU"));
        filemenu.getItems().addAll(itemNew);

        //item affichage
        final Menu affichageMenu = new Menu("Créer Jeu");
        final MenuItem itemFscreen = new MenuItem(dataset.getString("TESTLVL"));
        final MenuItem exportMI = new MenuItem(dataset.getString("EXPORT"));
        affichageMenu.getItems().addAll(itemFscreen, exportMI);

        final Menu importExportMenu = new Menu("Importer");
        final MenuItem importMI = new MenuItem(dataset.getString("IMPORTER"));

        importExportMenu.getItems().addAll(importMI);

        final Menu hallOfFameMenu= new Menu("Rang");
        final MenuItem rankMI = new MenuItem(dataset.getString("LOOKRANK"));
        hallOfFameMenu.getItems().addAll(rankMI);


        final Menu configurationMenu= new Menu("Configuration");
        final MenuItem configMI = new MenuItem(dataset.getString("CONFIG"));
        configurationMenu.getItems().addAll(configMI);

        final Menu authorMenu= new Menu("Auteurs");
        final MenuItem authorMI = new MenuItem(dataset.getString("AUTHOR"));
        authorMenu.getItems().addAll(authorMI);

        menubar.getMenus().addAll(filemenu,affichageMenu, importExportMenu, hallOfFameMenu, configurationMenu,authorMenu);
        myPane.getChildren().addAll(menubar);
        return myPane;
    }

}