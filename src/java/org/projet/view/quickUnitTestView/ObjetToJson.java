package src.java.org.projet.view.quickUnitTestView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ObjetToJson {

    public static void main(String[] args) throws JsonProcessingException {
        ObjetToJson.objetToJsonTest();
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
