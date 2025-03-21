package object;

import Controler.startButtonController;
import Model.startButtonModel;
import View.startButtonView;

public class startButtonObject extends Object {
    public startButtonObject() {
        super( new startButtonModel(), new startButtonController(), new startButtonView());

        getControler().setModel(getModel());
        getControler().setView(getView());
        getView().setControler(getControler());
        getControler().setAction(getView());
    }


    @Override
    public startButtonView getView() {
        return (startButtonView) super.getView();
    }

    @Override
    public startButtonModel getModel() {
        return (startButtonModel) super.getModel();
    }

    @Override
    public startButtonController getControler() {
        return (startButtonController) super.getControler();
    }

}
