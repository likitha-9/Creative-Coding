package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author likitha-9
 *
 *         FXML Controller Class
 *
 */
public class CipherController implements Initializable {

	ObservableList<String> choices = FXCollections.observableArrayList("Atbash", "ROT13", "Caesar", "Affine",
			"Rail-Fence", "Baconian", "Polybius Square", "Simple Substitution", "Codes and Nomenclators",
			"Columnar Transposition", "Autokey", "Beaufort", "Porta", "Running Key", "Vigenère and Gronsfeld",
			"Homophonic Substitution", "Four-Square", "Hill", "Playfair", "ADFGVX", "ADFGX", "Bifid",
			"Straddle Checkerboard", "Trifid", "Base64", "Fractionated Morse");
	public ComboBox<String> combo;
	public TextArea area;

	public TextField a; // a and b were used for Affine Cipher
	public TextField b;
	public Button submit;

	public Label message;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combo.setItems(choices);
		combo.setOnAction(this::getCipherMethod);

		a.setDisable(true);
		b.setDisable(true);
	}

	@FXML
	public void getCipherMethod(ActionEvent event) {

		// Enable/disable TextFields based on ComboBox selections.
		if (combo.getValue() == "Atbash")
			writeMessage("A monoalphabetic substitution that takes the alphabet and maps to its reverse. "
					+ "Ex: A->Z, B->Y, C->X, and so on.\n");

		if (combo.getValue() == "ROT13")
			writeMessage("A substitution cipher where letters are offset by 13. Ex: A->N, B->O, etc.");

		if (combo.getValue() == "Caesar")
			writeMessage("A substitution cipher where letters are shifted a number of places. "
					+ "Ex: if offset=1, A->B, B->C, etc.");

		if (combo.getValue() == "Affine") {
			a.setDisable(false);
			b.setDisable(false);
			writeMessage("The algorithm for Affine Cipher is: c=(ap+b)(mod 26). "
					+ "p is the number representing a letter. Values of a and b MUST be relatively prime to 26.");
		} else {
			a.setDisable(true);
			b.setDisable(true);
		}

		// Check for actions on Submit.
		submit.setOnMouseClicked(e -> {
			if (combo.getValue() == "Atbash")
				AllCipherMethods.atbash(area.getText());

			if (combo.getValue() == "ROT13")
				AllCipherMethods.rot13(area.getText());

			if (combo.getValue() == "Caesar")
				AllCipherMethods.caesar(area.getText());

			if (combo.getValue() == "Affine") {
				try {
					// values of a, b must be apt (they should be integers and in range from 1-26)
					int value_a = Integer.parseInt(a.textProperty().get()),
							value_b = Integer.parseInt(b.textProperty().get());
					if (value_a < 1 || value_a > 26 || value_b < 1 || value_b > 26)
						throw new Exception();

					// checking whether a and b are relatively prime to 26
					AllCipherMethods.affine(area.getText(), Integer.parseInt(a.textProperty().get()),
							Integer.parseInt(b.textProperty().get()));

				} catch (Exception E) {
					writeMessage("a and b must be valid integers!\nThis condition MUST be true: 1 <= a,b <= 26.");
				}

			}
		});

	}

	public void writeMessage(String str) {
		message.setText(str);
	}

}
