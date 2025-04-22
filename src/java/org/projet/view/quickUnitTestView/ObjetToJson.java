package src.java.org.projet.view.quickUnitTestView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;

import java.util.ArrayList;
import java.util.List;

public class ObjetToJson {

    public static void main(String[] args) throws JsonProcessingException {
        ObjetToJson.objetToJsonTest();
        ObjetToJson.levelEditorModelToJson();
    }
    public static void objetToJsonTest    () throws JsonProcessingException {

        Obj obj = new Obj();
        obj.x = 5;
        obj.y = 6;
        obj.a = new ArrayList<>();
        obj.a.add(1);
        //Conversion de l'objet en json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        System.out.println(json);
        //Récupération du json-> objet
        Obj regetObj = mapper.readValue(json, Obj.class);
        System.out.println(regetObj);


    }

    public static void levelEditorModelToJson() throws JsonProcessingException {
       /*
        var menu = new SelectItemSectionModel();
        CaseMatrix item = new CaseMatrix("src/java/org/projet/assets/character/ennemy/img.png",Hero.class,0,0,1,1);
        menu.addItem(item);

        */


        var lv = new MatrixLvlEditorModel(40,40,"");

        //Conversion de l'objet en json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lv);
        System.out.println(json+"\n LevelEditorModel Class-> toJson");

        //Récupération du json-> objet
        MatrixLvlEditorModel regetObj = mapper.readValue(json, MatrixLvlEditorModel.class);
        System.out.println(regetObj+"\n LevelEditorModel json->class");

    }


}


class Obj {
    int x;
    int y;
    List<Integer> a;
    public Obj() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Integer> getA() {
        return a;
    }

    public void setA(List<Integer> a) {
        this.a = a;
    }
}
