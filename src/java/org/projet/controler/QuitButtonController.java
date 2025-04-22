package src.java.org.projet.controler;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import src.java.org.projet.interfaces.Commands;
import src.java.org.projet.model.QuitButtonModel;
import src.java.org.projet.view.MyButtonView;
import src.java.org.projet.view.QuitButtonView;

public class QuitButtonController extends MyButtonController implements Commands {


    public QuitButtonController() {
    }


    public QuitButtonModel getQuitModel() {
        return (QuitButtonModel) this.getModel();
    }

    public QuitButtonView getQuitView() {
        return (QuitButtonView) this.getView();
    }


    @Override
    public void setAction(MyButtonView v) {
        super.setAction(v);
        v.getMyButton().setOnAction((ActionEvent e) -> { execute();});
    }

    @Override
    public void execute() {
        getQuitModel().execute();
        Platform.exit();
    }

}
