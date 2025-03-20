package Controler;


import Model.abstractModel;
import View.abstractView;

public abstract class abstractControler {

    private abstractModel model;
    private abstractView view;

    public abstractControler(){
    }

    public abstractModel getModel() {
        return model;
    }

    public  abstractView getView() {
        return view;
    }

    public void setModel(abstractModel model) {
        this.model = model;
    }

    public void setView(abstractView view) {
        this.view = view;
    }
}
