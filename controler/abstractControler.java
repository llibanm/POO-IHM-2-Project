package controler;


import model.AbstractModel;
import view.AbstractView;

public abstract class abstractControler {

    private AbstractModel model;
    private AbstractView view;

    public abstractControler(){
    }

    public AbstractModel getModel() {
        return model;
    }

    public AbstractView getView() {
        return view;
    }

    public void setModel(AbstractModel model) {
        this.model = model;
    }

    public void setView(AbstractView view) {
        this.view = view;
    }
}
