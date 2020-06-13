package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author likitha-9
 *
 *         Main class
 *
 */

public class Main extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage mainStage) throws IOException {

		//Load the FXML file.
		Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));

		// Create scene, add it to stage, and show the stage.
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
