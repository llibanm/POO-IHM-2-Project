package src.java.org.projet.model.modelLevelEditor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ConvertToJson<T> {
    private final Class<T> classType;
    public ConvertToJson(){
        this.classType = null;
    }
    public ConvertToJson(Class<T> classType) {
        this.classType = classType;
    }

    public void convertTojson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        System.out.println(json + "\n  Class-> toJson");

        // Récupération du json-> objet
        T regetObj = mapper.readValue(json, classType);
        System.out.println(regetObj + "\n json->toclass");
    }

    public Class<T> getClassType() {
        return classType;
    }
}