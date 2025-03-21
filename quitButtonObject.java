package object;

import Controler.quitButtonController;
import Model.quitButtonModel;
import View.quitButtonView;

public class quitButtonObject extends Object{



    public quitButtonObject() {
        super(new quitButtonModel(),new quitButtonController(), new quitButtonView());

        /*model = (quitButtonModel) super.getModel();
        view = (quitButtonView) super.getView();
        controler = (quitButtonController) super.getControler();

        controler.setModel(model);
        controler.setView(view);
        view.setControler(controler);
        //controler.execute();
        controler.setQuitAction(getView());*/

        getControler().setModel(getModel());
        getControler().setView(getView());
        getView().setControler(getControler());
        getControler().setAction(getView());

    }

    @Override
    public quitButtonView getView() {
        return (quitButtonView) super.getView();
    }

    @Override
    public quitButtonModel getModel() {
        return (quitButtonModel) super.getModel();
    }

    @Override
    public quitButtonController getControler() {
        return (quitButtonController) super.getControler();
    }
}
