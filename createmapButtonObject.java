package object;

import Controler.abstractControler;
import Controler.createmapButtonController;
import Model.abstractModel;
import Model.createmapButtonModel;
import View.abstractView;
import View.createmapButtonView;

public class createmapButtonObject extends Object {
    public createmapButtonObject() {
        super(new createmapButtonModel(), new createmapButtonController(), new createmapButtonView());
        getControler().setModel(getModel());
        getControler().setView(getView());
        getView().setControler(getControler());
        getControler().setAction(getView());
    }


    @Override
    public createmapButtonModel getModel() {
        return (createmapButtonModel) super.getModel();
    }

    @Override
    public createmapButtonController getControler() {
        return (createmapButtonController) super.getControler();
    }

    @Override
    public createmapButtonView getView() {
        return (createmapButtonView) super.getView();
    }
}
