package src.java.org.projet.interfaces;

import java.beans.PropertyChangeListener;

/**
 * Interface Observable
 */
public interface MyObservable {

    void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);

    void removePropertyChangeListener (PropertyChangeListener propertyChangeListener);
}