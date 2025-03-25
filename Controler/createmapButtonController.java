package Controler;

import Interfaces.Commands;
import Model.createmapButtonModel;
import View.abstractView;
import View.createmapButtonView;
import View.myButtonView;
import javafx.event.ActionEvent;

public class createmapButtonController extends myButtonController implements Commands {

    public createmapButtonController() {}


    public createmapButtonModel getMapButtonModel() {
        return (createmapButtonModel) this.getModel();
    }

    public createmapButtonView getMapButtonView(){
        return (createmapButtonView) this.getView();
    }

    @Override
    public void setAction(myButtonView v) {
        super.setAction(v);
        v.getMyButton().setOnAction((ActionEvent e) -> { execute();});
    }

    @Override
    public void execute() {

    }
}
