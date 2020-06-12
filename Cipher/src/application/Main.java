package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage mainStage) {
		// Create VBox (MAIN)
		VBox vbox=new VBox(20);

		// Enable writing text
		HBox hbox_writing = new HBox(20);
		Label enterLabel = new Label("Enter the text to encode:\t");
		TextField enterText = new TextField("Enter text.");
		hbox_writing.getChildren().addAll(enterLabel, enterText);

		// Create a choice box for the ciphers.
		HBox hbox_choices=new HBox(20);
		Label choiceLabel = new Label("Select a cipher:\t");
		ChoiceBox choices = new ChoiceBox<String>(FXCollections.observableArrayList(
				"Atbash",
				"ROT13",
				"Caesar",
				"Affine",
				"Rail-Fence",
				"Baconian",
				"Polybius Square",
				"Simple Substitution",
				"Codes and Nomenclators",
				"Columnar Transposition",
				"Autokey",
				"Beaufort",
				"Porta",
				"Running Key",
				"Vigenère and Gronsfeld",
				"Homophonic Substitution",
				"Four-Square",
				"Hill",
				"Playfair",
				"ADFGVX",
				"ADFGX",
				"Bifid",
				"Straddle Checkerboard",
				"Trifid",
				"Base64",
				"Fractionated Morse"
				));
		hbox_choices.getChildren().addAll(choiceLabel,choices);

		// Add all children to the main VBox.
		vbox.getChildren().addAll(hbox_writing, hbox_choices);

		//

		// Create scene, add it to stage, and show the stage.
		Scene scene = new Scene(vbox, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
