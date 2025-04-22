package src.java.org.projet.controler;


import src.java.org.projet.interfaces.MyConcreteObservable;
import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.view.AbstractView;

import java.beans.PropertyChangeListener;

public abstract class AbstractControler  {

    private AbstractModel model;
    private AbstractView view;
    protected PropertyChangeListener propertyChangeListener;

    public PropertyChangeListener getPropertyChangeListener() {
        return propertyChangeListener;
    }

    public void setPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.propertyChangeListener = propertyChangeListener;
    }

    public AbstractControler(){
    }

    public AbstractModel getModel() {
        return model;
    }

    public AbstractView getView() {
        return view;
    }

    public void setModel(AbstractModel model) {
        this.model = model;
    }

    public void setView(AbstractView view) {
        this.view = view;
    }
}
