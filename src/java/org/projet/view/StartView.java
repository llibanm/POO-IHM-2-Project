package src.java.org.projet.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.java.org.projet.controler.SceneControler;
import src.java.org.projet.view.AbstractView;


public class StartView extends AbstractView {

    //private Image backgroundImage;
    private SceneControler sceneControler;

    public StartView(Stage s, StartingBackground background) {
        super("Colossal Space Adventure",background);



      	//  backgroundImage = new Image("spaceImagesProject/Space_Background.png");

       /* BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1200,1000,true,true,true,true)
        );
			*/
        this.setBackground(background.getBackground());

        //createTitle("Colossal Space Adventure"); // Sarah :A creer dans background
													//Liban :Fait mais j'ai mis dans le constructeur abstractView
        createButtonSingleP();
        createButtonMultiP();
        createButtonBest();
        createButtonParam();
        //createButtonQuit(700, 600); //Sarah: A creer dans background createButtonQuit(int x, int y)
									  //Liban: Fait mais je l'ai initialisé dans le contructeur abstractView et je l'ai mit dans ue liste de Button


    }
    
    public void createButtonSingleP() {
    
    	Button SinglePButton = new Button("Single Player");
	SinglePButton.setLayoutX(200);
	SinglePButton.setLayoutY(100);
		
	SinglePButton.setOnMouseClicked(( e) -> { //Liban: TODO mettre ça dans un controler
	    try {
        		sceneControler.swithToSelectChar(); // Appel sécurisé avec try-catch
    		    } catch (Exception ex) {
        		ex.printStackTrace(); // Affiche l'erreur dans la console
    		      }
    	super.getChildren().add(SinglePButton);//Liban: TODO modifier cette ligne car on utilise un Borderpane
    	});
    }
    
    
    public void createButtonMultiP() {
    	Button MultiPButton = new Button("Multi Player");
	MultiPButton.setLayoutX(400);
	MultiPButton.setLayoutY(100);
		
    	super.getChildren().add(MultiPButton);//Liban: TODO modifier cette ligne car on utilise un Borderpane
    }
    
    public void createButtonBest() {
    	Button BestButton = new Button("Hall Of Fame");
	BestButton.setLayoutX(100);
	BestButton.setLayoutY(600);
		
    	super.getChildren().add(BestButton);//Liban: TODO modifier cette ligne car on utilise un Borderpane
    }
    
    public void createButtonParam() {
    	Button ParamButton = new Button("Paramètres");
	ParamButton.setLayoutX(200);
	ParamButton.setLayoutY(100);
		
	ParamButton.setOnMouseClicked(( e) -> { //Liban: TODO mettre ça dans un controler
	    try {
        		sceneControler.swithToParam(); // Appel sécurisé avec try-catch
    		    } catch (Exception ex) {
        		ex.printStackTrace(); // Affiche l'erreur dans la console
    		      }
    	super.getChildren().add(ParamButton);//Liban: TODO modifier cette ligne car on utilise un Borderpane
    	});
    }

}
