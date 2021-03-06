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
			"Rail-Fence", "Baconian", "Polybius Square", "Simple Substitution", "Columnar Transposition", "Autokey",
			"Beaufort", "Porta", "Running Key", "Vigen�re and Gronsfeld", "Homophonic Substitution", "Four-Square",
			"Hill", "Playfair", "ADFGVX", "ADFGX", "Bifid", "Straddle Checkerboard", "Trifid", "Base64",
			"Fractionated Morse");
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
					+ "p is the number representing a letter. Value of a MUST be relatively prime to 26. "
					+ "Value of b can be up to 26.");
		}

		// Rail-Fence Cipher
		if (combo.getValue() == "Rail-Fence") {
			a.setDisable(false);
			b.setDisable(true);

			promptText("Enter the value of key", "");
			writeMessage("Key = # of rails. " + "E.g. key=3. Plaintext=\"defense\"\n" + "d . . . n . .\n"
					+ ". e . e . s .\n" + ". . f . . . e\n");
		}

		// Baconian Cipher
		if (combo.getValue() == "Baconian") {
			a.setDisable(false);
			b.setDisable(false);

			promptText("Enter first character", "Enter second character");
			writeMessage("Each letter is assigned a string of 5 binary digits. "
					+ "For e.g., letter A->'aaaaa', B->'aaaab', and so on. "
					+ "EXCEPT for I/J and U/V; they have the same one.");
		}

		// Polybius Square Cipher
		if (combo.getValue() == "Polybius Square") {
			a.setDisable(false);
			b.setDisable(false);

			promptText("Enter key", "Enter ciphertext characters");
			writeMessage("Usually a 25 letter 'key square' & 5 cipher characters.\n"
					+ "e.g. Key square: 'zebracdfghiklmnopqstuvwxy',\n" + "Cipher chars: 'abcde'");
		}

		// Simple Substition Cipher
		if (combo.getValue() == "Simple Substitution") {
			a.setDisable(false);
			b.setDisable(true);

			promptText("Enter key", "");
			writeMessage("Keys usually consist of 26 letters. "
					+ "Each character in the plaintext is replaced with the corresponding letter in the cipher alphabet. ");
		}

		// Columnar Transposition Cipher
		if (combo.getValue() == "Columnar Transposition") {
			a.setDisable(false);
			b.setDisable(false);

			promptText("Enter keyword", "Enter pad character");
			writeMessage(
					"The key for this cipher is a keyword. The Plaintext will be padded so that it neatly fits in a rectangle.");
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
					AllCipherMethods.railFence(area.getText(), Integer.parseInt(a.getText()));
					// AllCipherMethods.railFence(area.getText(),
					// Integer.parseInt(a.textProperty().get()));

				} catch (Exception E) {
					System.out.println(E);
					writeMessage("Key has to be a valid integer!");
				}
			}

			// Baconian Cipher
			if (combo.getValue() == "Baconian") {
				AllCipherMethods.baconian(area.getText(), a.getText(), b.getText());
			}

			// Polybius Square Cipher
			if (combo.getValue() == "Polybius Square") {
				try {
					if (a.getText().length() <= 25 || b.getText().length() != 5) {
						AllCipherMethods.polybiusSquare(area.getText(), a.getText(), b.getText());
						writeMessage(
								"Any character that's in plaintext but not in key will be replaced by a random Unicode character.");
					} else
						throw new Exception();
				} catch (Exception E) {
					writeMessage("Key has to be 25 characters long and number of cipher characters should only be 5! "
							+ "Padding/random characters will be added to a key that's <25 characters long.");
				}
			}

			// Simple Substitution Cipher
			if (combo.getValue() == "Simple Substitution") {
				try {
					if (a.getText().length() == 26)
						AllCipherMethods.simpleSubstitution(area.getText(), a.getText());
					else
						throw new Exception();
				} catch (Exception E) {
					writeMessage(
							"Key should contain 26 characters! Be careful not to use repeated characters for different letters!");
				}
			}

			// Columnar Transposition Cipher
			if (combo.getValue() == "Columnar Transposition") {
				try {
					if(a.getText().length()==0 || b.getText().length()==0)
						throw new Exception();
					AllCipherMethods.columnarTransposition(area.getText(),a.getText(),b.getText());
				} catch (Exception E) {
					writeMessage("Keyword/padding characters can't be empty!");
				}
			}
		});

	}

	public void writeMessage(String str) {
		message.setText(str); // edit the Label to show appropriate messages w.r.t to selected cipher
	}

	public void promptText(String str_a, String str_b) {
		a.setPromptText(str_a);
		b.setPromptText(str_b);
	}

}
