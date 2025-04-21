package controler;

import interfaces.Commands;
import model.StartButtonModel;
import view.MyButtonView;
import view.StartButtonView;


public class StartButtonController extends myButtonController implements Commands {
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
