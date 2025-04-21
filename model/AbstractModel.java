package model;

public abstract class AbstractModel {

    private String objectDescription;

    public AbstractModel(String objectD){
        objectDescription = objectD;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String obj){
        objectDescription = obj;
    }

}
