package src.java.org.projet;


import src.java.org.projet.controler.QuitButtonController;
import src.java.org.projet.model.QuitButtonModel;
import src.java.org.projet.view.QuitButtonView;


public class QuitButtonObject extends MyObject{



    public QuitButtonObject() {
        super(new QuitButtonModel(),new QuitButtonController(), new QuitButtonView());

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
    public QuitButtonView getView() {
        return (QuitButtonView) super.getView();
    }

    @Override
    public QuitButtonModel getModel() {
        return (QuitButtonModel) super.getModel();
    }

    @Override
    public QuitButtonController getControler() {
        return (QuitButtonController) super.getControler();
    }
}
