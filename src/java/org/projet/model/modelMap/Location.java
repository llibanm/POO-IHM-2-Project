package src.java.org.projet.model.modelMap;

public class Location {
    private final String name;
    private final String description; //What you can see in this place
    private final int indexOnWorldMap; //The index of this location on the world map

    public Location(String name, String description, int indexOnWorldMap) {

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