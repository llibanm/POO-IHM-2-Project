package src.java.org.projet.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dataset {

    private static final Dataset instance = new Dataset();

    private List<String> data = new ArrayList<>();
    private Map<String, String> mapString = new HashMap<>();
    private Map<String, Integer> mesure = new HashMap<>();
    private Map<String, Float> mesuref = new HashMap<>();
    private Map<String, Double> mesured = new HashMap<>();
    private Map<String, String> configMap = new HashMap<>();

    private Dataset() {
        initMesured(listeMesured);
        initStrings(listeMsg);
        initMesures(listeMesure);
        initConfiguration();
    }

    List<Pair<String, String>> listeMsg = List.of(
            makePair("AUTHOR","Clara Schobert\nMarcel Dzengue Bony\nAbbes Samy\nMohamed Ali Liban" ),
            makePair("mission_hero", "But:"+"\nArriver au dernier niveau et battre le boss avec le plus de munitions et santé\n"+
                    "Hints: Déplacements(z,q,s,d)\nInteraction avec les objets(r)\nTir(f)"),
            makePair("JOUERMU", "Joueur"),
            makePair("TESTLVL","Tester/animer niveau actuel" ),
            makePair("LOOKRANK", "Voir le classement"),
            makePair("IMPORTER", "Importer"),
            makePair("CONFIG", "Configuration"),
            makePair("EXPORT","Exporter niveau"),
            makePair("theme", "dark"),
            makePair("selectedItem", "selectedItem"),
            makePair("move", "move"),
            makePair("fireHero", "fireHero"),
            makePair("removeItem", "removeItem"),
            makePair("DEFAULTSCOREPATH", "json/score.json" ),
            makePair("DEFAULT_GAME_IMPORT_JSON_PATH", "json/defaultLevels.json"),
            makePair("DEFAULT_IMPORT_JSON_PATH", "json/levels.json"),
            makePair("HEROPNG", "src/java/org/projet/assets/Hero.png"),
            makePair("DEFAULTHEROPNG", "src/java/org/projet/assets/defaultHero.png"),
            makePair("DEFAULT_HERO_BULLET_PATH","src/java/org/projet/assets/bule.png"),
            makePair("DEFAULT_ENNEMY_BULLET_PATH","src/java/org/projet/assets/buleE.png"),
            makePair("DEFAULT_ASSET_PATH","src/java/org/projet/assets/"),
            makePair("PLANET_0_PATH", "src/java/org/projet/assets/planet/planet03.png"),
            makePair("PLANET_1_PATH", "src/java/org/projet/assets/planet/planet00.png"),
            makePair("PLANET_2_PATH", "src/java/org/projet/assets/planet/planet02.png"),
            makePair("PLANET_3_PATH", "src/java/org/projet/assets/planet/planet09.png"),
            makePair("DEFAULT_RECT_COLOR", "TRANSPARENT"), //Couleur du rectangle entourant chaque case //TRANSPARENT
            makePair("", ""),
            makePair("", ""),
            makePair("", "")



    );

    List<Pair<String, Double>> listeMesured = List.of(
          new Pair<>("deltaTimeGL", 0.05)
    );

    List<Pair<String, Integer>> listeMesure = List.of(
            new Pair<>("ROW_HERO_APPARITION", 17),
            new Pair<>("COL_HERO_APPARITION", 10),
            new Pair<>("SCENE_V",1366),
            new Pair<>("SCENE_V1",768),
            new Pair<>("DEFAULT_AGRESSOR_REMOVE_HP_HERO",2),
            new Pair<>("DEFAULT_ARROW_REMOVE_HP_HERO",2),
            new Pair<>("DEFAULT_AGRESSOR_SPEED",1),
            new Pair<>("DEFAULT_AGRESSOR_PORTEE_ATK",6),
            new Pair<>("DEFAULT_BOSS_PORTEE_ATK",12),
            new Pair<>("DEFAULT_BOSS_REMOVE_HP_HERO",2),
            new Pair<>("DEFAULT_BOSS_SPEED",6),
            new Pair<>("DEFAULT_HERO_SPEED",1),
            new Pair<>("DEFAULT_ARROW_SPEED",3),
            new Pair<>("DEFAULT_INCREASEMENT_LASER",3),
            new Pair<>("DEFAULT_INCREASEMENT_ARROW",5),
            new Pair<>("DEFAULT_NB_ROW_COL",20),  //nombre de lignes et colonne de chaque map vue et  map
            new Pair<>("DEFAULT_CASE_SIZE_VIEW",40), // Taille d'une case dans la vue : 40
            new Pair<>("BACKGROUND_SIZE_V1",50),
            new Pair<>("BACKGROUND_SIZE_V2",50),
            new Pair<>("",0)

    );



    public static Dataset getInstance() {
        return instance;
    }

    public Map<String, String> getConfigMap() {
        return this.configMap;
    }

    public void setConfigMap(final Map<String, String> configMap) {
        this.configMap = configMap;
        configScreenSize();
        configLang();
    }

    private void configLang() {
        if(configMap.get("langue").equals("ang")){
            addString("mission_hero", "Goal:\nReach the final level and defeat the boss with the most ammo and health\nHints: Move (z,q,s,d)\nInteract with objects (r)\nShoot (f)");
            addString("JOUERMU", "Player");
            addString("TESTLVL", "Test/Play current level");
            addString("LOOKRANK", "View leaderboard");
            addString("IMPORTER", "Import");
            addString("CONFIG", "Settings");
            addString("EXPORT", "Export level");

        }
    }

    public  int toInt(String string) {
        return Integer.parseInt(string);
    }
    private void configScreenSize() {
        addMesure("SCENE_V", toInt(configMap.get("largeur")));
        addMesure("SCENE_V1", toInt(configMap.get("hauteur")));
    }

    public  void initConfiguration() {
        Map<String, String> res = new HashMap<>();
        res.put("droite", "D");
        res.put("gauche", "Q");
        res.put("haut", "Z");
        res.put("bas", "S");
        res.put("largeur", "800");
        res.put("hauteur", "800");
        res.put("langue", "fr");
        res.put("interagir", "R");
        res.put("tir", "F");
        configMap = res;
        //return  res;
    }

    public Pair<String, String> makePair(String key, String value) {
        return new Pair<>(key, value);
    }

    public void initStrings(List<Pair<String, String>> values) {
        for (Pair<String, String> p : values) {
            mapString.put(p.getKey(), p.getValue());
        }
    }

    public void initMesures(List<Pair<String, Integer>> values) {
        for (Pair<String, Integer> p : values) {
            mesure.put(p.getKey(), p.getValue());
        }
    }

    public void initMesuref(List<Pair<String, Float>> values) {
        for (Pair<String, Float> p : values) {
            mesuref.put(p.getKey(), p.getValue());
        }
    }

    public void initMesured(List<Pair<String, Double>> values) {
        for (Pair<String, Double> p : values) {
            mesured.put(p.getKey(), p.getValue());
        }
    }

    public List<String> getData() {
        return data;
    }

    public void addData(String value) {
        data.add(value);
    }

    public void addString(String key, String value) {
        mapString.put(key, value);
    }

    public String getString(String key) {
        return mapString.get(key);
    }

    public Map<String, String> getAllStrings() {
        return mapString;
    }

    public void addMesure(String key, Integer value) {
        mesure.put(key, value);
    }

    public Integer getMesure(String key) {
        return mesure.get(key);
    }

    public Map<String, Integer> getAllMesures() {
        return mesure;
    }

    public void addMesuref(String key, Float value) {
        mesuref.put(key, value);
    }

    public Float getMesuref(String key) {
        return mesuref.get(key);
    }

    public Map<String, Float> getAllMesuresf() {
        return mesuref;
    }

    public void addMesured(String key, Double value) {
        mesured.put(key, value);
    }

    public Double getMesured(String key) {
        return mesured.get(key);
    }

    public Map<String, Double> getAllMesuresd() {
        return mesured;
    }
}
