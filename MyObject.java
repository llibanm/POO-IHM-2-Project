

import controler.abstractControler;
import model.AbstractModel;
import view.AbstractView;

public abstract class MyObject {

    private AbstractModel model;
    private abstractControler controler;
    private AbstractView view;


    public MyObject(AbstractModel m, abstractControler c, AbstractView v){
        model = m;
        controler = c;
        view = v;
    }


    public AbstractModel getModel() {
        return model;
    }



    public abstractControler getControler() {
        return controler;
    }



    public AbstractView getView() {
        return view;
    }


}
