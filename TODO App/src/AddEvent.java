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

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public VBox elements() {
		VBox vbox = new VBox();
		HBox hbox = new HBox();

		Label l_name = new Label("Name:"), l_date = new Label("Date:"); // l_[a-z]: l=label
		TextField t_name; // t_[a-z]: t=text
		DatePicker date=new DatePicker();



		return vbox;
	}
}
