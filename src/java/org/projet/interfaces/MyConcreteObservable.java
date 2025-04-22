package src.java.org.projet.interfaces;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract  class MyConcreteObservable implements  MyObservable {


    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
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
