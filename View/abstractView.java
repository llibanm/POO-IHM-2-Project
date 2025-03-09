package View;

public abstract class abstractView {

    private String objectDescription;


    public abstractView(String obj){
        objectDescription = obj;
    }


    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String obj){
        objectDescription = obj;
    }
}
