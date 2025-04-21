package view;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class MyButton extends Button {



    public MyButton(String s, Boolean animated) {
        super(s);



        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/css/PressStart2P-Regular.ttf"), 18);


        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.GRAY);


        if (animated) {
            ScaleTransition pulse = new ScaleTransition(Duration.millis(1000), this);
            pulse.setToX(1.1);
            pulse.setToY(1.1);
            pulse.setAutoReverse(true);
            pulse.setCycleCount(Timeline.INDEFINITE);
            pulse.play();
        }

        this.setFont(pixelFont);

        this.setMaxWidth(300);
        this.setMaxHeight(1500);

    }




}
