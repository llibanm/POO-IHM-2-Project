package model;


import interfaces.Commands;

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
