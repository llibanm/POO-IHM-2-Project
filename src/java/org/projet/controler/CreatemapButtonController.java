package src.java.org.projet.controler;


import javafx.event.ActionEvent;
import src.java.org.projet.interfaces.Commands;
import src.java.org.projet.model.CreatemapButtonModel;
import src.java.org.projet.view.CreatemapButtonView;
import src.java.org.projet.view.MyButtonView;

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
