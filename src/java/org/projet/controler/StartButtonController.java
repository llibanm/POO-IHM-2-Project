package src.java.org.projet.controler;


import src.java.org.projet.interfaces.Commands;
import src.java.org.projet.model.StartButtonModel;
import src.java.org.projet.view.MyButtonView;
import src.java.org.projet.view.StartButtonView;

public class StartButtonController extends MyButtonController implements Commands {
    public StartButtonController() {
    }

    public StartButtonModel getStartButtonModel() {
        return (StartButtonModel) this.getModel();
    }

    public StartButtonView getStartButtonView() {
        return (StartButtonView) this.getView();
    }

    @Override
    public void setAction(MyButtonView v) {
        super.setAction(v);
    }


    @Override
    public void execute() {
        getStartButtonModel().execute();
    }
}
