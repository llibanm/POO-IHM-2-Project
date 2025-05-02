package src.java.org.projet.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Classe concrète pour écouter des messages
 */
public abstract  class MyConcreteObservable implements  MyObservable {

    @JsonIgnore
    private  PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void init() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    public MyConcreteObservable() {}
    @Override
       public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener){
            this.propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
        }

    @Override
        public  void removePropertyChangeListener (PropertyChangeListener propertyChangeListener){
            this.propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
        }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
}