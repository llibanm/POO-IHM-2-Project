package Controler;

import Interfaces.Commands;
import Model.quitButtonModel;
import View.quitButtonView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;

public class quitButtonController extends abstractControler implements Commands {


    public quitButtonController() {
    }


    public quitButtonModel getQuitModel() {
        return (quitButtonModel) this.getModel();
    }

    public quitButtonView getQuitView() {
        return (quitButtonView) this.getView();
    }

    public void setQuitAction(quitButtonView v) {

        v.getQuitButton().setOnAction((ActionEvent e) -> {Platform.exit(); execute();});

    }

    @Override
    public void execute() {
        getQuitModel().execute();
        Platform.exit();
    }

}
