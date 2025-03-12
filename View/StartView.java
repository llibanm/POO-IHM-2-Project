package View;

import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class StartView extends BorderPane {

    private Image backgroundImage;
    private SceneControler sceneControler;

    public StartView(SceneControler sceneControler){
    
    	this.sceneControler = sceneControler;

        backgroundImage = new Image("spaceImagesProject/Space_Background.png");

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1200,1000,true,true,true,true)
        );

        this.setBackground(new Background(background));
        
        createTitle("Colossal Space Adventure"); //A creer dans background
        createButtonSingleP();
        createButtonMultiP();
        createButtonBest();
        createButtonParam();
        createButtonQuit(700, 600); //A creer dans background createButtonQuit(int x, int y)

    }
    
    public void createButtonSingleP() {
    
    	Button SinglePButton = new Button("Single Player");
	SinglePButton.setLayoutX(200);
	SinglePButton.setLayoutY(100);
		
	SinglePButton.setOnMouseClicked((MouseEvent e) -> {
	    try {
        		sceneControler.swithToSelectChar(); // Appel sécurisé avec try-catch
    		    } catch (Exception ex) {
        		ex.printStackTrace(); // Affiche l'erreur dans la console
    		      }
    	super.getChildren().add(SinglePButton);
    	}
    }
    
    
    public void createButtonMultiP() {
    	Button MultiPButton = new Button("Multi Player");
	MultiPButton.setLayoutX(400);
	MultiPButton.setLayoutY(100);
		
    	super.getChildren().add(MultiPButton);
    }
    
    public void createButtonBest() {
    	Button BestButton = new Button("Hall Of Fame");
	BestButton.setLayoutX(100);
	BestButton.setLayoutY(600);
		
    	super.getChildren().add(BestButton);
    }
    
    public void createButtonParam() {
    	Button ParamButton = new Button("Paramètres");
	ParamButton.setLayoutX(200);
	ParamButton.setLayoutY(100);
		
	ParamButton.setOnMouseClicked((MouseEvent e) -> {
	    try {
        		sceneControler.swithToParam(); // Appel sécurisé avec try-catch
    		    } catch (Exception ex) {
        		ex.printStackTrace(); // Affiche l'erreur dans la console
    		      }
    	super.getChildren().add(ParamButton);
    	}
    }

}
