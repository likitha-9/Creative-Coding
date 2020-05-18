import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorMessages {

	// display error messages if user violates any rules
	public void displayErrorMessages(Text text) {
		// open a new stage
		Stage errorStage = new Stage();
		errorStage.setTitle("ERROR!");

		// add the error text
		VBox errorDialogBox = new VBox(20);
		errorDialogBox.setPadding(new Insets(20));
		errorDialogBox.getChildren().addAll(text);

		// add the text to scene and show the scene
		Scene errorScene = new Scene(errorDialogBox, 350, 70);
		errorStage.setScene(errorScene);
		errorStage.show();

	}
}
