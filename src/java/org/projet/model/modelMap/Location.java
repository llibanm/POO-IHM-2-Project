package src.java.org.projet.model.modelMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private final String name;
    private final String description; //What you can see in this place
    private final int indexOnWorldMap; //The index of this location on the world map
    @JsonCreator
    public Location(@JsonProperty("name")String name, @JsonProperty("description")String description,@JsonProperty("indexOnWorldMap") int indexOnWorldMap) {

        this.name = name;
        this.description = description;
        this.indexOnWorldMap = indexOnWorldMap;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIndexOnWorldMap() {
        return indexOnWorldMap;
    }
}