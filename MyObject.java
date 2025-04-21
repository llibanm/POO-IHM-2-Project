

import controler.AbstractControler;
import model.AbstractModel;
import view.AbstractView;

public abstract class MyObject {

    private AbstractModel model;
    private AbstractControler controler;
    private AbstractView view;


    public MyObject(AbstractModel m, AbstractControler c, AbstractView v){
        model = m;
        controler = c;
        view = v;
    }


    public AbstractModel getModel() {
        return model;
    }



    public AbstractControler getControler() {
        return controler;
    }



    public AbstractView getView() {
        return view;
    }


}
