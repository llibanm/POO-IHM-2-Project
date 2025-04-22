package src.java.org.projet;


import src.java.org.projet.controler.StartButtonController;
import src.java.org.projet.model.StartButtonModel;
import src.java.org.projet.view.StartButtonView;


public class StartButtonObject extends MyObject {
    public StartButtonObject() {
        super( new StartButtonModel(), new StartButtonController(), new StartButtonView());

        getControler().setModel(getModel());
        getControler().setView(getView());
        getView().setControler(getControler());
        getControler().setAction(getView());
    }


    @Override
    public StartButtonView getView() {
        return (StartButtonView) super.getView();
    }

    @Override
    public StartButtonModel getModel() {
        return (StartButtonModel) super.getModel();
    }

    @Override
    public StartButtonController getControler() {
        return (StartButtonController) super.getControler();
    }

}
