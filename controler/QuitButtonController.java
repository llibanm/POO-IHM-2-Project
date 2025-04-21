package controler;

import interfaces.Commands;
import model.QuitButtonModel;
import view.MyButtonView;
import view.QuitButtonView;
import javafx.application.Platform;
import javafx.event.ActionEvent;

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
