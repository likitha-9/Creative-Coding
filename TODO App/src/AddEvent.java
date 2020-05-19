import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddEvent {

	static StackPane stack = new StackPane();

	public static void addEvent() {
		Stage stage = new Stage();
		stage.setTitle("Add an event");

		Scene scene = new Scene(stack, 300, 200);
		stack.getChildren().add(elements());

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public static VBox elements() {
		/*
		 * A VBox consisting of multiple HBoxes. --> Name* (required), Date (an empty
		 * date will mean indefiniteness), Description (optional)
		 */
		VBox vbox = new VBox(10);
		HBox hb_name = new HBox(10), hb_date = new HBox(10), hb_descrip = new HBox(10);

		Label l_name = new Label("Name:\t"), l_date = new Label("Date:\t"),
				l_descrip = new Label("Enter description:\t"); // l_[a-z]: l=label

		TextField t_name = new TextField(), t_descrip = new TextField(); // t_[a-z]: t=text

		/*
		 * // let the t_descrip TextField resize dynamically w.r.t. the text.
		 * t_descrip.setMinWidth(50); t_descrip.setPrefWidth(50);
		 * t_descrip.setMaxWidth(160); t_descrip.setMinHeight(20);
		 * t_descrip.textProperty().addListener(new ChangeListener<String>() {
		 *
		 * @Override public void changed(ObservableValue<? extends String> arg0, String
		 * arg1, String arg2) { t_descrip.setPrefWidth( t_descrip.getText().length() *
		 * 7); /* assuming that every character in JavaFX font is 7px in width. (guessed
		 * by trial & error)
		 */
		/*
		 * t_descrip.setPrefHeight(20*(t_descrip.getText().length() % 160)); }
		 *
		 * });
		 */

		DatePicker date = new DatePicker();

		// Add name, date, description
		hb_name.getChildren().addAll(l_name, t_name);
		hb_date.getChildren().addAll(l_date, date);
		hb_descrip.getChildren().addAll(l_descrip, t_descrip);

		vbox.getChildren().addAll(hb_name, hb_date, hb_descrip);

		return vbox;
	}
}
