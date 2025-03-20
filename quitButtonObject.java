import Controler.abstractControler;
import Controler.quitButtonController;
import Model.abstractModel;
import Model.quitButtonModel;
import View.abstractView;
import View.quitButtonView;
import javafx.application.Platform;

public class quitButtonObject extends Object{

    private  quitButtonModel model ;
    private  quitButtonView view;
    private  quitButtonController controler;

    public quitButtonObject() {
        super(new quitButtonModel(),new quitButtonController(), new quitButtonView());

        model = (quitButtonModel) super.getModel();
        view = (quitButtonView) super.getView();
        controler = (quitButtonController) super.getControler();

        controler.setModel(model);
        controler.setView(view);
        view.setControler(controler);
        //controler.execute();
        controler.setQuitAction(getView());

    }

    @Override
    public quitButtonView getView() {
        return view;
    }

    @Override
    public quitButtonModel getModel() {
        return model;
    }

    @Override
    public quitButtonController getControler() {
        return controler;
    }
}
