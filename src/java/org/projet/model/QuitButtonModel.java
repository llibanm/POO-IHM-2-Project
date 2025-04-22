package src.java.org.projet.model;


import src.java.org.projet.interfaces.Commands;

public class QuitButtonModel extends AbstractModel implements Commands {
    public QuitButtonModel() {
        super("quit button");
    }

    @Override
    public void execute() {
        Sauvegarde();
    }

    public void Sauvegarde(){

    }
}
