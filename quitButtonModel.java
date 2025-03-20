package Model;


import Interfaces.Commands;

public class quitButtonModel  extends abstractModel implements Commands {
    public quitButtonModel() {
        super("quit button");
    }

    @Override
    public void execute() {
        Sauvegarde();
    }

    public void Sauvegarde(){

    }
}
