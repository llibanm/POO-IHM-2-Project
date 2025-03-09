package Controler;


import Model.abstractModel;
import View.abstractView;

public abstract class abstractControler {

    private abstractModel model;
    private abstractView view;

    public abstractControler(abstractModel m, abstractView v){
        model = m;
        view = v;
    }

}
