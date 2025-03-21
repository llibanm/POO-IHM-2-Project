package object;

import Controler.abstractControler;
import Model.abstractModel;
import View.abstractView;

public abstract class Object {

    private abstractModel model;
    private abstractControler controler;
    private abstractView view;


    public Object(abstractModel m, abstractControler c, abstractView v){
        model = m;
        controler = c;
        view = v;
    }


    public abstractModel getModel() {
        return model;
    }



    public abstractControler getControler() {
        return controler;
    }



    public abstractView getView() {
        return view;
    }


}
