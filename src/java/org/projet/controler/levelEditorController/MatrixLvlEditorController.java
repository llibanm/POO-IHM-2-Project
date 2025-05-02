package src.java.org.projet.controler.levelEditorController;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.model.modelLevelEditor.base.Score;
import src.java.org.projet.model.modelMap.Location;
import src.java.org.projet.view.levelEditorView.HeroStateView;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static src.java.org.projet.view.util.PopupMsg.showMessage;
import static src.java.org.projet.view.util.PopupMsg.showScores;

/**
 * Controleur global, il écoute les messages des provenant de des vues et modèles
 *
 */
public class MatrixLvlEditorController implements PropertyChangeListener {

    private final MyLogger logger = new MyLogger(MatrixLvlEditorController.class);
   /**Modèle de la map actuelle  */
    private MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    HeroStateView heroStateView;

    private CaseMatrix currentSelectedCaseMatrix;
    private SelectItemSectionController selectItemSectionController;
    private GameLogic gameLogic;
    GameModel gameModel;

    Dataset dataset = Dataset.getInstance();
    private Map<String, String> configMap = dataset.getConfigMap();


    public MatrixLvlEditorController(MatrixLvlEditorModel model, MatrixLvLEditorView view, HeroStateView heroStateView) {
        this.model = model;
        this.view = view;
        addGridListenersOnView();
        gameLogic = new GameLogic(model);
        this.heroStateView = heroStateView;

    }

    /**
     * Constructeur
     * @param game ensemble des maps du jeu
     * @param view vue principal de la map
     * @param heroStateView vue affichant l'état du hero
     * @param selectItemSectionController controller
     */
    public MatrixLvlEditorController(GameModel game, MatrixLvLEditorView view, HeroStateView heroStateView, SelectItemSectionController selectItemSectionController) {
        // this.model = model;
        this.heroStateView = heroStateView;
        this.view = view;
        this.gameModel = game;
        //selectItemSectionController.addPropertyChangeListener(this.getPropertyChangeListener());
        this.selectItemSectionController = selectItemSectionController;
        selectItemSectionController.addPropertyChangeListener(this);
        initController();

    }

    /**
     *  initialisation
     */
    public void initController() {

        initModels();
        initView();
        showMessage(dataset.getString("mission_hero"));

    }

    private void initModels() {
        model = gameModel.getCurrentLevel();
        model.init();
        subscriptionToModel();
        if(gameLogic != null) {
            gameLogic.stopMovementLoop();
        }
        gameLogic = new GameLogic(model);
        gameLogic.setGameModel(gameModel);
    }


    /**
     * Initialisation de la vue, listener des touches claviers...
     */
    private void initView() {
        getFocusOnMatrixView();
        view.initializeReset();
        view.setBackground(model.getUrlBackground());
        addGridListenersOnView();
        view.setOnKeyPressed(this::handleKeyPressed);
        model.showItemModel();
    }

    /**
     *Ecoute des messages des provenants du modèle principal
     */
    private void subscriptionToModel() {
        model.addPropertyChangeListener(this);
    }

    /**
     * Arrêt de l'écoute des messages provenant du modèle principal
     */
    private void unSubscriptionToModel() {
        //model.addPropertyChangeListener(this);
        model.removePropertyChangeListener(this);
    }

    /**
     * Changement de la location courante
     * @param location nouvelle location
     */
    public void updateLocation(Location location) {

        logger.info("Update location " + location);
        //unSubscriptionToModel();
        //gameLogic.stopMovementLoop();
        Hero hero = model.getHero();
        hero.setCoord(new Coord(dataset.getMesure("ROW_HERO_APPARITION"), dataset.getMesure("COL_HERO_APPARITION")));
        gameModel.setCurrentLevel(location.getIndexOnWorldMap());
        /*
        model = gameModel.getCurrentLevel();
        gameLogic.setModel(model);
         */
        initModels();
        //gameLogic.init();
        model.setHero(hero);
        initView();
    }

    private void updateLocationBis() {
       // unSubscriptionToModel();
        //this.model = gameModel.getCurrentLevel();
        //gameLogic.stopMovementLoop();
        //gameLogic.setModel(model);
        //gameLogic.init();
        //subscriptionToModel();
        Hero hero = model.getHero();
        int row = hero.getCoord().getRow();
        int col = hero.getCoord().getCol();
        model.resetItemMatrice(row, col);
        view.removeItemFromGridPane(row, col);
        hero.setCoord(new Coord(17, 10));
        this.model.setHero(hero);
        initView();
        model.showItemModel();


    }


