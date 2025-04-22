package src.java.org.projet.model;

import src.java.org.projet.interfaces.MyConcreteObservable;

public abstract class AbstractModel extends MyConcreteObservable  {

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
