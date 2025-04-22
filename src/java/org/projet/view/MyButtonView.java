package src.java.org.projet.view;

public class MyButtonView extends AbstractView {
    
    private MyButton myButton;
    private boolean animation;
    public MyButtonView(String t, AbstractBackground b, boolean animated) {
        super(t, b);
        myButton = new MyButton(this.getTitle(),animated);
        animation = animated;
    }

    public MyButton getMyButton() {
        return myButton;
    }

    public boolean getAnimation() {
        return animation;
    }
}
