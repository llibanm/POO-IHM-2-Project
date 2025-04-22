package src.java.org.projet.controler;


import javafx.stage.Stage;
import src.java.org.projet.model.AbstractModel;
import src.java.org.projet.view.AbstractView;

public class SceneControler extends AbstractControler {

	private Stage stage;
	
	public SceneControler(Stage stage, AbstractModel model, AbstractView view){
       // super(model,view);
        this.stage = stage;
	}
	
	public void swithToParam() throws Exception{
	
	/*InitPlateau InitPage = new InitPlateau(this);

        Scene scene = new Scene(InitPage, size1, size2);

        stage.setTitle("Page d'accueil");
        stage.setScene(scene);
        stage.show();*/
	}
	
	public void swithToSelectChar() throws Exception{
	
	}
	
}
	
