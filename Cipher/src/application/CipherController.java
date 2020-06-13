package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combo.setItems(choices);
		combo.setOnAction(this::getCipherMethod);
	}

	@FXML
	public void getCipherMethod(ActionEvent event) {
		if(combo.getValue()=="Atbash")
			AllCipherMethods.atbash(area.getText());

		if(combo.getValue()=="ROT13")
			AllCipherMethods.rot13(area.getText());

	}

}
