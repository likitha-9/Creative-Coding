package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author likitha-9
 *
 *         FXML Controller Class
 *
 */
public class CipherController implements Initializable {

	public ChoiceBox<String> choiceBox;
	ObservableList<String> choices = FXCollections.observableArrayList("Atbash", "ROT13", "Caesar", "Affine",
			"Rail-Fence", "Baconian", "Polybius Square", "Simple Substitution", "Codes and Nomenclators",
			"Columnar Transposition", "Autokey", "Beaufort", "Porta", "Running Key", "Vigenère and Gronsfeld",
			"Homophonic Substitution", "Four-Square", "Hill", "Playfair", "ADFGVX", "ADFGX", "Bifid",
			"Straddle Checkerboard", "Trifid", "Base64", "Fractionated Morse");

	public TextArea textArea;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBox.setValue("Atbash");
		choiceBox.setItems(choices);
	}

}
