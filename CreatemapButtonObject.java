
import controler.createmapButtonController;
import model.CreatemapButtonModel;
import view.CreatemapButtonView;

public class CreatemapButtonObject extends MyObject {
    public CreatemapButtonObject() {
        super(new CreatemapButtonModel(), new createmapButtonController(), new CreatemapButtonView());
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
    public createmapButtonController getControler() {
        return (createmapButtonController) super.getControler();
    }

    @Override
    public CreatemapButtonView getView() {
        return (CreatemapButtonView) super.getView();
    }
}
