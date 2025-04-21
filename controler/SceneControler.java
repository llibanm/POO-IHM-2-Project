package controler;

import model.AbstractModel;
import view.AbstractView;
import javafx.stage.Stage;

public class SceneControler extends abstractControler {

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
	
