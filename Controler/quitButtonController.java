package Controler;

import Interfaces.Commands;
import Model.quitButtonModel;
import View.myButtonView;
import View.quitButtonView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;

import javax.swing.*;

public class quitButtonController extends myButtonController implements Commands {


    public quitButtonController() {
    }


    public quitButtonModel getQuitModel() {
        return (quitButtonModel) this.getModel();
    }

    public quitButtonView getQuitView() {
        return (quitButtonView) this.getView();
    }


    @Override
    public void setAction(myButtonView v) {
        super.setAction(v);
        v.getMyButton().setOnAction((ActionEvent e) -> { execute();});
    }

    @Override
    public void execute() {
        getQuitModel().execute();
        Platform.exit();
    }

}
