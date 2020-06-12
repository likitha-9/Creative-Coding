package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage mainStage) {
		//Create HBox
		HBox hbox=new HBox(20);
		//add children to that HBox
		Label enterLabel = new Label("Enter the text to encode:\t");
		TextField enterText=new TextField("Enter text.");
		hbox.getChildren().addAll(enterLabel,enterText);

		//Create scene and add it to stage.
		Scene scene = new Scene(hbox, 40, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
