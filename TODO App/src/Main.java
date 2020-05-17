import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	static BorderPane border = new BorderPane();
	Button addEvent = new Button();

	@Override
	public void start(Stage mainStage) throws Exception {
		Scene scene = new Scene(border, 300, 500);
		
		mainStage.setTitle("To-Do List");
		mainStage.setScene(scene);
		mainStage.show();		
	}

	public static void main(String[] args) {
		Application.launch();

	}

}
