package controler;

import interfaces.Commands;
import model.CreatemapButtonModel;
import view.CreatemapButtonView;
import view.MyButtonView;
import javafx.event.ActionEvent;

public class CreatemapButtonController extends MyButtonController implements Commands {

    public CreatemapButtonController() {}


    public CreatemapButtonModel getMapButtonModel() {
        return (CreatemapButtonModel) this.getModel();
    }

    public CreatemapButtonView getMapButtonView(){
        return (CreatemapButtonView) this.getView();
    }

    @Override
    public void setAction(MyButtonView v) {
        super.setAction(v);
        v.getMyButton().setOnAction((ActionEvent e) -> { execute();});
    }

    @Override
    public void execute() {

    }
}