    /**
     * Récepteur des messages issues du modèle, mise à jour de
     * la vue en conséquence
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        logger.severe("Changement " + evt.getPropertyName() + " propertyChange");

        if (dataset.getString("selectedItem").equals(evt.getPropertyName())) {
            handleSelectedItem(evt);
        } else if (dataset.getString("move").equals(evt.getPropertyName())) {
            handleItemMovement(evt);
        } else if (dataset.getString("fireHero").equals(evt.getPropertyName())) {
            handleHeroShoot(evt);

            logger.info("Current selectedCaseMatrix is " + currentSelectedCaseMatrix);
        } else if (dataset.getString("removeItem").equals(evt.getPropertyName())) {
            handleRemoveItem(evt);
        } else if ("UpdateHeroState".equals(evt.getPropertyName())) { // TODO
            handleUpdateHeroState(evt);

        } else if ("location".equals(evt.getPropertyName())) { // TODO
            handleLocationChange(evt);

        } else if ("showModelCase".equals(evt.getPropertyName())) { // TODO
            handleShowModelCase(evt);

        }else if ("endOfTheGame".equals(evt.getPropertyName())) { // TODO
            handleEndOfTheGame(evt);

        }
        else {
            logger.info("Erreur propertyChange");
        }
    }

    /** Fin du jeu*
      * @param evt
     */
    private void handleEndOfTheGame(PropertyChangeEvent evt) {
        logger.info("EndOfTheGame propertyChange");
        gameLogic.stopMovementLoop();
        heroStateView.updateHeroState(model.getHero());

        List<String> champs = List.of("Nom");
        Platform.runLater(() -> {
            Optional<Map<String, String>> result = demanderInfos(champs);
            result.ifPresent(map -> {
                String nom = map.get("Nom");
                gameModel.addScore(new Score(nom, 10));
                showScores(gameModel.getHallOfFame());
                try {
                    gameModel.exporterScores(gameModel.getDefaultScorePath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }


    /**
     * Afficher une case spécifique du modèle
     * @param evt
     */
    private void handleShowModelCase(PropertyChangeEvent evt) {
        CaseMatrix newLocation = (CaseMatrix) evt.getNewValue();
        logger.info("Affichage des cases du model  " + newLocation);
        view.placeItemImg(newLocation.getUrlImgToShow(), newLocation.getCoordRow(), newLocation.getCoordCol());
    }

    /**
     * Changer de Location
     * @param evt
     */
    private void handleLocationChange(PropertyChangeEvent evt) {
        Location newLocation = (Location) evt.getNewValue();
        logger.info("Maj de la location " + newLocation);
        updateLocation(newLocation);
    }

    /**
     * Mise à jour de la vue liées aux stat du héros
     * @param evt
     */
    private void handleUpdateHeroState(PropertyChangeEvent evt) {
        Hero hero = (Hero) evt.getNewValue();
        logger.info("Maj des stats du héros " + hero);
        heroStateView.updateHeroState(hero);
    }

    /**
     * Gestion de la suppression des items
     * @param evt
     */
    private void handleRemoveItem(PropertyChangeEvent evt) {
        logger.severe("Reception du signal remove item");
        Coord old = (Coord) evt.getOldValue();
        view.resetCell(old.getRow(), old.getCol());
    }

    /**
     * Gestion du tir du héro
     * @param evt
     */
    private void handleHeroShoot(PropertyChangeEvent evt) {
        logger.severe("Reception du signal de tir");
        CaseMatrix bullet = (CaseMatrix) evt.getNewValue();
        view.placeItemImg(bullet.getUrlImgToShow(), bullet.getCoordRow(), bullet.getCoordCol());
        logger.info("CaseMatrix  Flèche " + bullet);
    }

    /**
     * Gestion  des mouvement des entités, mise à jour de la vue
     * @param evt
     */
    private void handleItemMovement(PropertyChangeEvent evt) {
        logger.severe("Movement " + evt.getPropertyName() + " propertyChange");
        Coord old = (Coord) evt.getOldValue();
        Movable ennemy = (Movable) evt.getNewValue();
        logger.info(ennemy + " old coord is " + old);
        logger.info("Entity coord is " + ennemy);

        int rowX = ennemy.getCoord().getRow() - old.getRow();
        int rowY = ennemy.getCoord().getCol() - old.getCol();
        ImageView url = ennemy.nextImage(rowX, rowY);
        view.updateHeroPositionViewBis(old.getRow(), old.getCol(), url, ennemy.getCoord().getRow(), ennemy.getCoord().getCol());
        setNodeListener(view.getNodeAt(old.getRow(), old.getCol()));
        logger.info("Current selectedCaseMatrix is " + currentSelectedCaseMatrix);
    }

    /**
     * Gestion de la sélection d'un item, mise à jour de
     * la case courante sélectionnées, que la matrice utilisera pour
     * placer les items sur la map.
     * @param evt
     */

    private void handleSelectedItem(PropertyChangeEvent evt) {
        currentSelectedCaseMatrix = (CaseMatrix) evt.getNewValue();
        logger.info("Current selectedCaseMatrix is " + currentSelectedCaseMatrix);
    }

    /**
     * Déplacement du héro
     * @param rowX décalage en row
     * @param colY décalag en colonne
     */
    public void movelogic(int rowX, int colY) {
        gameLogic.moveHero(rowX, colY);
    }

    /**
     * Gestion des actions clavier pour déplacer le héros
     * @param keyEvent
     */
    public void handleKeyPressed(KeyEvent keyEvent) {
        String code = keyEvent.getCode().toString();
        String hautKey = configMap.get("haut");

        if (code.equals(hautKey)) {
            logger.info("Appui sur Z (ou la touche configurée pour 'haut')");
            movelogic(-1, 0);
        } else if (code.equals(configMap.get("droite"))) {
            logger.info("Appui sur D");
            movelogic(0, 1);
        } else if (code.equals(configMap.get("bas"))) {
            logger.info("Appui sur S");
            movelogic(1, 0);
        } else if (code.equals(configMap.get("gauche"))) {
            logger.info("Appui sur Q");
            movelogic(0, -1);
        } else if (code.equals(configMap.get("tir"))) {
            logger.info("Héro tire!!");
            gameLogic.heroShot();
        } else if (code.equals(configMap.get("interagir"))) {
            logger.info("Héro intéragit avec les objets autours");
            gameLogic.interactWithObjects();
            heroStateView.updateHeroState(model.getHero());
        }

    }

    /**
     * Obtenir les infos de configuration
     * @return
     */
    public Optional<Map<String, String>> demanderInfos() {
        Dialog<Map<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Configuration");

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();

        TextField droite = new TextField("d");
        TextField gauche = new TextField("q");
        TextField haut = new TextField("z");
        TextField bas = new TextField("s");
        TextField largeur = new TextField("800");
        TextField hauteur = new TextField("600");
        TextField langue = new TextField("fr");
        TextField interact = new TextField("r");
        TextField shoot = new TextField("f");

        grid.add(new Label("Droite :"), 0, 0); grid.add(droite, 1, 0);
        grid.add(new Label("Gauche :"), 0, 1); grid.add(gauche, 1, 1);
        grid.add(new Label("Haut :"),   0, 2); grid.add(haut,   1, 2);
        grid.add(new Label("Bas :"),    0, 3); grid.add(bas,    1, 3);
        grid.add(new Label("Largeur :"),0, 4); grid.add(largeur,1, 4);
        grid.add(new Label("Hauteur :"),0, 5); grid.add(hauteur,1, 5);
        grid.add(new Label("Langue(fr|ang) :"), 0, 6); grid.add(langue, 1, 6);
        grid.add(new Label("Interagir :"),0, 7); grid.add(interact, 1, 7);
        grid.add(new Label("Tir :"),      0, 8); grid.add(shoot,    1, 8);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                Map<String, String> res = new HashMap<>();
                res.put("droite", droite.getText().toUpperCase());
                res.put("gauche", gauche.getText().toUpperCase());
                res.put("haut", haut.getText().toUpperCase());
                res.put("bas", bas.getText().toUpperCase());
                res.put("largeur", largeur.getText());
                res.put("hauteur", hauteur.getText());
                res.put("langue", langue.getText());
                res.put("interagir", interact.getText().toUpperCase());
                res.put("tir", shoot.getText().toUpperCase());
                this.configMap = res;
                return res;
            }
            return null;
        });

        return dialog.showAndWait();
    }


    /**
     * Obtenir le focus écran
     */
    public void getFocusOnMatrixView() {
        view.setFocusTraversable(true);
        view.requestFocus();
    }

    /**
     * Place un listener de click sur un node
     * @param node node
     */
    public void setNodeListener(Node node) {
        node.setOnMouseClicked(e -> {
            handleGridClick(node);
        });
    }

    /**
     * Pacer des listener sur chaque case de la map
     */
    public void addGridListenersOnView() {
        view.getChildren().forEach(node -> {
            node.setOnMouseClicked(e -> {
                handleGridClick(node);
            });
        });
    }

    /**
     * Gestion des clique sur les cases de la map
     * @param node une case de la map
     */

    private void handleGridClick(Node node) {
        logger.info("clicked on grid");
        getFocusOnMatrixView();
        if (currentSelectedCaseMatrix != null) {
            Integer col = GridPane.getColumnIndex(node);
            Integer row = GridPane.getRowIndex(node);

            if (col != null && row != null) {
                model.addItemMatrice(row, col, currentSelectedCaseMatrix);
                view.removeItemFromGridPane(row, col);
                view.placeItemImg(currentSelectedCaseMatrix.getUrlImgToShow(), row, col);
            }
        } else {
            logger.info("Erreur addGridListenersOnView currentSelectedCaseMatrix == null");
        }
    }

    /**
     * Listener pour gérer les cliques sur la barre principal
     * @param myPane
     */
    public void setupMenuListeners(Pane myPane) {

        MenuBar menubar = null;

        for (Node node : myPane.getChildren()) {
            if (node instanceof MenuBar) {
                menubar = (MenuBar) node;
                break;
            }
        }

        if (menubar == null) {
            System.out.println("Aucun MenuBar trouvé dans le Pane");
            return;
        }

        for (Menu menu : menubar.getMenus()) {
            for (MenuItem item : menu.getItems()) {
                item.setOnAction(event -> {
                    String itemName = item.getText();
                    if (itemName.equals(dataset.getString("JOUERMU"))) {
                        System.out.println("Lancement du jeu par défaut");
                    } else if (itemName.equals(dataset.getString("TESTLVL"))) {
                        System.out.println("Teste le niveau");
                        gameLogic.init();
                    } else if (itemName.equals(dataset.getString("LOOKRANK"))) {
                        System.out.println("Affichage du classement");
                        menuActionLookRank();
                    } else if (itemName.equals(dataset.getString("IMPORTER"))) {
                        System.out.println("Chargement du jeu depuis la dernière sauvegarde");
                        try {
                            handleImportLvl();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (itemName.equals(dataset.getString("Configuration"))) {
                        System.out.println("Ouverture des configurations");
                        demanderInfos();
                    } else if (itemName.equals(dataset.getString("EXPORT"))) {
                        System.out.println("Exportation du jeu");
                        try {
                            gameModel.exporterNiveaux("json/levels.json");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Action non définie pour: " + itemName);
                    }


                });
            }
        }
    }

    private void handleImportLvl() throws IOException {
        gameLogic.stopMovementLoop();
        gameModel.importerNiveaux(dataset.getString("DEFAULT_IMPORT_JSON_PATH"));
        initController();
       // model.initEntityWithMatrix();
        updateLocationBis();
    }

    /**
     * Afficher la liste des core
     */
    private void menuActionLookRank() {
        showScores(gameModel.getHallOfFame());
    }

    public void setPaneView(Pane myPane) {
        setupMenuListeners(myPane);
    }


    /**
     * Récupérer les infos de configurations du jeu
     * @param champs Liste des champs à récupérer
     * @return Map contenent comme clés le champ et l'entrée saisie
     */
    public  Optional<Map<String, String>> demanderInfos(List<String> champs) {
        Dialog<Map<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Saisie Infos");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        Map<String, TextField> fields = new HashMap<>();

        int row = 0;
        for (String champ : champs) {
            TextField tf = new TextField();
            grid.add(new Label(champ + " :"), 0, row);
            grid.add(tf, 1, row);
            fields.put(champ, tf);
            row++;
        }

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                Map<String, String> res = new HashMap<>();
                fields.forEach((k, v) -> res.put(k, v.getText()));
                return res;
            }
            return null;
        });

        return dialog.showAndWait();
    }





}