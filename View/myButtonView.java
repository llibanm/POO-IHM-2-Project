package View;

public class myButtonView extends abstractView{
    
    private myButton myButton;
    private boolean animation;
    public myButtonView(String t,abstractBackground b,boolean animated) {
        super(t, b);
        myButton = new myButton(this.getTitle(),animated);
        animation = animated;
    }

    public myButton getMyButton() {
        return myButton;
    }

    public boolean getAnimation() {
        return animation;
    }
}
