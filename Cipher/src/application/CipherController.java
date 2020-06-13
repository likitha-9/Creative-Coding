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
	public TextField a;
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

		/** Enable/disable TextFields based on ComboBox selections. */

		// Atbash Cipher
		if (combo.getValue() == "Atbash")
			writeMessage("A monoalphabetic substitution that takes the alphabet and maps to its reverse. "
					+ "Ex: A->Z, B->Y, C->X, and so on.\n");

		// ROT13 Cipher
		if (combo.getValue() == "ROT13")
			writeMessage("A substitution cipher where letters are offset by 13. Ex: A->N, B->O, etc.");

		// Caesar Cipher
		if (combo.getValue() == "Caesar")
			writeMessage("A substitution cipher where letters are shifted a number of places. "
					+ "Ex: if offset=1, A->B, B->C, etc.");

		// Affine Cipher
		if (combo.getValue() == "Affine") {
			a.setDisable(false);
			b.setDisable(false);

			promptText("Value of a", "Value of b");
			writeMessage("The algorithm for Affine Cipher is: c=(ap+b)(mod 26). "
					+ "p is the number representing a letter. Values of a and b MUST be relatively prime to 26.");
		}

		// Rail-Fence Cipher
		if (combo.getValue() == "Rail-Fence") {
			a.setDisable(false);
			b.setDisable(true);

			promptText("Enter the value of key", "");
			writeMessage("Key = # of rails. " + "E.g. key=3. Plaintext=\"defense\"\n" + "d . . . n . .\n"
					+ ". e . e . s .\n" + ". . f . . . e\n");
		}
	}

	public void submitActions(ActionEvent event) {
		/** Check for actions on Submit. */
		submit.setOnMouseClicked(e -> {
			// Atbash Cipher
			if (combo.getValue() == "Atbash")
				AllCipherMethods.atbash(area.getText());

			// ROT13 Cipher
			if (combo.getValue() == "ROT13")
				AllCipherMethods.rot13(area.getText());

			// Caesar Cipher
			if (combo.getValue() == "Caesar")
				AllCipherMethods.caesar(area.getText());

			// Affine Cipher
			if (combo.getValue() == "Affine") {
				try {
					// values of a, b must be apt (they should be integers and in range from 1-26)
					int value_a = Integer.parseInt(a.textProperty().get()),
							value_b = Integer.parseInt(b.textProperty().get());
					if (value_a < 0 || value_a > 25 || value_b < 0 || value_b > 25)
						throw new Exception();

					// check if factors of a are divisible by 26.
					for (int i = 2; i <= value_a; i++) {
						if (value_a % i == 0)
							if (25 % i == 0)
								throw new Exception();
					}

					// if all is well, invoke the function
					AllCipherMethods.affine(area.getText(), Integer.parseInt(a.textProperty().get()),
							Integer.parseInt(b.textProperty().get()));

				} catch (Exception E) {
					writeMessage("a and b must be valid integers!\nThese conditions MUST be true: "
							+ "(1) a MUST be relatively prime to 26, and "
							+ "(2) a, b should be in the range of 0 <= a,b <= 25.");
				}

			}

			// Rail-Fence Cipher
			if (combo.getValue() == "Rail-Fence") {
				try {
					System.out.println();
					AllCipherMethods.railFence(area.getText(), Integer.parseInt(a.textProperty().get()));
					//AllCipherMethods.railFence(area.getText(), Integer.parseInt(a.textProperty().get()));

				} catch (Exception E) {
					System.out.println(E);
					writeMessage("Key has to be a valid integer!");
				}
			}

		});

	}

	public void writeMessage(String str) {
		message.setText(str); // edit the Label to show appropriate messages
	}

	public void promptText(String str_a, String str_b) {
		a.setPromptText(str_a);
		b.setPromptText(str_b);
	}

}
