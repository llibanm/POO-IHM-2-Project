package src.java.org.projet;


import src.java.org.projet.controler.CreatemapButtonController;
import src.java.org.projet.model.CreatemapButtonModel;
import src.java.org.projet.view.CreatemapButtonView;

public class CreatemapButtonObject extends MyObject {
    public CreatemapButtonObject() {
        super(new CreatemapButtonModel(), new CreatemapButtonController(), new CreatemapButtonView());
        getControler().setModel(getModel());
        getControler().setView(getView());
        getView().setControler(getControler());
        getControler().setAction(getView());
    }


    @Override
    public CreatemapButtonModel getModel() {
        return (CreatemapButtonModel) super.getModel();
    }

    @Override
    public CreatemapButtonController getControler() {
        return (CreatemapButtonController) super.getControler();
    }

    @Override
    public CreatemapButtonView getView() {
        return (CreatemapButtonView) super.getView();
    }
}
