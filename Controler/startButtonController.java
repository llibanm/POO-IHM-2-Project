package Controler;

import Interfaces.Commands;
import Model.startButtonModel;
import View.myButtonView;
import View.startButtonView;


public class startButtonController extends myButtonController implements Commands {
    public startButtonController() {
    }

    public startButtonModel getStartButtonModel() {
        return (startButtonModel) this.getModel();
    }

    public startButtonView getStartButtonView() {
        return (startButtonView) this.getView();
    }

    @Override
    public void setAction(myButtonView v) {
        super.setAction(v);
    }


    @Override
    public void execute() {

    }
}
