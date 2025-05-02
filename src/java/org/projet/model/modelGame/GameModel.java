package src.java.org.projet.model.modelGame;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelCharacter.Boss;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.model.modelMap.SimpleDoor;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;
import src.java.org.projet.model.modelLevelEditor.base.Score;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *Classe representant l'ensemble des maps du jeu
 */
public class GameModel {
    List<MatrixLvlEditorModel> allLevels;
    Dataset dataset = Dataset.getInstance();
    List<Score> hallOfFame;
    private String defaultScorePath = dataset.getString("DEFAULTSCOREPATH");

    private final MyLogger logger = new MyLogger(GameModel.class);
    MatrixLvlEditorModel currentLevel;

    /**
     * Constructeur
     * @param allLevels liste de Map
     */
    public GameModel(List<MatrixLvlEditorModel> allLevels) {
        super();
        this.allLevels = allLevels;
        this.currentLevel = allLevels.get(0);
        hallOfFame = new ArrayList<>();
        initScore();

    }

    /**
     * Chargement du score depuis le fichier json
     */
    public void initScore() {
        try {
            hallOfFame = importerScores(getDefaultScorePath());
        } catch (IOException e) {
            logger.severe("Impossible de charger les scores, initialisation par défaut.");
            hallOfFame = new ArrayList<>();
            hallOfFame.add(new Score("Mars", 0));
        }
    }

    /**
     *
     * @return la liste des maps
     */
    public List<MatrixLvlEditorModel> getAllLevels() {
        return allLevels;
    }

    /**
     * Changer la map courante
     * @param index index de la nouvelle map dans la liste des map
     */
    public void setCurrentLevel(int index) {
        if (index >= 0 && index < allLevels.size()) {
            this.currentLevel = allLevels.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid level index: " + index);
        }
    }

    /**
     * Ajouter le score d'un joueur
     * @param score score nom, score du joueur
     */
    public  void addScore(Score score){
        this.hallOfFame.add(score);
    }

    /**
     * Récupérer les scores
     * @return Liste de scores
     */
    public List<Score> getHallOfFame() {
        return this.hallOfFame;
    }

    /**
     *
     * @param hallOfFame
     */
    public void setHallOfFame(final List<Score> hallOfFame) {
        this.hallOfFame = hallOfFame;
    }


    public void setAllLevels(List<MatrixLvlEditorModel> allLevels) {
        this.allLevels = allLevels;
    }

    /**
     * Obtenir la map courante
     * @return
     */
    public MatrixLvlEditorModel getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Fixer une nouvelle map courante
     * @param currentLevel map à fixer
     */
    public void setCurrentLevel(MatrixLvlEditorModel currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     *
     * @return path ou sera stocké le fichier json du score
     */
    public String getDefaultScorePath() {
        return this.defaultScorePath;
    }

    /**
     *
     * @param defaultScorePath fixer le path du fichier json du score
     */
    public void setDefaultScorePath(final String defaultScorePath) {
        this.defaultScorePath = defaultScorePath;
    }

    /**
     * Exporter toutes les map des niveaux aux formats json
     * @param cheminFichier chemin d'export json
     * @throws IOException
     */
    public void exporterNiveaux(String cheminFichier) throws IOException {


        List<MatrixLvlEditorModel> niveaux = getAllLevels();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        new File(cheminFichier).getParentFile().mkdirs();

        try {
            Coord hero = getCurrentLevel().getHero().getCoord();
            //CaseMatrix beforeExporting = currentLevel.getCaseMatrix(hero.getRow(),hero.getCol());
           // currentLevel.resetItemMatrice(hero.getRow(),hero.getCol());
            mapper.writeValue(new File(cheminFichier), niveaux);
           // currentLevel.setCaseMatrix(hero.getRow(), hero.getCol(),beforeExporting);
            logger.info(niveaux.size() + " niveaux exportés vers: " + cheminFichier);
        } catch (JsonProcessingException e) {
            logger.error("Erreur export niveaux: " + e);
            throw new IOException("Erreur conversion niveaux en JSON", e);
        }
    }

    /**
     * Importer des niveaux stockés en json
     * @param cheminFichier Chemin d'import
     * @return Liste des Map des niveaux
     * @throws IOException
     */
    public List<MatrixLvlEditorModel> importerNiveaux(String cheminFichier) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        File file = new File(cheminFichier);
        if (!file.exists()) throw new FileNotFoundException("Fichier introuvable: " + cheminFichier);

        try {
            List<MatrixLvlEditorModel> niveaux = mapper.readValue(file, new TypeReference<>() {});
            setAllLevels(niveaux);
            instantiateAllCasesToJson();
            initAllModelsList();
            setCurrentLevel(niveaux.getFirst());
            return niveaux;
        } catch (JsonProcessingException e) {
            logger.error("Erreur import niveaux: " + e);
            throw new IOException("Erreur conversion JSON en niveaux", e);
        }
    }

    public void initAllModelsList() {
        for (MatrixLvlEditorModel level : allLevels) {
            level.initEntityWithMatrix();
        }
    }

    public void instantiateAllCasesToJson() throws JsonProcessingException {
        Map<String, Class<?>> typeMap = new HashMap<>();
        typeMap.put("SimpleDoor", SimpleDoor.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        for (MatrixLvlEditorModel level : allLevels) {
            for (int i = 0; i < level.getNbOfRows(); i++) {
                for (int j = 0; j < level.getNbOfCols(); j++) {
                    CaseMatrix case_ = level.getCaseMatrix(i,j);
                    String caseObj = case_.getClassNameOfItem();
                    if(caseObj != "null"){
                        Object classItem = case_.getClassOfItems();
                        if (classItem != null) {
                            String jsonStr = mapper.writeValueAsString(classItem);  // conversion
                            node = mapper.readTree(jsonStr);
                            // String type = node.get("ClassNameOfItem").asText();

                            Class<?> clazz = typeMap.get(caseObj);
                            if (clazz != null) {
                                Object obj = mapper.treeToValue(node, clazz);
                                case_.setClassOfItems(obj);
                            }
                        }

                    }

                }
            }
        }
    }

    /**
     * Exporter les scores en json
     * @param cheminFichier chemin d'export
     * @throws IOException
     */
    public void exporterScores(String cheminFichier) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        new File(cheminFichier).getParentFile().mkdirs();

        try {
            mapper.writeValue(new File(cheminFichier), hallOfFame);
            logger.info("Scores exportés vers: " + cheminFichier);
        } catch (JsonProcessingException e) {
            logger.error("Erreur export scores: " + e);
            throw new IOException("Erreur conversion scores en JSON", e);
        }
    }

    /**
     * Importer les Scores depuis un fichier json
     * @param cheminFichier chemin du fichier json
     * @return
     * @throws IOException
     */
    public List<Score> importerScores(String cheminFichier) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(cheminFichier);
        if (!file.exists()) throw new FileNotFoundException("Fichier introuvable: " + cheminFichier);

        try {
            List<Score> scores = mapper.readValue(file, new TypeReference<>() {});
            hallOfFame = scores;
            return scores;
        } catch (JsonProcessingException e) {
            logger.error("Erreur import scores: " + e);
            throw new IOException("Erreur conversion JSON en scores", e);
        }
    }


    @Override
    public String toString() {
        return "GameModel{" +
                "allLevels=" + allLevels +
                ", currentLevel=" + currentLevel +
                '}';
    }
}