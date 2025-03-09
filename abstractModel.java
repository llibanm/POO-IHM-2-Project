package Model;

public abstract class abstractModel {

    private String objectDescription;

    public abstractModel(String objectD){
        objectDescription = objectD;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String obj){
        objectDescription = obj;
    }

}
